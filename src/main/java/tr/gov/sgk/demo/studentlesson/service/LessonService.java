package tr.gov.sgk.demo.studentlesson.service;

import tr.gov.sgk.demo.studentlesson.dto.LessonDTO;
import tr.gov.sgk.demo.studentlesson.entity.Lesson;

import java.util.List;

public interface LessonService {
    List<LessonDTO> getAllLessons();

    LessonDTO getLessonById(int id);

    void saveLesson(LessonDTO lessonDTO);

    void deleteLessonById(int id);

    List<Lesson> findByLessonCode(String lessonCode);
}
