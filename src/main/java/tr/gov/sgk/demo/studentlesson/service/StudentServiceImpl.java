package tr.gov.sgk.demo.studentlesson.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tr.gov.sgk.demo.studentlesson.dto.StudentDTO;
import tr.gov.sgk.demo.studentlesson.entity.Student;
import tr.gov.sgk.demo.studentlesson.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAllByOrderByNumber();
        return students.stream().map(student -> modelMapper.map(student, StudentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id:" + id));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void saveStudent(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentbyId(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id:" + id));
        studentRepository.delete(student);
    }

    @Override
    public List<Student> findByNumber(int number) {
        return studentRepository.findByNumber(number);
    }

}