package com.api.payments.repository;

import com.api.payments.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

    Users findByName(String name);

    Users findByEmail(String email);

    boolean existsById(UUID userId);

    Optional<Users> findById(UUID userId);

    void deleteById(UUID userId);
}
