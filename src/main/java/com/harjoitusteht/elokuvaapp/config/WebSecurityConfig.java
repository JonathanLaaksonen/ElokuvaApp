package com.harjoitusteht.elokuvaapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        // Assuming userDetailsService is used elsewhere or configured automatically
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/css/**", "/js/**", "/register").permitAll() // Allow CSS and JS when logged out
                .requestMatchers(toH2Console()).permitAll() // Allow H2 Console access
                .anyRequest().authenticated())
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(toH2Console())) // Disable CSRF for H2 Console
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("script-src 'self' 'unsafe-inline'; frame-src 'self' data:;"))
                .frameOptions().sameOrigin()) // Moved inside headers configuration
            .formLogin(form -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home", true))
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Specify the logout URL
                .logoutSuccessUrl("/login") // Redirect to login page after logout
                .invalidateHttpSession(true) // Invalidate session
                .deleteCookies("JSESSIONID") // Delete cookies
                .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}