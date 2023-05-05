package tr.gov.sgk.demo.studentlesson.service;

import tr.gov.sgk.demo.studentlesson.dto.StudentNotesDTO;
import tr.gov.sgk.demo.studentlesson.entity.Lesson;
import tr.gov.sgk.demo.studentlesson.entity.StudentNotes;

import java.io.OutputStream;
import java.util.List;

public interface StudentNotesService {
    List<StudentNotesDTO> getAllNotes();

    StudentNotesDTO getNoteById(Integer id);

    void saveNote(StudentNotesDTO studentNotesDTO);

    void deleteNoteById(int id);

    List<StudentNotes> findByNote(int note);

    List<StudentNotes>findByLessonLessonCodeContainingIgnoreCaseOrStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCase(String lessonCode, String firstName, String lastName);

    List<StudentNotes> findByKeyword(String keyword);

}
