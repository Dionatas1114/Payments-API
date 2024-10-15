package com.api.payments.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsersDto {

    public UUID id;

    @NotNull(message = "Name cannot be null")
    @Min(value = 6, message = "Name should not be less than 6")
    @Max(value = 50, message = "Name should not be greater than 50")
    public String name;

    @NotNull(message = "Email cannot be null")
    @Email
    public String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 100)
    public String password;

    @NotNull(message = "Phone cannot be null")
    @Size(min = 10, max = 10)
    public String phone;

    public UserConfigurationsDto userConfigurations;

}
