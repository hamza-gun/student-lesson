package tr.gov.sgk.demo.studentlesson.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "lesson_code")
    private String lessonCode;

    @OneToMany(mappedBy = "lesson")
    private List<StudentLessons> studentLessons = new ArrayList<>();

    @OneToMany(mappedBy = "lesson")
    private List<StudentNotes> studentNotes = new ArrayList<>();
}
