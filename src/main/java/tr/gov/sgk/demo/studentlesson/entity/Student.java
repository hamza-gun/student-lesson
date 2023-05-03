package tr.gov.sgk.demo.studentlesson.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "number")
    private Integer number;

    @OneToMany(mappedBy = "student")
    private List<StudentLessons> studentLessons = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<StudentNotes> studentNotes = new ArrayList<>();
}
