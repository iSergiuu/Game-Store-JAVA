package com.gamestore.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails { // <--- Implementam UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role; // "ADMIN" or "CLIENT"

    @ManyToMany(fetch = FetchType.EAGER) // Eager ca sa incarce jocurile imediat
    @JoinTable(name = "cart_items")
    private List<Game> cart = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "library_items")
    private List<Game> library = new ArrayList<>();

    // --- METODE OBLIGATORII PENTRU SECURITY ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Transformam rolul nostru String ("ADMIN") in limbaj de securitate ("ROLE_ADMIN")
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}