package com.roze.service.admin.order.impl;

import com.roze.dto.OrderDto;
import com.roze.entity.Order;
import com.roze.enums.OrderStatus;
import com.roze.repository.OrderRepository;
import com.roze.service.admin.order.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {
    private final OrderRepository orderRepository;

    public List<OrderDto> getAllPlacedOrders() {
        List<Order> orderList = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered));
        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }
}
