package com.msjo.shop.controller;


import com.msjo.shop.config.security.UserDetailsImpl;
import com.msjo.shop.dto.req.ProductMypriceRequestDto;
import com.msjo.shop.dto.req.ProductRequestDto;
import com.msjo.shop.dto.res.ProductResponseDto;
import com.msjo.shop.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 관심 상품 등록하기
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return productService.createProduct(requestDto, userDetails.getUser());
    }

    // 관심 상품 조회하기
    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 응답 보내기
        return productService.getProducts(userDetails.getUser(),  page-1, size, sortBy, isAsc);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable long id, @RequestBody ProductMypriceRequestDto request) {
        return productService.updateProduct(id, request);
    }

    // 관리자 조회
    @GetMapping("/admin/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

}
