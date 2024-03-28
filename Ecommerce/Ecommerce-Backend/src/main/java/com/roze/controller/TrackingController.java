package com.roze.controller;

import com.roze.dto.OrderDto;
import com.roze.service.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TrackingController {
    private final CartService cartService;

    @GetMapping("/order/{trackingId}")
    public ResponseEntity<OrderDto> searchOrderByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        OrderDto orderDto = cartService.searchOrderByTrackingId(trackingId);
        if (orderDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(orderDto);
        }
    }
}
