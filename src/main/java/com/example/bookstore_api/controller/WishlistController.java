package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.Wishlist;
import com.example.bookstore_api.model.WishlistItem;
import com.example.bookstore_api.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<Wishlist> addToWishlist(@RequestBody WishlistItem item, @RequestParam String username) {
        Wishlist updatedWishlist = wishlistService.addToWishlist(username, item);
        return new ResponseEntity<>(updatedWishlist, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long itemId, @RequestParam String username) {
        wishlistService.removeFromWishlist(username, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Wishlist> getWishlist(@PathVariable String username){
        Wishlist wishlist = wishlistService.getWishlist(username);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }
}
