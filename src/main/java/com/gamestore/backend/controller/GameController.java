package com.gamestore.backend.controller;

import com.gamestore.backend.entity.Game;
import com.gamestore.backend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public Game addGame(@RequestBody Game game) {
        return gameService.addGameToStore(game); // Nu mai trimitem user ID
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @PutMapping("/{gameId}")
    public Game updateGame(@PathVariable Long gameId, @RequestBody Game gameDetails) {
        return gameService.updateGame(gameId, gameDetails);
    }

    @DeleteMapping("/{gameId}")
    public String deleteGame(@PathVariable Long gameId) {
        gameService.deleteGame(gameId);
        return "Deleted!";
    }
}