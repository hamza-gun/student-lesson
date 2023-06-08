package tr.gov.sgk.demo.studentlesson.controller;

import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tr.gov.sgk.demo.studentlesson.dto.LessonDTO;
import tr.gov.sgk.demo.studentlesson.entity.Lesson;
import tr.gov.sgk.demo.studentlesson.service.LessonService;
import tr.gov.sgk.demo.studentlesson.utility.PDFGeneratorLesson;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/lesson")
public class LessonController {
    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/list-lessons")
    public String getAllLessons(Model model) {
        List<LessonDTO> lessonDTOList = lessonService.getAllLessons();
        model.addAttribute("lessons", lessonDTOList);

        return "list-lessons";
    }

    @PostMapping("/search")
    public ModelAndView searchLessons(@RequestParam(value = "keyword", required = false) String keyword) {
        ModelAndView mav = new ModelAndView("list-lessons");
        List<?> lessons;
        if(keyword != null && !keyword.isEmpty()){
            lessons = lessonService.findByKeyword(keyword);
        }else{
            lessons = lessonService.getAllLessons();
        }
        mav.addObject("lessons", lessons);
        mav.addObject("keyword", keyword);

        return mav;
    }

    @GetMapping("/pdf")
    public void generator(HttpServletResponse response) throws DocumentException, IOException, com.itextpdf.text.DocumentException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_"+currentDateTime+".pdf";
        response.setHeader(headerkey, headervalue);
        List<LessonDTO> lessons = lessonService.getAllLessons();
        PDFGeneratorLesson generetorUser = new PDFGeneratorLesson();
        generetorUser.setLessonList(lessons);
        generetorUser.generate(response);
    }

    @GetMapping("/add-form")
    public String showFormForLessonAdd(Model theModel) {
        // create model attribute to bind form data
        Lesson theLesson = new Lesson();
        theModel.addAttribute("lesson", theLesson);
        return "form-lesson";
    }

    @PostMapping("/save")
    public String saveLesson(@Valid @ModelAttribute("lesson") LessonDTO theLessonDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "form-lesson";
        }
        lessonService.saveLesson(theLessonDTO);
        return "redirect:/lesson/list-lessons";
    }

    @GetMapping("/update-form")
    public String showFormForLessonUpdate(@RequestParam("lessonId") Integer lessonId,
                                          Model theModel) {
        LessonDTO theLessonDTO = lessonService.getLessonById(lessonId);
        List<LessonDTO> lessonList = lessonService.getAllLessons();
        theModel.addAttribute("lesson", theLessonDTO);

        return "form-lesson";
    }

    @GetMapping("/delete")
    public String deleteLesson(@RequestParam("lessonId") Integer lessonId) {
        LessonDTO tempLesson = lessonService.getLessonById(lessonId);

        if (tempLesson == null) {
            throw new RuntimeException("Lesson id not found : " + lessonId);
        }
        lessonService.deleteLessonById(lessonId);
        return "redirect:/lesson/list-lessons";
    }
}
