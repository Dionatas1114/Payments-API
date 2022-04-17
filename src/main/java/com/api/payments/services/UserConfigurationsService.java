package com.api.payments.services;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.repository.UserConfigurationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConfigurationsService {

    @Autowired
    private UserConfigurationsRepository userConfigurationsRepository;

    public List<UserConfigurationsDto> findUserConfigurations(){
        List<UserConfigurations> allUserConfigurations = userConfigurationsRepository.findAll();
        List<UserConfigurationsDto> userConfigurationsDtoList = new ArrayList<>();

        for (UserConfigurations userConfigurations : allUserConfigurations) {
            userConfigurationsDtoList.add(convertToDto(userConfigurations));
        }
        return userConfigurationsDtoList;
    }

    private UserConfigurationsDto convertToDto(UserConfigurations userConfigurations) {
        UserConfigurationsDto userConfigurationsDto = new UserConfigurationsDto();

        userConfigurationsDto.setHasNotifications(userConfigurations.hasNotifications);
        userConfigurationsDto.setId(userConfigurations.getUser().getId());
        userConfigurationsDto.setName(userConfigurations.getUser().getName());
        return userConfigurationsDto;
    }
}
