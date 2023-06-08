package tr.gov.sgk.demo.studentlesson.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tr.gov.sgk.demo.studentlesson.dto.LessonDTO;
import tr.gov.sgk.demo.studentlesson.entity.Lesson;

import java.util.List;

public interface LessonService {
    List<LessonDTO> getAllLessons();

    LessonDTO getLessonById(int id);

    void saveLesson(LessonDTO lessonDTO);

    void deleteLessonById(int id);

//    List<Lesson> findByLessonCode(String lessonCode);

    List<Lesson> findByLessonNameContainingIgnoreCaseOrLessonCodeContainingIgnoreCase(String lessonName, String lessonCode);

    List<Lesson> findByKeyword(String keyword);
}
