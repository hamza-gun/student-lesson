package tr.gov.sgk.demo.studentlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tr.gov.sgk.demo.studentlesson.dto.LessonDTO;
import tr.gov.sgk.demo.studentlesson.dto.StudentDTO;
import tr.gov.sgk.demo.studentlesson.dto.StudentLessonsDTO;
import tr.gov.sgk.demo.studentlesson.entity.Lesson;
import tr.gov.sgk.demo.studentlesson.entity.Student;
import tr.gov.sgk.demo.studentlesson.exception.DuplicateStudentLessonException;
import tr.gov.sgk.demo.studentlesson.exception.ResourceNotFoundException;
import tr.gov.sgk.demo.studentlesson.service.LessonService;
import tr.gov.sgk.demo.studentlesson.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student-lessons")
public class StudentLessonsController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private LessonService lessonService;

    @GetMapping("/list-student-lessons")
    public String listStudentLessons(Model model){
        List<StudentLessonsDTO> studentLessonsDTOS = studentService.getAllStudentLessons();
        model.addAttribute("studentLessons", studentLessonsDTOS);
        return "list-student-lessons";
    }

    @GetMapping("/student-lessons")
    public String showStudentLessonForm(Model model) {
        List<StudentDTO> studentList = studentService.getAllStudents();
        List<LessonDTO> lessonList = lessonService.getAllLessons();
        StudentLessonsDTO studentLessons = new StudentLessonsDTO();
        model.addAttribute("studentList", studentList);
        model.addAttribute("lessonList", lessonList);
        model.addAttribute("studentLessons", studentLessons);
        return "student-lessons-form";
    }

    @PostMapping("/save-student-lessons")
    public String saveStudentLessons(@ModelAttribute("studentLessons") StudentLessonsDTO studentLessons, RedirectAttributes redirectAttributes) {
        try {
            studentService.saveStudentLesson(studentLessons);
            redirectAttributes.addFlashAttribute("successMessage", "Student lesson has been saved successfully.");
        } catch (DuplicateStudentLessonException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while saving student lesson.");
        }
        return "redirect:/student-lessons/list-student-lessons";
    }

    @GetMapping("/showFormForStudentLessonAdd")
    public String showFormForStudentLessonAdd(Model model) {
        List<StudentDTO> students = studentService.getAllStudents();
        List<LessonDTO> lessons = lessonService.getAllLessons();

        StudentLessonsDTO studentLessonsDTO = new StudentLessonsDTO();
        model.addAttribute("studentLessons", studentLessonsDTO);
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);

        return "form-student-lesson";
    }
}
