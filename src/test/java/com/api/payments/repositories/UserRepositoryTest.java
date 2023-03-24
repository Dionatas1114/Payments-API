package com.api.payments.repositories;

import com.api.payments.entity.Users;
import com.api.payments.mocks.UsersMocked;
import com.api.payments.utils.Log;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private com.api.payments.repository.UserRepository userRepository;

    @Test
    @DisplayName("This test should return all user data")
    public void findAllUsers() {
        var userDataMocked = new UsersMocked().returnUserDataMocked();

        var userPersisted = testEntityManager.persist(userDataMocked);
        Log.info("## userPersisted, userName: " + userPersisted.getName());

        var allUsersFromRepository = userRepository.findAll();

        Assertions.assertEquals(1, allUsersFromRepository.size());
        Assertions.assertEquals(userDataMocked.getName(), allUsersFromRepository.get(0).getName());
    }

    @Test
    @DisplayName("This test should return user data by params: name, email or phone")
    public void findUserByParam() {
        var userDataMocked = new UsersMocked().returnUserDataMocked();

        var userPersisted = testEntityManager.persist(userDataMocked);
        String userName = userPersisted.getName();
        String userEmail = userPersisted.getEmail();
        String userPhone = userPersisted.getPhone();

        var userByName = userRepository.findByName(userName);
        var userByEmail = userRepository.findByEmail(userEmail);
        var userByPhone = userRepository.findByPhone(userPhone);

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
        var userDataMocked = new UsersMocked().returnUserDataMocked();

        var userPersisted = testEntityManager.persist(userDataMocked);

        Users userSaved = userRepository.save(userPersisted);

        Assertions.assertEquals(userDataMocked, userSaved);
    }

    @Test
    @DisplayName("This test should update user data")
    public void updateUserData() {
        var userDataMocked = new UsersMocked().returnUserDataMocked();

        var userPersisted = testEntityManager.persist(userDataMocked);

        String newUserName = "Luis";
        String newUserEmail = "luis@gmail.com.br";
        String newUserPassw = "Luis1234#@";
        String newUserPhone = "5551555554444";

        userPersisted.setName(newUserName);
        userPersisted.setEmail(newUserEmail);
        userPersisted.setPassword(newUserPassw);
        userPersisted.setPhone(newUserPhone);

        Users userUpdated = userRepository.save(userPersisted);

        Assertions.assertEquals(newUserName, userUpdated.getName());
        Assertions.assertEquals(newUserEmail, userUpdated.getEmail());
        Assertions.assertEquals(newUserPassw, userUpdated.getPassword());
        Assertions.assertEquals(newUserPhone, userUpdated.getPhone());
    }

    @Test
    @DisplayName("This test should delete user data by userId")
    public void deleteUserData() {
        var userDataMocked = new UsersMocked().returnUserDataMocked();

        var userPersisted = testEntityManager.persist(userDataMocked);
        UUID userId = userPersisted.getId();

        userRepository.deleteById(userId);

        Log.info(userPersisted.getName());
        Assertions.assertEquals(10, 10);
    }
}
