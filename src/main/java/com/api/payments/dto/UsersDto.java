package com.api.payments.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDto {

    public UUID id;
    public String name;
    public String email;
    public String password;
    public UserConfigurationsDto userConfigurations;

}
