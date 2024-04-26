package com.harjoitusteht.elokuvaapp;

import com.harjoitusteht.elokuvaapp.model.User;
import com.harjoitusteht.elokuvaapp.repository.UserRepository;
import com.harjoitusteht.elokuvaapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
    }

    @Test
    void registerNewUserAccountShouldSaveNewUser() {
        when(userRepository.findByUsername("testuser")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");
        
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User registered = userService.registerNewUserAccount(user);

        assertNotNull(registered);
        assertEquals("hashedPassword", registered.getPassword());
        assertTrue(registered.getRoles().contains("ROLE_USER"));
        assertTrue(registered.getRoles().contains("ROLE_ADMIN"));

        verify(userRepository).findByUsername("testuser");
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerNewUserAccountShouldThrowExceptionWhenUsernameExists() {
        when(userRepository.findByUsername("testuser")).thenReturn(new User());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerNewUserAccount(user);
        });

        assertTrue(exception.getMessage().contains("Username already in use"));

        verify(userRepository).findByUsername("testuser");
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }
}

