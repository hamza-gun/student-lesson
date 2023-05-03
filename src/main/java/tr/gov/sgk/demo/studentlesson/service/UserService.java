package tr.gov.sgk.demo.studentlesson.service;

import tr.gov.sgk.demo.studentlesson.dto.UserDTO;
import tr.gov.sgk.demo.studentlesson.entity.User;

import java.util.List;

public interface UserService{
    User createUser(UserDTO userDto);
    User updateUser(Long userId, UserDTO userDto);
    void deleteUser(Long userId);
    User getUserById(Long userId);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    User authenticate(String username, String password);
}
