package com.api.payments.services;

import com.api.payments.dto.UserConfigurationsDto;

import java.util.List;

public interface UserConfigurationsService {

    List<UserConfigurationsDto> findUserConfigurations() throws Exception;

}
