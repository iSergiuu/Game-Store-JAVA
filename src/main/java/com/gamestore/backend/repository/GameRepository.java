package com.gamestore.backend.repository;

import com.gamestore.backend.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // Aici Spring ne oferă deja metode precum: save(), findAll(), findById(), delete()
    // Nu trebuie să scriem noi nimic!
}