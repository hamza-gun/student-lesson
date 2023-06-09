package tr.gov.sgk.demo.studentlesson.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.gov.sgk.demo.studentlesson.dto.StudentNotesDTO;
import tr.gov.sgk.demo.studentlesson.entity.StudentNotes;
import tr.gov.sgk.demo.studentlesson.repository.LessonRepository;
import tr.gov.sgk.demo.studentlesson.repository.NoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentNotesServiceImpl implements StudentNotesService {

    @Autowired
    private NoteRepository studentNotesRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ModelMapper modelMapper;


    public StudentNotesDTO mapToStudentNotesDTO(StudentNotes studentNotes) {
        return modelMapper.map(studentNotes, StudentNotesDTO.class);
    }

    @Override
    public List<StudentNotesDTO> getAllNotes() {
        List<StudentNotes> studentNotes = studentNotesRepository.findAllByOrderByNoteDesc();
        return studentNotes.stream()
                .map(note -> modelMapper.map(note, StudentNotesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentNotesDTO getNoteById(Integer id) {
        Optional<StudentNotes> result = studentNotesRepository.findById(id);
        if (result.isPresent()) {
            StudentNotes note = result.get();
            return modelMapper.map(note, StudentNotesDTO.class);
        } else {
            throw new RuntimeException("Note with id " + id + " not found.");
        }
    }

    @Override
    public void saveNote(StudentNotesDTO studentNotesDTO) {
        StudentNotes note = modelMapper.map(studentNotesDTO, StudentNotes.class);
        studentNotesRepository.save(note);
    }

    @Override
    public void deleteNoteById(int id) {
        studentNotesRepository.deleteById(id);
    }

    @Override
    public List<StudentNotes> findByNote(int note) {
        return studentNotesRepository.findByNote(note);
    }

    @Override
    public List<StudentNotes> findByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCaseOrLessonLessonNameContainingIgnoreCaseOrLessonLessonCodeContainingIgnoreCase(String firstName, String lastName, String lessonName, String lessonCode) {
        return studentNotesRepository.findByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCaseOrLessonLessonNameContainingIgnoreCaseOrLessonLessonCodeContainingIgnoreCase(firstName, lastName, lessonName, lessonCode);
    }

    @Override
    public List<StudentNotes> findByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCaseOrLessonLessonNameContainingIgnoreCaseOrLessonLessonCodeContainingIgnoreCaseOrNote(String firstName, String lastName, String lessonName, String lessonCode, Integer note) {
        return studentNotesRepository.findByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCaseOrLessonLessonNameContainingIgnoreCaseOrLessonLessonCodeContainingIgnoreCaseOrNote(firstName, lastName, lessonName, lessonCode, note);
    }

    public List<StudentNotes> findByKeyword(String keyword) {
        if (keyword == null) {
            keyword = "";
        }
       return studentNotesRepository.findByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCaseOrLessonLessonNameContainingIgnoreCaseOrLessonLessonCodeContainingIgnoreCase(keyword, keyword, keyword, keyword);
    }

    public List<StudentNotes> findByKeywordAndNote(String keyword, Integer note) {
        if (keyword == null) {
            keyword = "";
        }
        return studentNotesRepository.findByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCaseOrLessonLessonNameContainingIgnoreCaseOrLessonLessonCodeContainingIgnoreCaseOrNote(keyword, keyword, keyword, keyword, note);
    }

    public List<StudentNotes> getNotesContainingKeyword(String keyword) {
        return studentNotesRepository.findAllByNoteContaining(keyword);
    }
}
