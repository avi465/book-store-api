package com.example.bookstore_api.service;

import com.example.bookstore_api.model.Wishlist;
import com.example.bookstore_api.model.WishlistItem;
import com.example.bookstore_api.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    public Wishlist addToWishlist(String username, WishlistItem item) {
        Wishlist wishlist = wishlistRepository.findByUsername(username)
                .orElse(new Wishlist(username, new ArrayList<>()));

        wishlist.getItems().add(item);
        return wishlistRepository.save(wishlist);
    }

    public void removeFromWishlist(String username, Long itemId) {
        Wishlist wishlist = wishlistRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        wishlist.getItems().removeIf(item -> item.getId().equals(itemId));
        wishlistRepository.save(wishlist);
    }

    public Wishlist getWishlist(String username) {
        return wishlistRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));
    }
}
