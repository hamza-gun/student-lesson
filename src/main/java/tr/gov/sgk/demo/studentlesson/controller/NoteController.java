package tr.gov.sgk.demo.studentlesson.controller;

import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tr.gov.sgk.demo.studentlesson.dto.LessonDTO;
import tr.gov.sgk.demo.studentlesson.dto.StudentNotesDTO;
import org.modelmapper.ModelMapper;
import tr.gov.sgk.demo.studentlesson.entity.Lesson;
import tr.gov.sgk.demo.studentlesson.entity.StudentNotes;
import tr.gov.sgk.demo.studentlesson.service.LessonService;
import tr.gov.sgk.demo.studentlesson.service.StudentNotesService;
import tr.gov.sgk.demo.studentlesson.service.StudentService;
import tr.gov.sgk.demo.studentlesson.utility.PDFGeneratorLesson;
import tr.gov.sgk.demo.studentlesson.utility.PDFGeneratorNote;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    private StudentNotesService studentNotesService;
    private StudentService studentService;
    private LessonService lessonService;
    private final ModelMapper modelMapper;

    public NoteController(StudentNotesService studentNotesService, StudentService studentService,
                                  LessonService lessonService, ModelMapper modelMapper) {
        this.studentNotesService = studentNotesService;
        this.studentService = studentService;
        this.lessonService = lessonService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/list-notes")
    public String listNotes(Model theModel) {
        List<StudentNotesDTO> studentNotesDTOList = studentNotesService.getAllNotes();
        theModel.addAttribute("note", studentNotesDTOList);
        return "list-notes";
    }

    @PostMapping("/search-form-note")
    public String searchNotes(@RequestParam("note") Integer note, Model model){
        if (note != null) {
            List<StudentNotes> notes = studentNotesService.findByNote(note);
//            List<StudentNotes> lessons = studentNotesService.findByLessonCode(lessonCode);
            model.addAttribute("note", notes);
//            model.addAttribute("lessonCode", lessons);
        } else{
            List<StudentNotesDTO> notes = studentNotesService.getAllNotes();
            model.addAttribute("list-notes", notes);
        }
        return "list-notes";
    }

    @GetMapping("/pdf/note")
    public void generator(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_"+currentDateTime+".pdf";
        response.setHeader(headerkey, headervalue);
        List<StudentNotesDTO> notes = studentNotesService.getAllNotes();
        PDFGeneratorNote generetorUser = new PDFGeneratorNote();
        generetorUser.setNoteList(notes);
        generetorUser.generate(response);
    }

    @GetMapping("/showFormForNoteAdd")
    public String showFormForNoteAdd(Model theModel) {
        StudentNotesDTO studentNotesDTO = new StudentNotesDTO();
        theModel.addAttribute("note", studentNotesDTO);
        theModel.addAttribute("studentList", studentService.getAllStudents());
        theModel.addAttribute("lessonList", lessonService.getAllLessons());
        return "form-note";
    }

    @PostMapping("/save-note")
    public String saveNote(@Valid @ModelAttribute("note") StudentNotesDTO theStudentNotes,
                           BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            theModel.addAttribute("studentList", studentService.getAllStudents());
            theModel.addAttribute("lessonList", lessonService.getAllLessons());
            return "form-note";
        }
        theModel.addAttribute("studentList", studentService.getAllStudents());
        theModel.addAttribute("lessonList", lessonService.getAllLessons());
        studentNotesService.saveNote(theStudentNotes);
        return "redirect:/note/list-notes";
    }

    @GetMapping("/showFormForNoteUpdate")
    public String showFormForUpdate(@RequestParam("noteId") int theId,
                                    Model theModel) {
        StudentNotesDTO studentNotesDTO = studentNotesService.getNoteById(theId);
        theModel.addAttribute("note", studentNotesDTO);
        theModel.addAttribute("studentList", studentService.getAllStudents());
        theModel.addAttribute("lessonList", lessonService.getAllLessons());
        return "form-note";
    }

    @GetMapping("/delete-note")
    public String deleteNote(@RequestParam("noteId") int theId) {
        studentNotesService.deleteNoteById(theId);
        return "redirect:/note/list-notes";
    }
}
