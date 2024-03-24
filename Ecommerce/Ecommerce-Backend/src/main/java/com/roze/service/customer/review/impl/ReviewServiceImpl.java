package com.roze.service.customer.review.impl;

import com.roze.dto.OrderedProductsResponseDto;
import com.roze.dto.ProductDto;
import com.roze.entity.CartItems;
import com.roze.entity.Order;
import com.roze.repository.OrderRepository;
import com.roze.service.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final OrderRepository orderRepository;

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
}
