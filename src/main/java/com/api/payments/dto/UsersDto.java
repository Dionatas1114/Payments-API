package com.api.payments.dto;

import com.api.payments.entity.UserConfigurations;
import lombok.Data;

@Data
public class UsersDto {

    public String name;
    public String email;
    public String password;
    public UserConfigurations userConfigurations;

}
