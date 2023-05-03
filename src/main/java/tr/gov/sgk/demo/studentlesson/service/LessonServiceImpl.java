package tr.gov.sgk.demo.studentlesson.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<Lesson> findByLessonCode(String lessonCode) {
        return lessonRepository.findByLessonCode(lessonCode);
    }
}
