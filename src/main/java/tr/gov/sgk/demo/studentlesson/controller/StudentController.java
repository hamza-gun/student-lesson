package tr.gov.sgk.demo.studentlesson.controller;

import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tr.gov.sgk.demo.studentlesson.dto.StudentDTO;
import tr.gov.sgk.demo.studentlesson.entity.Student;
import tr.gov.sgk.demo.studentlesson.service.StudentService;
//import tr.gov.sgk.demo.studentlesson.utility.PDFGeneratorStudent;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list-students")
    public String getAllStudents(Model model) {
        List<StudentDTO> studentDTOList = studentService.getAllStudents();
        model.addAttribute("student", studentDTOList);
        return "list-students";
    }

    @PostMapping("/search-form-student")
    public String searchStudents(@RequestParam("number") Integer number, Model model) {
        if(number != null) {
            List<Student> student = studentService.findByNumber(number);
            model.addAttribute("student", student);
            return "list-students";
        } else{
            List<StudentDTO> student = studentService.getAllStudents();
            model.addAttribute("list-students",student);
            return "list-students";
        }
    }

//    @GetMapping("/pdf/student")
//    public void generator(HttpServletResponse response) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
//        String currentDateTime = dateFormat.format(new Date());
//        String headerkey = "Content-Disposition";
//        String headervalue = "attachment; filename=pdf_"+currentDateTime+".pdf";
//        response.setHeader(headerkey, headervalue);
//        List<StudentDTO> students = studentService.getAllStudents();
//        PDFGeneratorStudent generetorUser = new PDFGeneratorStudent();
//        generetorUser.setStudentList(students);
//        //generetorUser.generate(response);
//    }

    @GetMapping("/showFormForStudentAdd")
    public String showFormForStudentAdd(Model theModel) {
        // create model attribute to bind form data
        Student theStudent = new Student();
        theModel.addAttribute("student", theStudent);
        return "form-student";
    }

    @PostMapping("/save-student")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDTO theStudent,
                              BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "form-student";
        }
        studentService.saveStudent(theStudent);
        return "redirect:/student/list-students";
    }

    @GetMapping("/showFormForStudentUpdate")
    public String showFormForStudentUpdate(@RequestParam("studentId") Integer theId,
                                           Model theModel) {
        StudentDTO theStudent = studentService.getStudentById(theId);
        List<StudentDTO> studentList = studentService.getAllStudents();
        theModel.addAttribute("student", theStudent);
        return "form-student";
    }

    @GetMapping("/delete-student")
    public String deleteCategory(@RequestParam("studentId") Integer theId) {
        StudentDTO tempStudent = studentService.getStudentById(theId);

        if (tempStudent == null) {
            throw new RuntimeException("Student id not found : " + theId);
        }
        studentService.deleteStudentbyId(theId);
        return "redirect:/student/list-students";
    }

}
