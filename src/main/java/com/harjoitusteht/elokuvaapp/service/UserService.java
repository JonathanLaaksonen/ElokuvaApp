package com.harjoitusteht.elokuvaapp.service;

import com.harjoitusteht.elokuvaapp.model.User;
import com.harjoitusteht.elokuvaapp.repository.MovieRepository;
import com.harjoitusteht.elokuvaapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUserAccount(User user) {
        
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already in use");
            
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER,ROLE_ADMIN"); 
        return userRepository.save(user);
}
}