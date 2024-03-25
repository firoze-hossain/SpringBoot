package com.roze.service.customer.review.impl;

import com.roze.dto.OrderedProductsResponseDto;
import com.roze.dto.ProductDto;
import com.roze.dto.ReviewDto;
import com.roze.entity.*;
import com.roze.repository.OrderRepository;
import com.roze.repository.ProductRepository;
import com.roze.repository.ReviewRepository;
import com.roze.repository.UserRepository;
import com.roze.service.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();
        if (optionalOrder.isPresent()) {
            orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());
            List<ProductDto> productDtoList = new ArrayList<>();
            for (CartItems cartItems : optionalOrder.get().getCartItems()) {
                ProductDto productDto = new ProductDto();
                productDto.setId(cartItems.getProduct().getId());
                productDto.setName(cartItems.getProduct().getName());
                productDto.setPrice(cartItems.getPrice());
                productDto.setQuantity(cartItems.getQuantity());
                productDto.setByteImage(cartItems.getProduct().getImage());
                productDtoList.add(productDto);
            }
            orderedProductsResponseDto.setProductDtoList(productDtoList);
        }
        return orderedProductsResponseDto;
    }

    public ReviewDto postReview(ReviewDto reviewDto) throws IOException {
        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
        Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
        if (optionalProduct.isPresent() && optionalUser.isPresent()) {
            Review review = new Review();
            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImage(reviewDto.getImg().getBytes());
            return reviewRepository.save(review).getDto();
        } else {
            return null;
        }
    }
}
