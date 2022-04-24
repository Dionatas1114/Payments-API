package com.api.payments.services.impl;

import com.api.payments.dto.UserConfigurationsDto;
import com.api.payments.entity.UserConfigurations;
import com.api.payments.repository.UserConfigurationsRepository;
import com.api.payments.services.UserConfigurationsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.sonatype.aether.RepositoryException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.api.payments.messages.UserMessages.usersEmpty;

@Service
@AllArgsConstructor
public class UserConfigurationsServiceImpl implements UserConfigurationsService {

    private UserConfigurationsRepository userConfigurationsRepository;

    @Override
    public List<UserConfigurationsDto> findUserConfigurations() throws Exception {

        List<UserConfigurationsDto> userConfigurationsDtoList = new ArrayList<>();
        List<UserConfigurations> allUserConfigurations = userConfigurationsRepository.findAll();

        if (allUserConfigurations.size() == 0) throw new RepositoryException(usersEmpty);

        for (UserConfigurations userConfigurations : allUserConfigurations) {
            userConfigurationsDtoList.add(convertToDto(userConfigurations));
        }
        return userConfigurationsDtoList;
    }

    private UserConfigurationsDto convertToDto(UserConfigurations userConfigurations) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userConfigurations, UserConfigurationsDto.class);
    }
}
