package com.roze.service.customer;

import com.roze.dto.ProductDetailDto;
import com.roze.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {
    List<ProductDto> getAllProducts();

    List<ProductDto> searchProductsByTitle(String name);

    ProductDetailDto getProductDetailById(Long productId);
}
