package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.Wishlist;
import com.example.bookstore_api.model.WishlistItem;
import com.example.bookstore_api.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{productId}")
    public ResponseEntity<Wishlist> addToWishlist(@Valid @PathVariable Long productId) {
        Wishlist updatedWishlist = wishlistService.addToWishlist(productId);
        return new ResponseEntity<>(updatedWishlist, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeFromWishlist(@Valid @PathVariable Long itemId) {
        wishlistService.removeFromWishlist(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<Wishlist> getWishlist(){
        Wishlist wishlist = wishlistService.getWishlist();
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }
}
