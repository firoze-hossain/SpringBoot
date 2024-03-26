package com.roze.controller.customer;

import com.roze.dto.WishlistDto;
import com.roze.service.customer.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDto wishlistDto) {
        WishlistDto postedWishlistDto = wishlistService.addProductToWishlist(wishlistDto);
        if (postedWishlistDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);
        }
    }
}
