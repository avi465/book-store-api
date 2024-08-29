package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.Cart;
import com.example.bookstore_api.model.CartItem;
import com.example.bookstore_api.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> addToCart(@Valid @RequestBody CartItem item, @RequestParam String username) {
        Cart updatedCart = cartService.addToCart(username, item);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Cart> updateCartItemQuantity(@Valid @PathVariable Long itemId, @RequestParam int quantity, @RequestParam String username) {
        Cart updatedCart = cartService.updateCartItemQuantity(username, itemId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeFromCart(@Valid @PathVariable Long itemId, @RequestParam String username) {
        cartService.removeFromCart(username, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Cart> getCart(@Valid @PathVariable String username) {
        Cart cart = cartService.getCart(username);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
