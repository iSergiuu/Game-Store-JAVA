package com.gamestore.backend.repository;

import com.gamestore.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring este deștept: dacă scriem numele metodei corect, el face SQL-ul singur!
    // Asta va face: "SELECT * FROM users WHERE username = ?"
    Optional<User> findByUsername(String username);
}