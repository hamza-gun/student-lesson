package tr.gov.sgk.demo.studentlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.gov.sgk.demo.studentlesson.entity.StudentLessons;

@Repository
public interface StudentLessonsRepository extends JpaRepository<StudentLessons, Integer> {
    boolean existsByStudentIdAndLessonId(Integer studentId, Integer lessonId);
}
