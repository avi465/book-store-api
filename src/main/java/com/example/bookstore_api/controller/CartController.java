package com.example.bookstore_api.controller;

import com.example.bookstore_api.dto.CartDTO;
import com.example.bookstore_api.dto.CartItemDTO;
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
    public ResponseEntity<CartDTO> addToCart(@Valid @RequestBody CartItemDTO itemDTO, @RequestParam String username) {
        CartDTO updatedCart = cartService.addToCart(username, itemDTO);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<CartDTO> updateCartItemQuantity(@Valid @PathVariable Long itemId, @RequestParam int quantity, @RequestParam String username) {
        CartDTO updatedCart = cartService.updateCartItemQuantity(username, itemId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeFromCart(@Valid @PathVariable Long itemId, @RequestParam String username) {
        cartService.removeFromCart(username, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{username}")
    public ResponseEntity<CartDTO> getCart(@Valid @PathVariable String username) {
        CartDTO cart = cartService.getCart(username);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
