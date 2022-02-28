package com.api.payments.repository;

import com.api.payments.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    UserModel findByName(String name);

    UserModel findByEmail(String email);

    boolean existsById(UUID userId);

    Optional<UserModel> findById(UUID userId);

    void deleteById(UUID userId);
}
