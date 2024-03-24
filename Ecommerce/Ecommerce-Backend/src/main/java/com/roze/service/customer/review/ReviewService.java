package com.roze.service.customer.review;

import com.roze.dto.OrderedProductsResponseDto;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
}
