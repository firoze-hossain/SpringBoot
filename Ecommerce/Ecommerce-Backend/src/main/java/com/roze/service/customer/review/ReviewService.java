package com.roze.service.customer.review;

import com.roze.dto.OrderedProductsResponseDto;
import com.roze.dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDto postReview(ReviewDto reviewDto) throws IOException;
}
