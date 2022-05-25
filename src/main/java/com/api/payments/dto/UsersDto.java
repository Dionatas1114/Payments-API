package com.api.payments.dto;

import com.api.payments.entity.UserConfigurations;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UsersDto {

    public UUID id;
    public String name;
    public String email;
    public String password;
    public UserConfigurationsDto userConfigurations;

}
