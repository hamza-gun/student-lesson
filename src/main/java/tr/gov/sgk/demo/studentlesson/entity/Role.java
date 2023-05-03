package tr.gov.sgk.demo.studentlesson.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

}
