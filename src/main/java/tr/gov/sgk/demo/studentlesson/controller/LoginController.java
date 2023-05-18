package tr.gov.sgk.demo.studentlesson.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tr.gov.sgk.demo.studentlesson.entity.User;
import tr.gov.sgk.demo.studentlesson.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/showMyLoginPage")
    public String home() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage() {
        return "access-denied";
    }

    @PostMapping("/showMyLoginPage")
    public String showMyLoginPage(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Kullanıcı adı veya parola yanlış.");
            return "login";
        }
    }
}
