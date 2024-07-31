package com.msjo.shop.service;

import com.msjo.shop.dto.req.ProductMypriceRequestDto;
import com.msjo.shop.dto.req.ProductRequestDto;
import com.msjo.shop.dto.res.ProductResponseDto;
import com.msjo.shop.entity.Product;
import com.msjo.shop.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

    ProductRepository productRepository;

    public static final int MIN_MY_PRODUCT = 100;

    public List<ProductResponseDto> getProducts() {

        List<Product> productList = productRepository.findAll();

        return productList.stream().map(ProductResponseDto::new).toList();

    }

    public ProductResponseDto createProduct(ProductRequestDto request) {

        Product savedProduct = productRepository.save(new Product(request));

        return new ProductResponseDto(savedProduct);
    }

    @Transactional
    public ProductResponseDto updateProduct(long id, ProductMypriceRequestDto request) {

        int myprice = request.getMyprice();

        if(myprice < MIN_MY_PRODUCT) {
            throw new IllegalArgumentException("유효하지 않는 관심 가격입니다. 최소 " + MIN_MY_PRODUCT + "원 이상으로 설정해주세요.");
        }

        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 상품을 찾을 수 없습니다.")
        );

        product.update(request);

        return new ProductResponseDto(product);
    }
}
