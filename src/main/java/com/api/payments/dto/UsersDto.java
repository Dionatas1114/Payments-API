package com.api.payments.dto;

import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsersDto {

    public UUID id;
    public String name;
    public String email;
    public String password;
    public String phone;
    public UserConfigurationsDto userConfigurations;

}
