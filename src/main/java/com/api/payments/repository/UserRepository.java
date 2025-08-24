package com.api.payments.repository;

import com.api.payments.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByName(String name);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Optional<Users> findByNameOrEmailOrPhone(String userName, String email, String phone);

}
