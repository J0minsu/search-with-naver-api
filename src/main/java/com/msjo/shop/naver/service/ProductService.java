package com.msjo.shop.naver.service;

import com.msjo.shop.dto.req.ProductRequestDto;
import com.msjo.shop.dto.res.ProductResponseDto;
import com.msjo.shop.entity.Product;
import com.msjo.shop.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
//@Transactional
public class ProductService {

    ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto request) {

        Product savedProduct = productRepository.save(new Product(request));

        return new ProductResponseDto(savedProduct);
    }

}
