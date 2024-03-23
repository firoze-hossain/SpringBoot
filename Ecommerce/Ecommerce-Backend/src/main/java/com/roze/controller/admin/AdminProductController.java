package com.roze.controller.admin;

import com.roze.dto.FAQDto;
import com.roze.dto.ProductDto;
import com.roze.service.admin.faq.FAQService;
import com.roze.service.admin.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {
    private final ProductService productService;
    private final FAQService faqService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto1 = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.getAllProducts();
        return ResponseEntity.ok(productDtos);

    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable("name") String name) {
        List<ProductDto> productDtos = productService.getAllProductsByName(name);
        return ResponseEntity.ok(productDtos);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        boolean deletedProduct = productService.deleteProduct(id);
        if (deletedProduct) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/faq/{productId}")
    public ResponseEntity<FAQDto> postFAQ(@PathVariable("productId") Long productId, FAQDto faqDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId, faqDto));
    }
}
