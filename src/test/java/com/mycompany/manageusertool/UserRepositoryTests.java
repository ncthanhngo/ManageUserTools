package com.mycompany.manageusertool;
import com.mycompany.manageusertool.model.User;
import com.mycompany.manageusertool.repository.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testAddUser()  {
        User user = new User();
        user.setFirstName("Duong");
        user.setLastName("Hoa");
        user.setEmail("duong@gmail.com");
        user.setPassword("mmananan");
        User savedUser = userRepository.save(user);
        Assertions.assertNotNull(savedUser);
        Assertions.assertTrue(savedUser.getId()>0);
        // Kiểm tra xem savedUser đã được lưu vào cơ sở dữ liệu hay không
        User retrievedUser = userRepository.findById(savedUser.getId()).orElse(null);
        Assertions.assertNotNull(retrievedUser); // Kiểm tra retrievedUser không null
        Assertions.assertEquals(savedUser.getFirstName(), retrievedUser.getFirstName()); // Kiểm tra dữ liệu được ghi đúng
        Assertions.assertEquals(savedUser.getLastName(), retrievedUser.getLastName());
        Assertions.assertEquals(savedUser.getEmail(), retrievedUser.getEmail());
        Assertions.assertEquals(savedUser.getPassword(), retrievedUser.getPassword());
    }
    @Test
    public void testListAll(){
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            System.out.println(user.getFirstName());
        }
    }
    @Test
    public void testFindById(){
        Integer userId = 14;//Optional<> returns type of User object injected
        Optional<User> user = userRepository.findById(userId);
        Assertions.assertNotNull(user);
    }
    @Test
    public void testDeleteById(){
        Integer userId = 17;
        userRepository.deleteById(userId);
        Optional<User> user = userRepository.findById(userId);
        Assertions.assertFalse(user.isPresent());
    }

}
