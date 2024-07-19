package com.ecom.api.products.service;

import com.ecom.api.products.repository.CartRepository;
import com.ecom.api.products.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Cart addItemToCart(Long cartId, Long productId, int quantity)
    {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().put(productId, cart.getItems().getOrDefault(productId, 0) + quantity);
        return cartRepository.save(cart);
    }

    public Cart updateItemInCart(Long cartId, Long itemId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().put(itemId, quantity);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Long cartId, Long itemId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().remove(itemId);
        return cartRepository.save(cart);
    }
}
