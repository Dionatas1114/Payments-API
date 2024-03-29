package com.api.payments.services.impl;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.services.UserConfigurationsService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.api.payments.messages.UserMessages.noUserDataRegistered;

@Service
@AllArgsConstructor
public class UserConfigurationsServiceImpl implements UserConfigurationsService {

    private UserConfigurationsRepository userConfigurationsRepository;

    @Override
    public List<UserConfigurationsDto> findUserConfigurations() {

        val allUserConfigurations = userConfigurationsRepository.findAll();
        if (allUserConfigurations.isEmpty()) throw new ExceptionInInitializerError(noUserDataRegistered);

        List<UserConfigurationsDto> userConfigurationsDtoList = new ArrayList<>();
        allUserConfigurations.forEach(userConfiguration -> userConfigurationsDtoList.add(convertToDto(userConfiguration)));
        return userConfigurationsDtoList;
    }

    private UserConfigurationsDto convertToDto(UserConfigurations userConfigurations) {
        return new ModelMapper().map(userConfigurations, UserConfigurationsDto.class);
    }
}
