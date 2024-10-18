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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    public void testFindAllUsers_NotFound() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(Exception.class, () -> userServiceImpl.findAllUsers());
    }

    @Test
    public void testFindUserById_Success() throws Exception {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UsersDto result = userServiceImpl.findUserById(userId);

        verify(userRepository, times(1)).findById(userId); // Verifica se o repositório foi chamado corretamente
        assertNotNull(result);
        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getName(), result.getName());
        assertEquals(userId, result.getId());
    }

    @Test
    public void testFindUserById_NotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userServiceImpl.findUserById(userId));
    }

    @Test
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
    public void saveUserData_UserAlreadyExists() {
        when(userRepository.findByNameOrEmailOrPhone(userDto.getName(), userDto.getEmail(), userDto.getPhone()))
                .thenReturn(Optional.of(user));

        assertThrows(ServiceException.class, () -> userServiceImpl.saveUserData(userDto));
    }

    @Test
    public void saveUserData_UserNameIsInvalid() {
        userDto.setName("inval");
        when(userRepository.findByNameOrEmailOrPhone(userDto.getName(), userDto.getEmail(), userDto.getPhone()))
                .thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> userServiceImpl.saveUserData(userDto));
    }

    @Test
    public void saveUserData_EmailIsInvalid() {
        userDto.setEmail("invalidEmail");
        when(userRepository.findByNameOrEmailOrPhone(userDto.getName(), userDto.getEmail(), userDto.getPhone()))
                .thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> userServiceImpl.saveUserData(userDto));
    }

    @Test
    public void saveUserData_PhoneNumberIsInvalid() {
        userDto.setPhone("invalidPhone");
        when(userRepository.findByNameOrEmailOrPhone(userDto.getName(), userDto.getEmail(), userDto.getPhone()))
                .thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> userServiceImpl.saveUserData(userDto));
    }

    @Test
    public void updateUserData() {
    }

    @Test
    public void deleteUserData() {
    }
}
