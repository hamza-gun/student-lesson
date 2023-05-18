package tr.gov.sgk.demo.studentlesson.service;

import tr.gov.sgk.demo.studentlesson.dto.StudentDTO;
import tr.gov.sgk.demo.studentlesson.dto.StudentLessonsDTO;
import tr.gov.sgk.demo.studentlesson.entity.Student;
import tr.gov.sgk.demo.studentlesson.exception.DuplicateStudentLessonException;
import tr.gov.sgk.demo.studentlesson.exception.ResourceNotFoundException;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(int id);

    void saveStudent(StudentDTO studentDTO);

    void deleteStudentbyId(int id);

    List<Student> findByNumber(int number);

    void saveStudentLesson(StudentLessonsDTO studentLessonsDTO) throws DuplicateStudentLessonException, ResourceNotFoundException;

    List<StudentLessonsDTO> getAllStudentLessons();
}
