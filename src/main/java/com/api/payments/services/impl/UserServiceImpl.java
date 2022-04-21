package com.api.payments.services.impl;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.dto.UsersDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.entity.Users;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserService;
import lombok.AllArgsConstructor;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

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

        if (userRepository.count() == 0) throw new RepositoryException(usersEmpty);

        List<Users> usersList = userRepository.findAll();
        List<UsersDto> usersDtoList = new ArrayList<>();

        for(Users user : usersList) usersDtoList.add(convertToDto(user));

        return usersDtoList;
    }

    @Override
    public UsersDto findOneUser(UUID userId) throws RepositoryException {

        Optional<Users> userFind = userRepository.findById(userId);
        if (userFind.isEmpty()) throw new RepositoryException(userNotFound);

        return convertOptionalToDto(userFind);
    }

    @Override
    public void saveUserData(UsersDto userDto) throws Exception {

        String userName = userDto.getName ();
        String email = userDto.getEmail ();
        String password = userDto.getPassword ();

        if (userRepository.count() != 0) {

            Users byName = userRepository.findByName(userName);
            boolean userNameAlreadyExists = Objects.equals(byName.name, userName);

            Users byEmail = userRepository.findByEmail(email);
            boolean userEmailAlreadyExists = Objects.equals(byEmail.email, email);

            if (userNameAlreadyExists) throw new RepositoryException(userNameAlreadyRegistered);
            if (userEmailAlreadyExists) throw new RepositoryException(userEmailAlreadyRegistered);
        }

        userValidator (userName, email, password);

        try {
            Users users = new Users();

            users.setName(userName);
            users.setEmail(email);
            users.setPassword(password);

            Users userDataSaved = userRepository.save(users);

            UserConfigurations userConfigurations = new UserConfigurations();
            userConfigurations.setHasNotifications(userDto.getUserConfigurations().hasNotifications);
            userConfigurations.setUser(userDataSaved);
            userConfigurationsRepository.save(userConfigurations);

        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void updateUserData(UsersDto userDto, UUID userId) throws Exception {
        UserConfigurations userConfigurations = new UserConfigurations();

        String userName = userDto.getName ();
        String email = userDto.getEmail ();
        String password = userDto.getPassword ();
        boolean hasNotifications = userDto.getUserConfigurations().hasNotifications;

        Optional<Users> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new RepositoryException(userNotFound);

        List<Users> usersList = userRepository.findAll();

        for(Users users : usersList) {

            if (Objects.equals(users.getName(), userName) && users.getId() != userId)
                throw new ExceptionInInitializerError(userNameAlreadyRegistered);

            if (Objects.equals(users.getEmail(), email) && users.getId() != userId)
                throw new ExceptionInInitializerError(userNameAlreadyRegistered);
        }

        userValidator (userName, email, password);

        try{
            UserConfigurations userConfiguration = userConfigurationsRepository.findByUserId(userId);
            Users users = new Users();

            users.setId(userId);
            users.setName(userName);
            users.setEmail(email);
            users.setPassword(password);

            userRepository.save(users);

            UUID userConfigurationId = userConfiguration.getId();
            userConfigurations.setId(userConfigurationId);
            userConfigurations.setHasNotifications(hasNotifications);
            userConfigurations.setUser(users);

            userConfigurationsRepository.save(userConfigurations);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteUserData(UUID userId) throws RepositoryException {

        boolean exists = userRepository.existsById(userId);
        if (!exists) throw new RepositoryException(userNotFound);

        userRepository.deleteById(userId);
    }

    private UsersDto convertToDto(Users user) {
        UsersDto usersDto = new UsersDto();
        UserConfigurationsDto userConfigurationsDto = new UserConfigurationsDto();

        usersDto.setId(user.getId());
        usersDto.setName(user.getName());
        usersDto.setEmail(user.getEmail());
        usersDto.setPassword(user.getPassword());
        userConfigurationsDto.setHasNotifications(user.getUserConfigurations().hasNotifications);
        usersDto.setUserConfigurations(userConfigurationsDto);

        return usersDto;
    }

    private UsersDto convertOptionalToDto(Optional<Users> user) {
        UsersDto usersDto = new UsersDto();
        UserConfigurationsDto userConfigurationsDto = new UserConfigurationsDto();

        usersDto.setId(user.get().getId());
        usersDto.setName(user.get().getName());
        usersDto.setEmail(user.get().getEmail());
        usersDto.setPassword(user.get().getPassword());
        userConfigurationsDto.setHasNotifications(user.get().getUserConfigurations().hasNotifications);
        usersDto.setUserConfigurations(userConfigurationsDto);

        return usersDto;
    }
}
