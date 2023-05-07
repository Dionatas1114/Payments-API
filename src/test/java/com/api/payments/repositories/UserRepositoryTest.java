package com.api.payments.repositories;

import com.api.payments.mocks.UsersMocked;
import com.api.payments.utils.Log;
import lombok.val;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private com.api.payments.repository.UserRepository userRepository;

    @Test
    @DisplayName("This test should return all user data")
    public void findAllUsers() {
        val userDataMocked = new UsersMocked().returnUserDataMocked();

        val userPersisted = testEntityManager.persist(userDataMocked);
        Log.info("## userPersisted, userName: " + userPersisted.getName());

        val allUsersFromRepository = userRepository.findAll();

        Assertions.assertEquals(2, allUsersFromRepository.size());
        Assertions.assertEquals(userDataMocked.getName(), allUsersFromRepository.get(0).getName());
    }

    @Test
    @DisplayName("This test should return user data by params: name, email or phone")
    public void findUserByParam() {
        val userDataMocked = new UsersMocked().returnUserDataMocked();

        val userPersisted = testEntityManager.persist(userDataMocked);
        String userName = userPersisted.getName();
        String userEmail = userPersisted.getEmail();
        String userPhone = userPersisted.getPhone();

        val userByName = userRepository.findByName(userName);
        val userByEmail = userRepository.findByEmail(userEmail);
        val userByPhone = userRepository.findByPhone(userPhone);

        Assertions.assertEquals(userDataMocked.getName(), userByName.getName());
        Assertions.assertEquals(userDataMocked, userByName);

        Assertions.assertEquals(userDataMocked.getEmail(), userByName.getEmail());
        Assertions.assertEquals(userDataMocked, userByEmail);

        Assertions.assertEquals(userDataMocked.getPhone(), userByName.getPhone());
        Assertions.assertEquals(userDataMocked, userByPhone);
    }

    @Test
    @DisplayName("This test should save user data")
    public void saveUserData() {
        val userDataMocked = new UsersMocked().returnUserDataMocked();

        val userPersisted = testEntityManager.persist(userDataMocked);

        val userSaved = userRepository.save(userPersisted);

        Assertions.assertEquals(userDataMocked, userSaved);
    }

    @Test
    @DisplayName("This test should update user data")
    public void updateUserData() {
        val userDataMocked = new UsersMocked().returnUserDataMocked();

        val userPersisted = testEntityManager.persist(userDataMocked);

        String newUserName = "Luis";
        String newUserEmail = "luis@gmail.com.br";
        String newUserPassw = "Luis1234#@";
        String newUserPhone = "5551555554444";

        userPersisted.setName(newUserName);
        userPersisted.setEmail(newUserEmail);
        userPersisted.setPassword(newUserPassw);
        userPersisted.setPhone(newUserPhone);

        val userUpdated = userRepository.save(userPersisted);

        Assertions.assertEquals(newUserName, userUpdated.getName());
        Assertions.assertEquals(newUserEmail, userUpdated.getEmail());
        Assertions.assertEquals(newUserPassw, userUpdated.getPassword());
        Assertions.assertEquals(newUserPhone, userUpdated.getPhone());
    }

    @Test
    @DisplayName("This test should delete user data by userId")
    public void deleteUserData() {
        val userDataMocked = new UsersMocked().returnUserDataMocked();

        val userPersisted = testEntityManager.persist(userDataMocked);
        UUID userId = userPersisted.getId();

        userRepository.deleteById(userId);

        val user = userRepository.findById(userId);

        Assertions.assertTrue(user.isEmpty());
    }
}
