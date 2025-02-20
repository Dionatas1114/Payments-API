package com.api.payments.repositories;

import com.api.payments.entity.Users;
import com.api.payments.mocks.UsersMocked;
import com.api.payments.repository.UserRepository;
import com.api.payments.utils.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    private boolean shouldSave = true;
    private Users user;
    private UUID userId;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();

        if (shouldSave) {
            Users userMocked = new UsersMocked().returnUserMocked();

            Users userPersisted = userRepository.save(userMocked);
            Log.info("userId: " + userPersisted.getId().toString());
            userId = userPersisted.getId();
            user = userPersisted;
        }
    }

    @Test
    @DisplayName("This test should return all user data")
    public void findAllUsers() {
        List<Users> usersList = userRepository.findAll();

        assertEquals(1, usersList.size());
        assertEquals(user.getName(), usersList.get(0).getName());
        assertEquals(user.getEmail(), usersList.get(0).getEmail());
        assertEquals(user.getPassword(), usersList.get(0).getPassword());
        assertEquals(user.getPhone(), usersList.get(0).getPhone());
    }

    @Test
    @DisplayName("This test should return user data by name")
    public void testFindByName() {
        Optional<Users> userOptional = userRepository.findByName(user.getName());

        assertTrue(userOptional.isPresent());
        assertEquals(user.getName(), userOptional.get().getName());
    }

    @Test
    @DisplayName("This test should return user data by email")
    public void testFindByEmail() {
        Optional<Users> userOptional = userRepository.findByEmail(user.getEmail());

        assertTrue(userOptional.isPresent());
        assertEquals(user.getEmail(), userOptional.get().getEmail());
    }

    @Test
    @DisplayName("This test should return user data by phone")
    public void testFindByPhone() {
        Optional<Users> userOptional = userRepository.findByPhone(user.getPhone());

        assertTrue(userOptional.isPresent());
        assertEquals(user.getPhone(), userOptional.get().getPhone());
    }

    @Test
    @DisplayName("This test should return user data by params: name, email or phone")
    public void findByNameOrEmailOrPhone() {
        Optional<Users> userOptional = userRepository.findByNameOrEmailOrPhone(
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );

        assertTrue(userOptional.isPresent());
        assertEquals(user.getName(), userOptional.get().getName());
        assertEquals(user.getEmail(), userOptional.get().getEmail());
        assertEquals(user.getPhone(), userOptional.get().getPhone());
    }

    @Test
    @DisplayName("This test should save user data")
    public void saveUserData() {
        shouldSave = false;
        Users userPersisted = userRepository.save(user);

        assertEquals(user.getName(), userPersisted.getName());
        assertEquals(user.getEmail(), userPersisted.getEmail());
        assertEquals(user.getPassword(), userPersisted.getPassword());
        assertEquals(user.getPhone(), userPersisted.getPhone());
    }

    @Test
    @DisplayName("This test should update user data")
    public void updateUserData() {
        user.setName("Luis");
        user.setEmail("luis@gmail.com.br");
        user.setPassword("Luis1234#@");
        user.setPhone("5551555554444");

        Users userPersisted = userRepository.save(user);

        assertNotNull(userPersisted);
        assertEquals("Luis", userPersisted.getName());
        assertEquals("luis@gmail.com.br", userPersisted.getEmail());
        assertEquals("Luis1234#@", userPersisted.getPassword());
        assertEquals("5551555554444", userPersisted.getPhone());
        assertEquals(userId, userPersisted.getId());
    }

    @Test
    @DisplayName("This test should delete user data")
    public void deleteUserData() {
        Optional<Users> user = userRepository.findById(userId);
        assertTrue(user.isPresent());

        userRepository.delete(user.get());

        Optional<Users> user2 = userRepository.findById(userId);
        assertTrue(user2.isEmpty());
    }

    @Test
    @DisplayName("This test should delete user data by id")
    public void deleteUserDataById() {
        userRepository.deleteById(userId);
        Optional<Users> user = userRepository.findById(userId);
        assertTrue(user.isEmpty());
    }
}
