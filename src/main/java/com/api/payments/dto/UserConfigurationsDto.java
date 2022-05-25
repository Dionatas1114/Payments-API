package com.api.payments.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserConfigurationsDto {

    public UUID id;
    public boolean hasNotifications;

}
