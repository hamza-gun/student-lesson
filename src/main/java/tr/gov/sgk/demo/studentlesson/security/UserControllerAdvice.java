//package tr.gov.sgk.demo.studentlesson.security;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//@ControllerAdvice
//public class UserControllerAdvice {
//    @ModelAttribute("currentUser")
//    public UserDetails getCurrentUser() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            return (UserDetails) principal;
//        } else {
//            throw new UsernameNotFoundException("User not found.");
//        }
//    }
//}
