package com.example.bookstore_api.service;

import com.example.bookstore_api.dto.CartDTO;
import com.example.bookstore_api.dto.CartItemDTO;
import com.example.bookstore_api.exception.NotFoundException;
import com.example.bookstore_api.model.Cart;
import com.example.bookstore_api.model.CartItem;
import com.example.bookstore_api.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public CartDTO addToCart(String username, CartItemDTO itemDTO) {
        Cart cart = cartRepository.findByUsername(username)
                .orElse(new Cart(username, new ArrayList<>()));

        CartItem cartItem = new CartItem();
        cartItem.setProductId(itemDTO.getProductId());
        cartItem.setQuantity((itemDTO.getQuantity()));
        cartItem.setPrice(itemDTO.getPrice());

        cart.getItems().add(cartItem);

        cart.getItems().add(cartItem);
        cart = cartRepository.save(cart);

        return convertToDto(cart);
    }

    public CartDTO updateCartItemQuantity(String username, Long itemId, int quantity) {
        Cart cart = cartRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        for (CartItem item : cart.getItems()) {
            if (item.getId().equals(itemId)) {
                item.setQuantity(quantity);
                break;
            }
        }
        cart = cartRepository.save(cart);
        return convertToDto(cart);
    }

    public void removeFromCart(String username, Long itemId) {
        Cart cart = cartRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        cart.getItems().removeIf(item -> item.getId().equals(itemId));
        cartRepository.save(cart);
    }

    public CartDTO getCart(String username) {
        Cart cart = cartRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cart not found"));
        return convertToDto(cart);
    }

    private CartDTO convertToDto(Cart cart) {
        List<CartItemDTO> itemDTOs = cart.getItems()
                .stream()
                .map(item -> new CartItemDTO(item.getId(), item.getProductId(), item.getQuantity(), item.getPrice()))
                .collect(Collectors.toList());
        return new CartDTO(cart.getId(), cart.getUsername(), itemDTOs);
    }
}
