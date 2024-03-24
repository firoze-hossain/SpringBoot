package com.roze.controller.customer;

import com.roze.dto.OrderedProductsResponseDto;
import com.roze.service.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductsResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
    }
}
