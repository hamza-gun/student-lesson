package tr.gov.sgk.demo.studentlesson.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.gov.sgk.demo.studentlesson.dto.LessonDTO;
import tr.gov.sgk.demo.studentlesson.entity.Lesson;
import tr.gov.sgk.demo.studentlesson.repository.LessonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LessonServiceImpl(LessonRepository lessonRepository, ModelMapper modelMapper) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LessonDTO> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAllByOrderByLessonCode();
        return lessons.stream().map(lesson -> modelMapper.map(lesson, LessonDTO.class)).collect(Collectors.toList());
    }

    @Override
    public LessonDTO getLessonById(int id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id:" + id));
        return modelMapper.map(lesson, LessonDTO.class);
    }

    @Override
    public void saveLesson(LessonDTO lessonDTO) {
        Lesson lesson = modelMapper.map(lessonDTO, Lesson.class);
        lessonRepository.save(lesson);
    }

    @Override
    public void deleteLessonById(int id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id:" + id));
        lessonRepository.delete(lesson);
    }

//    @Override
//    public List<Lesson> findByLessonCode(String lessonCode) {
//        return lessonRepository.findByLessonCode(lessonCode);
//    }

    @Override
    public List<Lesson> findByLessonNameContainingIgnoreCaseOrLessonCodeContainingIgnoreCase(String lessonName, String lessonCode) {
        return lessonRepository.findByLessonNameContainingIgnoreCaseOrLessonCodeContainingIgnoreCase(lessonName, lessonCode);
    }

    @Override
    public List<Lesson> findByKeyword(String keyword) {
        if (keyword == null) {
            keyword = "";
        }
        return lessonRepository.findByLessonNameContainingIgnoreCaseOrLessonCodeContainingIgnoreCase(keyword, keyword);
    }

//    @Override
//    public Page<Lesson> findByLessonCodePaged(String lessonCode, Pageable pageable) {
//        return lessonRepository.findByLessonCode(lessonCode, pageable);
//    }
//
//    @Override
//    public Page<LessonDTO> getAllLessonsPaged(Pageable pageable) {
//        Page<Lesson> lessonPage = lessonRepository.findAll(pageable);
//        return lessonPage.map(this::convertToDto);
//    }

//    private LessonDTO convertToDto(Lesson lesson) {
//        return modelMapper.map(lesson, LessonDTO.class);
//    }
}
