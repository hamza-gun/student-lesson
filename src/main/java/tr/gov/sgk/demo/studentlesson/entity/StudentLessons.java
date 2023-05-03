package tr.gov.sgk.demo.studentlesson.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "student_lessons")
public class StudentLessons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}
