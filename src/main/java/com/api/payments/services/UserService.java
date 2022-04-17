package com.api.payments.services;

import com.api.payments.dto.UsersDto;
import com.api.payments.entity.*;
import com.api.payments.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import static com.api.payments.messages.UserMessages.*;
import static com.api.payments.validations.UserValidator.userValidator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConfigurationsRepository userConfigurationsRepository;

    public List<UsersDto> findAllUsers(){
        List<Users> usersList = userRepository.findAll();
        List<UsersDto> usersDtoList = new ArrayList<>();

        for(Users user : usersList) {
            usersDtoList.add(convertToDto(user));
        }
        return usersDtoList;
    }

    public void saveUserData(Users usersData) throws Exception {

        String userName = usersData.getName ();
        String email = usersData.getEmail ();
        String password = usersData.getPassword ();

//        if (userRepository.count() != 0) {

//            Users usersModel = userRepository.findByName(userName);
//            boolean userNameAlreadyExists = Objects.equals(usersModel.name, userName);
//
//            Users users_model = userRepository.findByEmail(email);
//            boolean userEmailAlreadyExists = Objects.equals(users_model.email, email);
//
//            if (userNameAlreadyExists || userEmailAlreadyExists) {
//                throw new Exception();
//            }
//        }

        userValidator (userName, email, password);
        userRepository.save (usersData);

        UserConfigurations userConfigurations = new UserConfigurations();
        userConfigurations.setUser(usersData);
        userConfigurationsRepository.save(userConfigurations);
    }

    public void updateUserData(Users usersData, UUID userId) throws Exception {

        UserConfigurations userConfigurations = new UserConfigurations();

        boolean userNotExists = !userRepository.existsById(userId);

//        String name = usersData.getName ();
//        Users userByName = userRepository.findByName(name);
//        boolean userNameAlreadyExists = Objects.equals(userByName.name, name)
//                && userByName.getId() != userId;
//
//        String email = usersData.getEmail();
//        Users userByEmail = userRepository.findByEmail(email);
//        boolean userEmailAlreadyExists = Objects.equals(userByEmail.email, email)
//                && userByEmail.getId() != userId;

        if (userNotExists){
            throw new Exception(userNotFound);

//        } else if (userNameAlreadyExists || userEmailAlreadyExists){
//            throw new Exception(userAlreadyExists);

        } else {

//            UserConfigurations userConfiguration = userConfigurationsRepository.findByUserId(userId);

            usersData.setId(userId);
            userRepository.save (usersData);

//            UUID userConfigurationId = userConfiguration.getUser().getUserConfigurations().getId();
//            userConfigurations.setId(userConfigurationId);
//
//            userConfigurations.setUser(usersData);
//            userConfigurationsRepository.save(userConfigurations);
        }
    }

    private UsersDto convertToDto(Users user) {

        UsersDto usersDto = new UsersDto();

        usersDto.setName(user.getName());
        usersDto.setEmail(user.getEmail());
        usersDto.setUserConfigurations(user.userConfigurations);
        return usersDto;
    }
}
