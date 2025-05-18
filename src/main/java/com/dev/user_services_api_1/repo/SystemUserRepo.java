package com.dev.user_services_api_1.repo;

import com.dev.user_services_api_1.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemUserRepo extends JpaRepository<SystemUser, String> {
    // This interface extends JpaRepository, which provides CRUD operations for SystemUser entities.
    // You can add custom query methods here if needed.

    Optional<SystemUser> findByEmail(String email);

}
