package tr.gov.sgk.demo.studentlesson.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.gov.sgk.demo.studentlesson.entity.Lesson;

import java.util.List;
@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findAllByOrderByLessonCode();
//    List<Lesson> findByLessonCode(String lessonCode);
    List<Lesson> findByLessonNameContainingIgnoreCaseOrLessonCodeContainingIgnoreCase(String lessonName, String lessonCode);
}
