package com.gamestore.backend.service;

import com.gamestore.backend.entity.Game;
import com.gamestore.backend.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    // --- ADD GAME ---
    public Game addGameToStore(Game game) {
        return gameRepository.save(game);
    }

    // --- UPDATE GAME (Fix pentru eroarea ta) ---
    // Acum nu mai cere adminId, ci doar gameId si detaliile
    public Game updateGame(Long gameId, Game gameDetails) {
        Game existingGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found!"));

        existingGame.setTitle(gameDetails.getTitle());
        existingGame.setPrice(gameDetails.getPrice());
        existingGame.setDescription(gameDetails.getDescription());

        return gameRepository.save(existingGame);
    }

    // --- DELETE GAME (Fix pentru eroarea ta) ---
    // Acum nu mai cere adminId
    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }

    // --- GET ALL GAMES ---
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}