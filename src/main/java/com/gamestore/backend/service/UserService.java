package com.gamestore.backend.service;

import com.gamestore.backend.entity.User;
import com.gamestore.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService { // <--- Implementam UserDetailsService

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // <--- Unealta de criptare a parolei

    // Aceasta metoda e folosita automat de Spring cand te loghezi
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // REGISTER (Acum criptam parola!)
    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username taken!");
        }
        // Criptam parola inainte de salvare (ex: "123" devine "$2a$10$hJS...")
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}