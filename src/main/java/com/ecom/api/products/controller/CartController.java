package com.ecom.api.products.controller;

import com.ecom.api.products.model.Cart;
import com.ecom.api.products.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    //- `POST /api/carts`: This API should create a new empty shopping cart and return its unique `cartId`.
    @PostMapping
    public Cart createCart() {
        return cartService.createCart();
    }

    //- `GET /api/carts/{cartId}`: This API should retrieve the contents of a specific shopping cart identified by its `cartId`. The response
// should include the list of products in the cart, their quantities, and the total cost.
    @GetMapping("/{cartId}")
    public Optional<Cart> getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    //- `POST /api/carts/{cartId}/items`: This API should add a new product to the shopping cart identified by `cartId`.
// The request body should include the `productId` and the desired quantity.
    @PostMapping("/{cartId}/items")
    public Cart addItemToCart(@PathVariable Long cartId, @RequestBody CartItemRequest request) {
        return cartService.addItemToCart(cartId, request.getProductId(), request.getQuantity());
    }

    //- `PUT /api/carts/{cartId}/items/{itemId}`: This API should update the quantity of a specific product (`itemId`) in
// the shopping cart identified by `cartId`. The request body should include the new quantity.
    @PutMapping("/{cartId}/items/{itemId}")
    public Cart updateItemInCart(@PathVariable Long cartId, @PathVariable Long itemId, @RequestBody CartItemRequest request) {
        return cartService.updateItemInCart(cartId, itemId, request.getQuantity());
    }

    //- `DELETE /api/carts/{cartId}/items/{itemId}`: This API should remove
// a specific product (`itemId`) from the shopping cart identified by `cartId`.
    @DeleteMapping("/{cartId}/items/{itemId}")
    public Cart removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        return cartService.removeItemFromCart(cartId, itemId);
    }

}
