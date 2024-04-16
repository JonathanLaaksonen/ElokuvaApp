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
    // Lisää tämä, jos konstruktori-injektointi ei ole vielä määritelty
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUserAccount(User user) {
        // Tarkista, ettei samalla käyttäjänimellä ole jo luotu tiliä
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already in use");
            // Heitä poikkeus tai käsittele tilanne, jos käyttäjänimi on jo käytössä
        }
        // Hashaa salasana ennen tallennusta
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER,ROLE_ADMIN"); // Aseta oletusrooli
        return userRepository.save(user);
}
}