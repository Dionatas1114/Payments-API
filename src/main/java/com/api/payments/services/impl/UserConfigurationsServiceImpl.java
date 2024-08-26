package com.api.payments.services.impl;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.services.UserConfigurationsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.api.payments.messages.UserMessages.noUserDataRegistered;

@Service
@AllArgsConstructor
public class UserConfigurationsServiceImpl implements UserConfigurationsService {

    private UserConfigurationsRepository userConfigurationsRepository;

    @Override
    public List<UserConfigurationsDto> findUserConfigurations() throws Exception {

        List<UserConfigurationsDto> userConfigurationsDtoList = new ArrayList<>();

        Optional.of(userConfigurationsRepository.findAll())
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new Exception(noUserDataRegistered))
                .forEach(userConfiguration -> userConfigurationsDtoList.add(convertToDto(userConfiguration)));

        return userConfigurationsDtoList;
    }

    private UserConfigurationsDto convertToDto(UserConfigurations userConfigurations) {
        return new ModelMapper().map(userConfigurations, UserConfigurationsDto.class);
    }
}
