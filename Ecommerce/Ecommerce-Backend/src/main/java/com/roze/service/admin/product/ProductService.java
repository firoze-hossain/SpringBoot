package com.roze.service.admin.product;

import com.roze.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();
}
