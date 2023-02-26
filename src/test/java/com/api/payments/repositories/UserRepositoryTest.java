package com.api.payments.repositories;

import com.api.payments.mocks.UsersMocked;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private com.api.payments.repository.UserRepository userRepository;

    @Test
    public void findAllUsers() {

        var user = new UsersMocked().returnUserDataMocked();

        var user1 = testEntityManager.persist(user);

        var usersListFromRepository = userRepository.findAll();

        Assertions.assertEquals(1, usersListFromRepository.size());
    }

    @Test
    public void findUserById() {
    }

    @Test
    public void saveUserData() {
    }

    @Test
    public void updateUserData() {
    }

    @Test
    public void deleteUserData() {
    }
}
