package tr.gov.sgk.demo.studentlesson.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.gov.sgk.demo.studentlesson.dto.StudentDTO;
import tr.gov.sgk.demo.studentlesson.dto.StudentLessonsDTO;
import tr.gov.sgk.demo.studentlesson.entity.Student;
import tr.gov.sgk.demo.studentlesson.entity.StudentLessons;
import tr.gov.sgk.demo.studentlesson.exception.DuplicateStudentLessonException;
import tr.gov.sgk.demo.studentlesson.repository.LessonRepository;
import tr.gov.sgk.demo.studentlesson.repository.StudentLessonsRepository;
import tr.gov.sgk.demo.studentlesson.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private StudentLessonsRepository studentLessonsRepository;

    public StudentServiceImpl(StudentRepository studentRepository, LessonRepository lessonRepository,
                              StudentLessonsRepository studentLessonsRepository) {
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.studentLessonsRepository = studentLessonsRepository;
    }
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

//    @Override
//    public void saveStudentLesson(StudentLessonsDTO studentLessonsDTO) {
//        for (Integer lessonId : studentLessonsDTO.getLessonIds()) {
//            boolean isDuplicate = studentLessonsRepository.existsByStudentIdAndLessonId(studentLessonsDTO.getStudentId(), lessonId);
//
//            if (isDuplicate) {
//                String message = "Bu öğrenci daha önce bu derse kaydedilmiş.";
//                throw new DuplicateStudentLessonException(message);
//            }
//
//            // Yeni öğrenci kaydını veritabanına ekle
//            StudentLessons studentLessons = new StudentLessons();
//            studentLessons.setStudent(studentRepository.findById(studentLessonsDTO.getStudentId())
//                    .orElseThrow(() -> new EntityNotFoundException("Student with id " + studentLessonsDTO.getStudentId() + " not found")));
//            studentLessons.setLesson(lessonRepository.findById(lessonId)
//                    .orElseThrow(() -> new EntityNotFoundException("Lesson with id " + lessonId + " not found")));
//            studentLessonsRepository.save(studentLessons);
//        }
//    }

    @Override
    public void saveStudentLesson(StudentLessonsDTO studentLessonsDTO) {
        for (Integer lessonId : studentLessonsDTO.getLessonIds()) {
            boolean isDuplicate = studentLessonsRepository.existsByStudentIdAndLessonId(studentLessonsDTO.getStudentId(), lessonId);

            if (isDuplicate) {
                String message = "Bu öğrenci daha önce bu derse kaydedilmiş.";
                throw new DuplicateStudentLessonException(message);
            }

            // Yeni öğrenci kaydını veritabanına ekle
            StudentLessons studentLessons = new StudentLessons();
            studentLessons.setStudent(studentRepository.findById(studentLessonsDTO.getStudentId())
                    .orElseThrow(() -> new EntityNotFoundException("Student with id " + studentLessonsDTO.getStudentId() + " not found")));
            studentLessons.setLesson(lessonRepository.findById(lessonId)
                    .orElseThrow(() -> new EntityNotFoundException("Lesson with id " + lessonId + " not found")));
            studentLessonsRepository.save(studentLessons);
        }
    }


//    @Override
//    public List<StudentLessonsDTO> getAllStudentLessons() {
//        List<StudentLessons> studentLessonsList = studentLessonsRepository.findAll();
//        List<StudentLessonsDTO> studentLessonsDTOList = new ArrayList<>();
//        for (StudentLessons studentLessons : studentLessonsList) {
//            StudentLessonsDTO studentLessonsDTO = new StudentLessonsDTO();
//            studentLessonsDTO.setId(studentLessons.getId());
//            studentLessonsDTO.setStudentId(studentLessons.getStudent().getId());
//            studentLessonsDTO.setLessonIds(Collections.singletonList(studentLessons.getLesson().getId()));
//            studentLessonsDTOList.add(studentLessonsDTO);
//        }
//        return studentLessonsDTOList;
//    }

    @Override
    public List<StudentLessonsDTO> getAllStudentLessons() {
        List<StudentLessons> studentLessonsList = studentLessonsRepository.findAll();
        List<StudentLessonsDTO> studentLessonsDTOList = new ArrayList<>();
        if(studentLessonsList.size()!=0) {
            for (StudentLessons studentLessons : studentLessonsList) {
                StudentLessonsDTO studentLessonsDTO = new StudentLessonsDTO();
                studentLessonsDTO.setId(studentLessons.getId());
                studentLessonsDTO.setStudentId(studentLessons.getStudent().getId());
                studentLessonsDTO.setLessonIds(Collections.singletonList(studentLessons.getLesson().getId()));
                studentLessonsDTOList.add(studentLessonsDTO);
            }
        }
        return studentLessonsDTOList;
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