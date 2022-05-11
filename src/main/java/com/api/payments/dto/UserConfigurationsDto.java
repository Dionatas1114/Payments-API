package com.api.payments.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserConfigurationsDto {

    public UUID id;
    public boolean hasNotifications;

}
