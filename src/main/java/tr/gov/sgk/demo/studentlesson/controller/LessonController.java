package tr.gov.sgk.demo.studentlesson.controller;

import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tr.gov.sgk.demo.studentlesson.dto.LessonDTO;
import tr.gov.sgk.demo.studentlesson.dto.StudentDTO;
import tr.gov.sgk.demo.studentlesson.entity.Lesson;
import tr.gov.sgk.demo.studentlesson.service.LessonService;
import tr.gov.sgk.demo.studentlesson.utility.PDFGeneratorLesson;
import tr.gov.sgk.demo.studentlesson.utility.PDFGeneratorStudent;

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

    @GetMapping("/list-lessons")
    public String getAllLessons(Model model) {
        List<LessonDTO> lessonDTOList = lessonService.getAllLessons();
        model.addAttribute("lesson", lessonDTOList);

        return "list-lessons";
    }

    @PostMapping("/search-form-lesson")
    public String searchLessons(@RequestParam("lessonCode") String lessonCode, Model model) {
        if(lessonCode != null) {
            List<Lesson> lesson = lessonService.findByLessonCode(lessonCode);
            model.addAttribute("lesson", lesson);
            return "list-lessons";
        } else{
            List<LessonDTO> lesson = lessonService.getAllLessons();
            model.addAttribute("list-lessons",lesson);
            return "list-lessons";
        }
    }

    @GetMapping("/pdf/lesson")
    public void generator(HttpServletResponse response) throws DocumentException, IOException {
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

    @GetMapping("/showFormForLessonAdd")
    public String showFormForLessonAdd(Model theModel) {
        // create model attribute to bind form data
        Lesson theLesson = new Lesson();
        theModel.addAttribute("lesson", theLesson);
        return "form-lesson";
    }

    @PostMapping("/save-lesson")
    public String saveLesson(@Valid @ModelAttribute("lesson") LessonDTO theLessonDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "form-lesson";
        }
        lessonService.saveLesson(theLessonDTO);
        return "redirect:/lesson/list-lessons";
    }

    @GetMapping("/showFormForLessonUpdate")
    public String showFormForLessonUpdate(@RequestParam("lessonId") Integer theId,
                                          Model theModel) {
        LessonDTO theLessonDTO = lessonService.getLessonById(theId);
        List<LessonDTO> lessonList = lessonService.getAllLessons();
        theModel.addAttribute("lesson", theLessonDTO);

        return "form-lesson";
    }

    @GetMapping("/delete-lesson")
    public String deleteLesson(@RequestParam("lessonId") Integer theId) {
        LessonDTO tempLesson = lessonService.getLessonById(theId);

        if (tempLesson == null) {
            throw new RuntimeException("Lesson id not found : " + theId);
        }
        lessonService.deleteLessonById(theId);
        return "redirect:/lesson/list-lessons";
    }
}
