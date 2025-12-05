package com.gamestore.backend.controller;

import com.gamestore.backend.entity.User;
import com.gamestore.backend.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    // POST http://localhost:8080/shop/cart/1 (Nu mai avem userId in URL!)
    @PostMapping("/cart/{gameId}")
    public String addToCart(@AuthenticationPrincipal User user, @PathVariable Long gameId) {
        shopService.addToCart(user.getId(), gameId);
        return "Game added to cart!";
    }

    @PostMapping("/checkout")
    public String checkout(@AuthenticationPrincipal User user) {
        shopService.checkout(user.getId());
        return "Checkout successful!";
    }
}