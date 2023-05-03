package tr.gov.sgk.demo.studentlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.gov.sgk.demo.studentlesson.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
