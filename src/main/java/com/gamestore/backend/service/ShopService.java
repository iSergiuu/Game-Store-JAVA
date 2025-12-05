package com.gamestore.backend.service;

import com.gamestore.backend.entity.Game;
import com.gamestore.backend.entity.User;
import com.gamestore.backend.repository.GameRepository;
import com.gamestore.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    // Add to Cart
    @Transactional
    public void addToCart(Long userId, Long gameId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found!"));

        user.getCart().add(game);
        userRepository.save(user);
    }

    // Checkout
    @Transactional
    public void checkout(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (user.getCart().isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        user.getLibrary().addAll(user.getCart());
        user.getCart().clear();
        userRepository.save(user);
    }

    // Refund
    @Transactional
    public void refund(Long userId, Long gameId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found!"));

        if (user.getLibrary().contains(game)) {
            user.getLibrary().remove(game);
            userRepository.save(user);
        } else {
            throw new RuntimeException("You do not own this game!");
        }
    }
}