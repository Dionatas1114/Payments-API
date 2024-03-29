package com.api.payments.services.impl;

import com.api.payments.config.SecurityConfig;
import com.api.payments.dto.UsersDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.entity.Users;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.api.payments.messages.UserMessages.*;
import static com.api.payments.validations.validators.UserValidator.userValidator;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserConfigurationsRepository userConfigurationsRepository;
    private SecurityConfig securityConfig;

    @Override
    public List<UsersDto> findAllUsers() throws ExceptionInInitializerError {

        val usersList = userRepository.findAll();
        if (usersList.isEmpty()) throw new ExceptionInInitializerError(noUserDataRegistered);

        List<UsersDto> usersDtoList = new ArrayList<>();
        usersList.forEach(user -> usersDtoList.add(convertToDto(user)));
        return usersDtoList;
    }

    @Override
    public UsersDto findUserById(UUID userId) throws ExceptionInInitializerError {

        val user = userRepository.findById(userId);
        return convertToDto(user.orElseThrow(() -> new ExceptionInInitializerError(userDataNotFound)));
    }

    @Transactional
    @Override
    public void saveUserData(UsersDto userDto) {

        String userName = userDto.getName();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String phone = userDto.getPhone();

        if (userRepository.count() != 0) {
            Users byName = userRepository.findByName(userName);
            Users byEmail = userRepository.findByEmail(email);
            Users byPhone = userRepository.findByPhone(phone);

            if (byName != null) {
                boolean userNameAlreadyExists = Objects.equals(byName.name, userName);
                if (userNameAlreadyExists) throw new ServiceException(userNameAlreadyRegistered);
            }

            if (byEmail != null) {
                boolean userEmailAlreadyExists = Objects.equals(byEmail.email, email);
                if (userEmailAlreadyExists) throw new ServiceException(userEmailAlreadyRegistered);
            }

            if (byPhone != null) {
                boolean userPhoneAlreadyExists = Objects.equals(byPhone.phone, phone);
                if (userPhoneAlreadyExists) throw new ServiceException(userPhoneAlreadyRegistered);
            }
        }

        userValidator(userName, email, password, phone);

        String passwordEncoded = securityConfig.passwordEncoder().encode(password);

        try {
            val users = new Users();

            users.setName(userName);
            users.setEmail(email);
            users.setPassword(passwordEncoded);
            users.setPhone(phone);

            val userDataSaved = userRepository.save(users);

            val userConfigurations = new UserConfigurations();
            userConfigurations.setHasNotifications(userDto.getUserConfigurations().isHasNotifications());
            userConfigurations.setLanguage(userDto.getUserConfigurations().getLanguage());
            userConfigurations.setUser(userDataSaved);
            userConfigurationsRepository.save(userConfigurations);

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void updateUserData(UsersDto userDto, UUID userId) throws Exception {
        val userConfigurations = new UserConfigurations();

        String userName = userDto.getName();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String phone = userDto.getPhone();
        boolean hasNotifications = userDto.getUserConfigurations().isHasNotifications();
        String language = userDto.getUserConfigurations().getLanguage();

        userRepository.findById(userId).orElseThrow(() -> new ExceptionInInitializerError(userDataNotFound));

        val usersList = userRepository.findAll();

        if (!usersList.isEmpty()) {
            for (val users : usersList) {
                if (Objects.equals(users.getName(), userName) && users.getId() != userId)
                    throw new ServiceException(userNameAlreadyRegistered);

                if (Objects.equals(users.getEmail(), email) && users.getId() != userId)
                    throw new ServiceException(userEmailAlreadyRegistered);

                if (Objects.equals(users.getPhone(), phone) && users.getId() != userId)
                    throw new ServiceException(userPhoneAlreadyRegistered);
            }
        }

        userValidator(userName, email, password, phone);

        try {
            val userConfiguration = userConfigurationsRepository.findByUserId(userId);
            val users = new Users();

            users.setId(userId);
            users.setName(userName);
            users.setEmail(email);
            users.setPassword(password);
            users.setPhone(phone);

            userRepository.save(users);

            UUID userConfigurationId = userConfiguration.getId();
            userConfigurations.setId(userConfigurationId);
            userConfigurations.setHasNotifications(hasNotifications);
            userConfigurations.setLanguage(language);
            userConfigurations.setUser(users);

            userConfigurationsRepository.save(userConfigurations);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void deleteUserData(UUID userId) {

        boolean exists = userRepository.existsById(userId);
        if (!exists) throw new ExceptionInInitializerError(userDataNotFound);
        userRepository.deleteById(userId);
    }

    private UsersDto convertToDto(Users user) {
        return new ModelMapper().map(user, UsersDto.class);
    }
}
