package tr.gov.sgk.demo.studentlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.gov.sgk.demo.studentlesson.entity.Student;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    List<Student> findAllByOrderByNumber();
    List<Student> findByNumber(Integer number);
    List<Student> findByLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(String firstName, String lastName);
}