package com.harjoitusteht.elokuvaapp.repository;

import com.harjoitusteht.elokuvaapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}