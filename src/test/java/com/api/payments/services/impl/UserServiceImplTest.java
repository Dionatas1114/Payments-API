package com.api.payments.services.impl;

import com.api.payments.dto.UsersDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.entity.Users;
import com.api.payments.interceptor.TokenInterceptor;
import com.api.payments.mocks.UsersMocked;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.repository.UserRepository;
import javassist.NotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.api.payments.messages.UserMessages.noUserDataRegistered;
import static com.api.payments.messages.UserMessages.userDataNotFound;
import static com.api.payments.messages.UserMessages.userEmailAlreadyRegistered;
import static com.api.payments.messages.UserMessages.userNameAlreadyRegistered;
import static com.api.payments.messages.UserMessages.userPhoneAlreadyRegistered;
import static com.api.payments.validations.messages.UserValidatorMessages.userEmailInvalid;
import static com.api.payments.validations.messages.UserValidatorMessages.userNameInvalid;
import static com.api.payments.validations.messages.UserValidatorMessages.userPhoneInvalid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl; // Classe a ser testada

    @Mock
    private UserRepository userRepository; // Dependencia mockada da classe a ser testada

    @Mock
    private UserConfigurationsRepository userConfigurationsRepository; // Dependencia mockada da classe a ser testada

    @Mock
    private TokenInterceptor tokenInterceptor; // Dependencia mockada da classe a ser testada

    @Mock
    private PasswordEncoder passwordEncoder; // Dependencia mockada da classe a ser testada

    private UUID userId;
    private Users user;
    private UsersDto userDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Users userDataMocked = new UsersMocked().returnUserMocked();
        userId = userDataMocked.getId();
        user = userDataMocked;

        userDto = new ModelMapper().map(user, UsersDto.class);
    }

    @Test
    @DisplayName("This test should return all user data")
    public void testFindAllUsers_Success() throws Exception {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<UsersDto> allUsers = userServiceImpl.findAllUsers();

        assertEquals(1, allUsers.size());
    }

    @Test
    @DisplayName("This test should return error when users not found")
    public void testFindAllUsers_NotFound() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        Exception e = assertThrows(Exception.class, () -> userServiceImpl.findAllUsers());
        assertEquals(noUserDataRegistered, e.getMessage());
    }

    @Test
    @DisplayName("This test should return user data by id")
    public void testFindUserById_Success() throws Exception {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UsersDto result = userServiceImpl.findUserById(userId);

        verify(userRepository, times(1)).findById(userId);
        assertNotNull(result);
        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getName(), result.getName());
        assertEquals(userId, result.getId());
        assertEquals(result, userDto);
    }

    @Test
    @DisplayName("This test should return error when user not found")
    public void testFindUserById_NotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        NotFoundException e = assertThrows(NotFoundException.class, () -> userServiceImpl.findUserById(userId));
        assertEquals(userDataNotFound, e.getMessage());
    }

    @Test
    @DisplayName("This test should save user data")
    public void saveUserData_Success() {
        Users userDataMocked = new UsersMocked().returnUserMocked();
        userDto.setPassword(userDataMocked.getPassword());
        user.setPassword("encodedPassword");

        when(tokenInterceptor.passwordEncoder()).thenReturn(passwordEncoder);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        doReturn(user).when(userRepository).save(any(Users.class));
        doReturn(user.getUserConfigurations()).when(userConfigurationsRepository).save(any(UserConfigurations.class));

        userServiceImpl.saveUserData(userDto);

        verify(passwordEncoder).encode(userDataMocked.getPassword());
        verify(userRepository).save(any(Users.class));
        verify(userConfigurationsRepository).save(any(UserConfigurations.class));

        assertEquals(userDto.getId(), user.getId());
    }

    @Test
    @DisplayName("This test should return error when user already exists")
    public void saveUserData_UserAlreadyExists() {
        when(userRepository.findByNameOrEmailOrPhone(userDto.getName(), userDto.getEmail(), userDto.getPhone()))
                .thenReturn(Optional.of(user));

        ServiceException e = assertThrows(ServiceException.class, () -> userServiceImpl.saveUserData(userDto));
        assertEquals(userNameAlreadyRegistered, e.getMessage());
    }

    @Test
    @DisplayName("This test should return error when user name is invalid")
    public void saveUserData_UserNameIsInvalid() {
        userDto.setName("inval");
        when(userRepository.findByNameOrEmailOrPhone(userDto.getName(), userDto.getEmail(), userDto.getPhone()))
                .thenReturn(Optional.empty());

        ServiceException e = assertThrows(ServiceException.class, () -> userServiceImpl.saveUserData(userDto));
        assertEquals(userNameInvalid, e.getMessage());
    }

    @Test
    @DisplayName("This test should return error when user email is invalid")
    public void saveUserData_EmailIsInvalid() {
        userDto.setEmail("invalidEmail");
        when(userRepository.findByNameOrEmailOrPhone(userDto.getName(), userDto.getEmail(), userDto.getPhone()))
                .thenReturn(Optional.empty());

        ServiceException e = assertThrows(ServiceException.class, () -> userServiceImpl.saveUserData(userDto));
        assertEquals(userEmailInvalid, e.getMessage());
    }

    @Test
    @DisplayName("This test should return error when user phone is invalid")
    public void saveUserData_PhoneNumberIsInvalid() {
        userDto.setPhone("invalidPhone");
        when(userRepository.findByNameOrEmailOrPhone(userDto.getName(), userDto.getEmail(), userDto.getPhone()))
                .thenReturn(Optional.empty());

        ServiceException e = assertThrows(ServiceException.class, () -> userServiceImpl.saveUserData(userDto));
        assertEquals(userPhoneInvalid, e.getMessage());
    }

    @Test
    @DisplayName("This test should update user data")
    public void updateUserData_Success() throws Exception {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByPhone(userDto.getPhone())).thenReturn(Optional.empty());
        when(userConfigurationsRepository.findByUserId(userId)).thenReturn(Optional.of(user.getUserConfigurations()));

        userServiceImpl.updateUserData(userId, userDto);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).findByEmail(userDto.getEmail());
        verify(userRepository, times(1)).findByPhone(userDto.getPhone());
        verify(userConfigurationsRepository, times(1)).findByUserId(userId);
        verify(userRepository, times(1)).save(any(Users.class));
        verify(userConfigurationsRepository, times(1)).save(any(UserConfigurations.class));
    }

    @Test
    @DisplayName("This test should return error when user not found")
    public void updateUserData_UserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        NotFoundException e = assertThrows(NotFoundException.class,
                () -> userServiceImpl.updateUserData(userId, userDto));

        assertEquals(userDataNotFound, e.getMessage());
    }

    @Test
    @DisplayName("This test should throw an exception when user email already exists")
    public void updateUserData_UserEmailAlreadyExists() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Users userWithSameEmail = user;
        userWithSameEmail.setId(UUID.fromString("2dd6279f-d37a-4f5f-b48b-2254fea7f90d"));
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(userWithSameEmail));

        ServiceException e = assertThrows(ServiceException.class,
                () -> userServiceImpl.updateUserData(userId, userDto));

        assertEquals(userEmailAlreadyRegistered, e.getMessage());
    }

    @Test
    @DisplayName("This test should throw an exception when user phone already exists")
    public void updateUserData_UserPhoneAlreadyExists() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Users userWithSameEmail = user;
        userWithSameEmail.setId(UUID.fromString("2dd6279f-d37a-4f5f-b48b-2254fea7f90d"));
        when(userRepository.findByPhone(userDto.getPhone())).thenReturn(Optional.of(userWithSameEmail));

        ServiceException e = assertThrows(ServiceException.class,
                () -> userServiceImpl.updateUserData(userId, userDto));

        assertEquals(userPhoneAlreadyRegistered, e.getMessage());
    }

    @Test
    @DisplayName("This test should delete user data")
    public void deleteUserData_Success() throws Exception {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userConfigurationsRepository
                .findByUserId(userId))
                .thenReturn(Optional.of(user.getUserConfigurations()));

        userServiceImpl.deleteUserData(userId);

        verify(userRepository, times(1)).deleteById(userId);
        verify(userConfigurationsRepository, times(1)).delete(user.getUserConfigurations());
    }

    @Test
    @DisplayName("This test should return error when user not found")
    public void deleteUserData_UserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        NotFoundException e = assertThrows(NotFoundException.class,
                () -> userServiceImpl.deleteUserData(userId));

        assertEquals(userDataNotFound, e.getMessage());
    }

    @Test
    @DisplayName("This test should return error when user configurations not found")
    public void deleteUserData_UserConfigurationsNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userConfigurationsRepository.findByUserId(userId)).thenReturn(Optional.empty());

        NotFoundException e = assertThrows(NotFoundException.class,
                () -> userServiceImpl.deleteUserData(userId));

        assertEquals(userDataNotFound, e.getMessage());
    }
}
