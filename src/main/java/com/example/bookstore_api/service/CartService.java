package com.example.bookstore_api.service;

import com.example.bookstore_api.exception.NotFoundException;
import com.example.bookstore_api.model.Cart;
import com.example.bookstore_api.model.CartItem;
import com.example.bookstore_api.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart addToCart(String username, CartItem item) {
        Cart cart = cartRepository.findByUsername(username)
                .orElse(new Cart(username, new ArrayList<>()));

        cart.getItems().add(item);
        return cartRepository.save(cart);
    }

    public Cart updateCartItemQuantity(String username, Long itemId, int quantity) {
        Cart cart = cartRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        for (CartItem item : cart.getItems()) {
            if (item.getId().equals(itemId)) {
                item.setQuantity(quantity);
                break;
            }
        }
        return cartRepository.save(cart);
    }

    public void removeFromCart(String username, Long itemId) {
        Cart cart = cartRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        cart.getItems().removeIf(item -> item.getId().equals(itemId));
        cartRepository.save(cart);
    }

    public Cart getCart(String username) {
        return cartRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cart not found"));
    }
}
