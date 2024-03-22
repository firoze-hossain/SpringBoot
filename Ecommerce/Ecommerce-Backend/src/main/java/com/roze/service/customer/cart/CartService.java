package com.roze.service.customer.cart;

import com.roze.dto.AddProductInCartDto;
import com.roze.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);

    OrderDto applyCoupon(Long userId, String code);

    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
}
