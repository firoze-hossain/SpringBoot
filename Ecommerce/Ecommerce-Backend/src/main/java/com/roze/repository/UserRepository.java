package com.roze.repository;

import com.roze.entity.User;
import com.roze.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String username);

    User findByUserRole(UserRole userRole);
}
