package tr.gov.sgk.demo.studentlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.gov.sgk.demo.studentlesson.entity.Lesson;
import tr.gov.sgk.demo.studentlesson.entity.StudentNotes;

import java.util.List;
@Repository
public interface NoteRepository extends JpaRepository<StudentNotes, Integer>{
    List<StudentNotes> findAllByOrderByNoteDesc();
    List<StudentNotes> findByNote(Integer note);
    List<StudentNotes> findAllByNoteContaining(String keyword);
    List<StudentNotes> findByLessonLessonCodeContainingIgnoreCaseOrStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCase(String lessonCode, String firstName, String lastName);
}


