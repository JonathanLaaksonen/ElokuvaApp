package com.harjoitusteht.elokuvaapp.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.harjoitusteht.elokuvaapp.model.User;
import com.harjoitusteht.elokuvaapp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String[] roles = user.getRoles().split(","); // Assuming roles are separated by a comma
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
    
        System.out.println("User roles: " + user.getRoles());
        System.out.println("Authorities: " + authorities);
    
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}