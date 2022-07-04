package com.api.payments.services.impl;

import com.api.payments.dto.UsersDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.entity.Users;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.api.payments.messages.UserMessages.*;
import static com.api.payments.validations.UserValidator.userValidator;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserConfigurationsRepository userConfigurationsRepository;

    @Override
    public List<UsersDto> findAllUsers() throws RepositoryException {

        List<UsersDto> usersDtoList = new ArrayList<>();
        List<Users> usersList = userRepository.findAll();

        boolean usersListEmpty = usersList.isEmpty();
        if (usersListEmpty)
            throw new RepositoryException(usersEmpty);

        for (Users user : usersList)
            usersDtoList.add(convertToDto(user));

        return usersDtoList;
    }

    @Override
    public UsersDto findUserById(UUID userId) throws RepositoryException {

        Optional<Users> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new RepositoryException(userNotFound);

        return convertToDto(user.get());
    }

    @Transactional
    @Override
    public void saveUserData(UsersDto userDto) throws Exception {

        String userName = userDto.getName();
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        if (userRepository.count() != 0) {

            Users byName = userRepository.findByName(userName);
            Users byEmail = userRepository.findByEmail(email);

            if (byName != null) {
                boolean userNameAlreadyExists =
                        Objects.equals(byName.userName, userName);
                if (userNameAlreadyExists)
                    throw new ServiceException(userNameAlreadyRegistered);
            }

            if (byEmail != null) {
                boolean userEmailAlreadyExists =
                        Objects.equals(byEmail.email, email);
                if (userEmailAlreadyExists)
                    throw new ServiceException(userEmailAlreadyRegistered);
            }
        }

        userValidator(userName, email, password);

        try {
            Users users = new Users();

            users.setUserName(userName);
            users.setEmail(email);
            users.setPassword(password);

            Users userDataSaved = userRepository.save(users);

            UserConfigurations userConfigurations = new UserConfigurations();
            userConfigurations.setHasNotifications(userDto.getUserConfigurations().hasNotifications);
            userConfigurations.setUser(userDataSaved);
            userConfigurationsRepository.save(userConfigurations);

        } catch (
                Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void updateUserData(UsersDto userDto, UUID userId) throws Exception {
        UserConfigurations userConfigurations = new UserConfigurations();

        String userName = userDto.getName();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        boolean hasNotifications = userDto.getUserConfigurations().hasNotifications;

        Optional<Users> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new RepositoryException(userNotFound);

        List<Users> usersList = userRepository.findAll();

        if (!usersList.isEmpty()) {
            for (Users users : usersList) {

                if (Objects.equals(users.getUserName(), userName) && users.getId() != userId)
                    throw new ServiceException(userNameAlreadyRegistered);

                if (Objects.equals(users.getEmail(), email) && users.getId() != userId)
                    throw new ServiceException(userNameAlreadyRegistered);
            }
        }

        userValidator(userName, email, password);

        try {
            UserConfigurations userConfiguration = userConfigurationsRepository.findByUserId(userId);
            Users users = new Users();

            users.setId(userId);
            users.setUserName(userName);
            users.setEmail(email);
            users.setPassword(password);

            userRepository.save(users);

            UUID userConfigurationId = userConfiguration.getId();
            userConfigurations.setId(userConfigurationId);
            userConfigurations.setHasNotifications(hasNotifications);
            userConfigurations.setUser(users);

            userConfigurationsRepository.save(userConfigurations);
        } catch (
                Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void deleteUserData(UUID userId) throws Exception {

        boolean exists = userRepository.existsById(userId);
        if (!exists)
            throw new RepositoryException(userNotFound);

        userRepository.deleteById(userId);
    }

    private UsersDto convertToDto(Users user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UsersDto.class);
    }
}
