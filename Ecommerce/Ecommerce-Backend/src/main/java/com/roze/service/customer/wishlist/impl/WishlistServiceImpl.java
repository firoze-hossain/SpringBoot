package com.roze.service.customer.wishlist.impl;

import com.roze.dto.WishlistDto;
import com.roze.entity.Product;
import com.roze.entity.User;
import com.roze.entity.Wishlist;
import com.roze.repository.ProductRepository;
import com.roze.repository.UserRepository;
import com.roze.repository.WishlistRepository;
import com.roze.service.customer.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WishlistRepository wishlistRepository;

    public WishlistDto addProductToWishlist(WishlistDto wishlistDto) {
        Optional<User> optionalUser = userRepository.findById(wishlistDto.getUserId());
        Optional<Product> optionalProduct = productRepository.findById(wishlistDto.getProductId());
        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            Wishlist wishlist = new Wishlist();
            wishlist.setUser(optionalUser.get());
            wishlist.setProduct(optionalProduct.get());
            return wishlistRepository.save(wishlist).getWishlistDto();
        }
        return null;

    }

    public List<WishlistDto> getWishlistByUserId(Long userId) {
        return wishlistRepository.findAllByUserId(userId).stream()
                .map(Wishlist::getWishlistDto).collect(Collectors.toList());
    }
}
