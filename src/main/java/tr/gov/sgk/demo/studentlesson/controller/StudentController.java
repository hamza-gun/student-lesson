package tr.gov.sgk.demo.studentlesson.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tr.gov.sgk.demo.studentlesson.dto.StudentDTO;
import tr.gov.sgk.demo.studentlesson.entity.Student;
import tr.gov.sgk.demo.studentlesson.log.ProjectLogger;
import tr.gov.sgk.demo.studentlesson.service.StudentService;
import tr.gov.sgk.demo.studentlesson.utility.PDFGeneratorStudent;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    public ProjectLogger projectLogger;

    @Autowired
    public StudentController(StudentService studentService, ProjectLogger projectLogger) {
        this.studentService = studentService;
        this.projectLogger = projectLogger;
    }

    @GetMapping("/list-students")
    public String getAllStudents(Model model) {
        List<StudentDTO> studentList = studentService.getAllStudents();
        model.addAttribute("students", studentList);
        return "list-students";
    }

    @PostMapping("/search-form-student")
    public ModelAndView searchStudents(@RequestParam(value = "keyword", required = false) String keyword) {
        ModelAndView mav = new ModelAndView("list-students");
        List<?> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = studentService.findByKeyword(keyword);
        } else {
            students = studentService.getAllStudents();
        }
        mav.addObject("students", students);
        mav.addObject("keyword", keyword);
        return mav;
    }

    @GetMapping("/pdf/student")
    public void generator(HttpServletResponse response) throws IOException, com.itextpdf.text.DocumentException{
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_"+currentDateTime+".pdf";
        response.setHeader(headerkey, headervalue);
        List<StudentDTO> students = studentService.getAllStudents();
        PDFGeneratorStudent generetorUser = new PDFGeneratorStudent();
        generetorUser.setStudentList(students);
        generetorUser.generate(response);
    }

    @GetMapping("/showFormForStudentAdd")
    public String showFormForStudentAdd(Model theModel) {

        StudentDTO theStudent = new StudentDTO();
        theModel.addAttribute("students", theStudent);
        return "form-student";
    }

    @PostMapping("/save-student")
    public String saveStudent(@Valid @ModelAttribute("students") StudentDTO theStudent,
                              BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "form-student";
        }

        studentService.saveStudent(theStudent);
        projectLogger.addStudent(theStudent);
        return "redirect:/student/list-students";
    }

    @GetMapping("/showFormForStudentUpdate")
    public String showFormForStudentUpdate(@RequestParam("studentId") Integer studentId,
                                           Model theModel) {
        StudentDTO theStudent = studentService.getStudentById(studentId);
        List<StudentDTO> studentList = studentService.getAllStudents();
        theModel.addAttribute("students", theStudent);
        return "form-student";
    }

    @GetMapping("/delete-student")
    public String deleteStudent(@RequestParam("studentId") Integer studentId) {
        StudentDTO tempStudent = studentService.getStudentById(studentId);

        if (tempStudent == null) {
            throw new RuntimeException("Student id not found : " + studentId);
        }
        studentService.deleteStudentbyId(studentId);
        return "redirect:/student/list-students";
    }

}
