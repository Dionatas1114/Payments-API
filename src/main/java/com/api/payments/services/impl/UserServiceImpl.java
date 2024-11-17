package com.api.payments.services.impl;

import com.api.payments.dto.UsersDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.entity.Users;
import com.api.payments.interceptor.TokenInterceptor;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.repository.UserRepository;
import com.api.payments.services.UserService;
import com.api.payments.validations.validators.UserValidator;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.api.payments.messages.UserMessages.noUserDataRegistered;
import static com.api.payments.messages.UserMessages.userDataNotFound;
import static com.api.payments.messages.UserMessages.userEmailAlreadyRegistered;
import static com.api.payments.messages.UserMessages.userNameAlreadyRegistered;
import static com.api.payments.messages.UserMessages.userPhoneAlreadyRegistered;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserConfigurationsRepository userConfigurationsRepository;
    private TokenInterceptor tokenInterceptor;

    @Override
    public List<UsersDto> findAllUsers() throws Exception {

        List<UsersDto> usersDtoList = new ArrayList<>();

        Optional.of(userRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new Exception(noUserDataRegistered))
                .forEach(user -> usersDtoList.add(convertToDto(user)));

        return usersDtoList;
    }

    @Override
    public UsersDto findUserById(UUID userId) throws Exception {

        Users user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userDataNotFound));
        return convertToDto(user);
    }

    @Transactional
    @Override
    public void saveUserData(UsersDto userDto) {

        String userName = userDto.getName();
        String email = userDto.getEmail();
        String phone = userDto.getPhone();
        String password = userDto.getPassword();

        // Verifica se o usuário já existe por nome, e-mail ou telefone
        userRepository.findByNameOrEmailOrPhone(userName, email, phone)
                .ifPresent(user -> {
                    if (userName.equals(user.getName())) throw new ServiceException(userNameAlreadyRegistered);
                    if (email.equals(user.getEmail())) throw new ServiceException(userEmailAlreadyRegistered);
                    if (phone.equals(user.getPhone())) throw new ServiceException(userPhoneAlreadyRegistered);
                });

        UserValidator.userValidator(userName, email, password, phone);

        String passwordEncoded = tokenInterceptor.passwordEncoder().encode(password);

        Users users = new Users();
        users.setName(userName);
        users.setEmail(email);
        users.setPassword(passwordEncoded);
        users.setPhone(phone);

        Users userDataSaved = userRepository.save(users);

        UserConfigurations userConfigurations = new UserConfigurations();
        userConfigurations.setHasNotifications(userDto.getUserConfigurations().isHasNotifications());
        userConfigurations.setLanguage(userDto.getUserConfigurations().getLanguage());
        userConfigurations.setUser(userDataSaved);

        userConfigurationsRepository.save(userConfigurations);
    }

    @Transactional
    @Override
    public void updateUserData(UUID userId, UsersDto userDto) throws Exception {

        Users users = new Users();
        UserConfigurations userConfigurations = new UserConfigurations();

        userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userDataNotFound));

        String userName = userDto.getName();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String phone = userDto.getPhone();
        boolean hasNotifications = userDto.getUserConfigurations().isHasNotifications();
        String language = userDto.getUserConfigurations().getLanguage();

        UserValidator.userValidator(userName, email, password, phone);

        userRepository.findByEmail(email)
                .ifPresent(existingUser -> {
                    if (!existingUser.getId().equals(userId)) {
                        throw new ServiceException(userEmailAlreadyRegistered);
                    }
                });

        userRepository.findByPhone(phone)
                .ifPresent(existingUser -> {
                    if (!existingUser.getId().equals(userId)) {
                        throw new ServiceException(userPhoneAlreadyRegistered);
                    }
                });

        UserConfigurations userConfiguration = userConfigurationsRepository
                .findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(userDataNotFound));

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
    }

    @Transactional
    @Override
    public void deleteUserData(UUID userId) throws Exception {

        userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userDataNotFound));
        UserConfigurations userConfigurations = userConfigurationsRepository
                .findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(userDataNotFound));

        userRepository.deleteById(userId);
        userConfigurationsRepository.delete(userConfigurations);
    }

    private UsersDto convertToDto(Users user) {
        return new ModelMapper().map(user, UsersDto.class);
    }
}
