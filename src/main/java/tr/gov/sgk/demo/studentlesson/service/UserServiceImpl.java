package tr.gov.sgk.demo.studentlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.gov.sgk.demo.studentlesson.dto.UserDTO;
import tr.gov.sgk.demo.studentlesson.entity.User;
import tr.gov.sgk.demo.studentlesson.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDto) {
        return null;
    }

    @Override
    public User updateUser(Long userId, UserDTO userDto) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
