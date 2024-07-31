package com.msjo.shop.controller;


import com.msjo.shop.dto.req.ProductMypriceRequestDto;
import com.msjo.shop.dto.req.ProductRequestDto;
import com.msjo.shop.dto.res.ProductResponseDto;
import com.msjo.shop.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 관심 상품 조회하기
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts() {
        // 응답 보내기
        return productService.getProducts();
    }

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto request) {
        return productService.createProduct(request);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable long id, @RequestBody ProductMypriceRequestDto request) {
        return productService.updateProduct(id, request);
    }


}
