package com.ecom.api.products.service;

import com.ecom.api.products.model.Discount;
import com.ecom.api.products.repository.CartRepository;
import com.ecom.api.products.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;

    private final DiscountService discountService;

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

    public Cart cartValueAfterDiscount(Long cartId, String discountCode) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        List<Discount> discountList = discountService.findByCode(discountCode)
                .orElseThrow(() -> new RuntimeException("Discount not found or invalid"));
        if(Objects.nonNull(discountList) && !discountList.isEmpty()){
            var discount = discountList.get(0);
            double totalValue = cart.getDiscount()+cart.getTotalCost();

            double totalDiscount = totalValue * discount.getPercentage() * 0.01;
            cart.setDiscountCode(discount.getCode());
            cart.setDiscount(totalDiscount);
            cart.setTotalCost(totalValue-totalDiscount);
            cartRepository.save(cart);
            return cart;
        }
        throw new RuntimeException("Discount not found or invalid");

    }

}
