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
    public ResponseEntity<CartDTO> addToCart(@Valid @RequestBody CartItemDTO itemDTO) {
        CartDTO updatedCart = cartService.addToCart(itemDTO);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<CartDTO> updateCartItemQuantity(@Valid @PathVariable Long itemId, @RequestParam int quantity) {
        CartDTO updatedCart = cartService.updateCartItemQuantity(itemId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeFromCart(@Valid @PathVariable Long itemId) {
        cartService.removeFromCart(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCart() {
        CartDTO cart = cartService.getCart();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
