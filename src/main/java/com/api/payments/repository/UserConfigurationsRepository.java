package com.api.payments.repository;

import com.api.payments.entity.UserConfigurations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserConfigurationsRepository extends JpaRepository<UserConfigurations, UUID>{

    Optional<UserConfigurations> findByUserId(UUID userId);

}
