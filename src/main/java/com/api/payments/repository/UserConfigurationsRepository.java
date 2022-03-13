package com.api.payments.repository;

import com.api.payments.model.UserConfigurations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConfigurationsRepository extends JpaRepository<UserConfigurations, Long>{
}
