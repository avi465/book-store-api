package com.example.bookstore_api.service;

import com.example.bookstore_api.dto.CartDTO;
import com.example.bookstore_api.dto.CartItemDTO;
import com.example.bookstore_api.exception.NotFoundException;
import com.example.bookstore_api.model.Cart;
import com.example.bookstore_api.model.CartItem;
import com.example.bookstore_api.model.Product;
import com.example.bookstore_api.repository.CartRepository;
import com.example.bookstore_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

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

    public CartDTO addToCart(CartItemDTO itemDTO) {
        String username = getCurrentUsername();

        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new NotFoundException("Product with given id not found"));

        Cart cart = cartRepository.findByUsername(username)
                .orElse(new Cart(username, new ArrayList<>()));

        CartItem cartItem = new CartItem();
        cartItem.setProductId(itemDTO.getProductId());
        cartItem.setQuantity((itemDTO.getQuantity()));
        cartItem.setPrice(product.getPrice());

        cart.getItems().add(cartItem);

        cart = cartRepository.save(cart);

        return convertToDto(cart);
    }

    public CartDTO updateCartItemQuantity(Long itemId, int quantity) {
        String username = getCurrentUsername();
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

    public void removeFromCart(Long itemId) {
        String username = getCurrentUsername();
        Cart cart = cartRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        cart.getItems().removeIf(item -> item.getId().equals(itemId));
        cartRepository.save(cart);
    }

    public CartDTO getCart() {
        String username = getCurrentUsername();
        Cart cart = cartRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cart not found"));
        return convertToDto(cart);
    }

    private CartDTO convertToDto(Cart cart) {
        List<CartItemDTO> itemDTOs = cart.getItems()
                .stream()
                .map(item -> new CartItemDTO(item.getProductId(), item.getQuantity(), item.getPrice()))
                .collect(Collectors.toList());
        return new CartDTO(cart.getUsername(), itemDTOs);
    }
}
