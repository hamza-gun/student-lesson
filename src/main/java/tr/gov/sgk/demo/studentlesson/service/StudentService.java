package tr.gov.sgk.demo.studentlesson.service;

import tr.gov.sgk.demo.studentlesson.dto.StudentDTO;
import tr.gov.sgk.demo.studentlesson.entity.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(int id);

    void saveStudent(StudentDTO studentDTO);

    void deleteStudentbyId(int id);

    List<Student> findByNumber(int number);

}
