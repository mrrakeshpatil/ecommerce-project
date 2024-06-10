package com.ecom.api.Products.Controller;

import com.ecom.api.Products.Service.CartService;
import com.ecom.api.Products.Model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//- `POST /api/carts`: This API should create a new empty shopping cart and return its unique `cartId`.
@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart createCart() {
        return cartService.createCart();
    }

    @GetMapping("/{cartId}")
    public Optional<Cart> getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    @PostMapping("/{cartId}/items")
    public Cart addItemToCart(@PathVariable Long cartId, @RequestBody CartItemRequest request) {
        return cartService.addItemToCart(cartId, request.getProductId(), request.getQuantity());
    }

    @PutMapping("/{cartId}/items/{itemId}")
    public Cart updateItemInCart(@PathVariable Long cartId, @PathVariable Long itemId, @RequestBody CartItemRequest request) {
        return cartService.updateItemInCart(cartId, itemId, request.getQuantity());
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public Cart removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        return cartService.removeItemFromCart(cartId, itemId);
    }

}