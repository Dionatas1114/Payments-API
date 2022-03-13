package com.api.payments.services;

import com.api.payments.model.UserConfigurations;
import com.api.payments.model.UserModel;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import static com.api.payments.validations.UserValidator.userValidator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConfigurationsRepository userConfigurationsRepository;

    public void saveUserData(UserModel userData) throws Exception {

        String userName = userData.getName ();
        String email = userData.getEmail ();
        String password = userData.getPassword ();

        if (userRepository.count() != 0) {

            UserModel userModel = userRepository.findByName(userName);
            boolean userNameAlreadyExists = Objects.equals(userModel.name, userName);

            UserModel user_model = userRepository.findByEmail(email);
            boolean userEmailAlreadyExists = Objects.equals(user_model.email, email);

            if (userNameAlreadyExists || userEmailAlreadyExists) {
                throw new Exception();
            }

            userValidator (userName, email, password);
        }

        userRepository.save (userData);

        UserConfigurations userConfigurations = new UserConfigurations();
        userConfigurations.setUserConfigurations(userData);
        userConfigurationsRepository.save(userConfigurations);
    }
}
