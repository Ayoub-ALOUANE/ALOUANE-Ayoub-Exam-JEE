package com.alouane.ayoub.exam.security;

import com.alouane.ayoub.exam.entities.AppUser;
import com.alouane.ayoub.exam.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AccountService accountService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            AppUser appUser = accountService.loadUserByUsername(username);
            if (appUser == null) throw new UsernameNotFoundException("User not found");
            return new User(appUser.getUsername(), appUser.getPassword(),
                    appUser.getRoles().stream()
                            .map(r -> new SimpleGrantedAuthority(r.getRoleName()))
                            .collect(Collectors.toList()));
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/login/**").permitAll()
                .anyRequest().authenticated());

        httpSecurity.headers(headers -> headers.frameOptions(frame -> frame.disable())); // For H2 console

        httpSecurity.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
