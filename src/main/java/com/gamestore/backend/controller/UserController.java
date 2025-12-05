package com.gamestore.backend.controller;

import com.gamestore.backend.entity.User;
import com.gamestore.backend.repository.UserRepository;
import com.gamestore.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    // 1. REGISTER (Oricine poate accesa asta - vezi SecurityConfig)
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // --- NOTA: Metoda veche de LOGIN a fost stearsa pentru ca ---
    // --- Spring Security se ocupa acum de logare (Basic Auth) ---

    // 2. GET CURRENT USER (Verifica cine e logat)
    // Inlocuieste vechiul Login. Daca asta merge, inseamna ca esti logat!
    // GET http://localhost:8080/users/me
    @GetMapping("/me")
    public User getCurrentUser(@AuthenticationPrincipal User user) {
        return user;
    }

    // 3. GET USER BY ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 4. GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}