package com.roze.service.customer.cart.impl;

import com.roze.dto.AddProductInCartDto;
import com.roze.dto.CartItemsDto;
import com.roze.dto.OrderDto;
import com.roze.dto.PlaceOrderDto;
import com.roze.entity.*;
import com.roze.enums.OrderStatus;
import com.roze.exceptions.ValidationException;
import com.roze.repository.*;
import com.roze.service.customer.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CouponRepository couponRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(),
                activeOrder.getId(), addProductInCartDto.getUserId());
        if (optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());
            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);
                orderRepository.save(activeOrder);
                return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }

    public OrderDto getCartByUserId(Long userId) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().
                stream().map(CartItems::getCartDto).collect(Collectors.toList());
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setId(activeOrder.getId());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);
        if (activeOrder.getCoupon() != null) {
            orderDto.setCouponName(activeOrder.getCoupon().getName());
        }
        return orderDto;
    }

    public OrderDto applyCoupon(Long userId, String code) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new ValidationException("Coupon not found!!"));
        if (couponIsExpired(coupon)) {
            throw new ValidationException("Coupon is expired");
        }
        double discountAmount = ((coupon.getDiscount() / 100.0) * activeOrder.getTotalAmount());
        double netAmount = activeOrder.getTotalAmount() - discountAmount;
        activeOrder.setAmount((long) netAmount);
        activeOrder.setDiscount((long) discountAmount);
        activeOrder.setCoupon(coupon);
        orderRepository.save(activeOrder);
        return activeOrder.getOrderDto();
    }

    public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(),
                activeOrder.getId(), addProductInCartDto.getUserId());
        if (optionalProduct.isPresent() && optionalCartItems.isPresent()) {
            CartItems cartItems = optionalCartItems.get();
            Product product = optionalProduct.get();
            activeOrder.setAmount(activeOrder.getAmount() + product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product.getPrice());
            cartItems.setQuantity(cartItems.getQuantity() + 1);
            if (activeOrder.getCoupon() != null) {
                double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;
                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);
            }
            cartItemsRepository.save(cartItems);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return null;
    }

    public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(),
                activeOrder.getId(), addProductInCartDto.getUserId());
        if (optionalProduct.isPresent() && optionalCartItems.isPresent()) {
            CartItems cartItems = optionalCartItems.get();
            Product product = optionalProduct.get();
            activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());
            cartItems.setQuantity(cartItems.getQuantity() - 1);
            if (activeOrder.getCoupon() != null) {
                double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;
                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);
            }
            cartItemsRepository.save(cartItems);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return null;
    }

//    public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
//        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);
//        Optional<User> optionalUser = userRepository.findById(placeOrderDto.getUserId());
//        if (optionalUser.isPresent()) {
//            activeOrder.setOrderDescription(placeOrderDto.getOderDescription());
//            activeOrder.setAddress(placeOrderDto.getAddress());
//            activeOrder.setDate(new Date());
//            activeOrder.setOrderStatus(OrderStatus.Placed);
//            activeOrder.setTrackingId(UUID.randomUUID());
//            orderRepository.save(activeOrder);
//
//            Order order = new Order();
//            order.setAmount(0L);
//            order.setDiscount(0L);
//            order.setTotalAmount(0L);
//            order.setUser(optionalUser.get());
//            order.setOrderStatus(OrderStatus.Pending);
//            orderRepository.save(order);
//            return activeOrder.getOrderDto();
//        }
//        return null;
//    }

    public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
        Optional<User> optionalUser = userRepository.findById(placeOrderDto.getUserId());
        if (optionalUser.isPresent()) {
            Order activeOrder = orderRepository.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);
            if (activeOrder != null) {
                activeOrder.setOrderDescription(placeOrderDto.getOderDescription());
                activeOrder.setAddress(placeOrderDto.getAddress());
                activeOrder.setDate(new Date());
                activeOrder.setOrderStatus(OrderStatus.Placed);
                activeOrder.setTrackingId(UUID.randomUUID());
                orderRepository.save(activeOrder);
                return activeOrder.getOrderDto();
            } else {
                Order order = new Order();
                order.setAmount(0L);
                order.setDiscount(0L);
                order.setTotalAmount(0L);
                order.setUser(optionalUser.get());
                order.setOrderStatus(OrderStatus.Pending);
                orderRepository.save(order);
                return order.getOrderDto();
            }
        }
        return null;
    }


    private boolean couponIsExpired(Coupon coupon) {
        Date currentDate = new Date();
        Date expirationDate = coupon.getExpirationDate();
        return expirationDate != null && currentDate.after(expirationDate);
    }

    public List<OrderDto> getMyPlacedOrders(Long userId) {
        return orderRepository.findByUserIdAndOrderStatusIn(userId,
                List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered)).
                stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

    public OrderDto searchOrderByTrackingId(UUID trackingId) {
        Optional<Order> optionalOrder = orderRepository.findByTrackingId(trackingId);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get().getOrderDto();
        }
        return null;
    }
}
