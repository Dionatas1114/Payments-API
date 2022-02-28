package com.api.payments.services;

import com.api.payments.model.UserModel;
import com.api.payments.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.api.payments.validations.UserValidator.userValidator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUserData(UserModel userData) throws Exception {

        String userName = userData.getName ();
        String email = userData.getEmail ();
        String password = userData.getPassword ();

        userValidator (userName, email, password);

        userRepository.save (userData);
    }
}
