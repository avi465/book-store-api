package com.example.bookstore_api.service;

import com.example.bookstore_api.exception.NotFoundException;
import com.example.bookstore_api.model.Product;
import com.example.bookstore_api.model.Wishlist;
import com.example.bookstore_api.model.WishlistItem;
import com.example.bookstore_api.repository.ProductRepository;
import com.example.bookstore_api.repository.WishlistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            assert authentication != null;
            return authentication.getName();
        }
    }

    public Wishlist addToWishlist(Long productId) {
        String username = getCurrentUsername();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with given id not found"));

        Wishlist wishlist = wishlistRepository.findByUsername(username)
                .orElse(new Wishlist(username, new ArrayList<>()));

        WishlistItem item = new WishlistItem();
        item.setProductId(product.getId());
        item.setProductName(product.getBookName());
        item.setPrice(product.getPrice());

        wishlist.getItems().add(item);
        return wishlistRepository.save(wishlist);
    }

    public void removeFromWishlist(Long itemId) {
        String username = getCurrentUsername();
        Wishlist wishlist = wishlistRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Wishlist not found"));

        wishlist.getItems().removeIf(item -> item.getId().equals(itemId));
        wishlistRepository.save(wishlist);
    }

    public Wishlist getWishlist() {
        String username = getCurrentUsername();
        return wishlistRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Wishlist not found"));
    }
}
