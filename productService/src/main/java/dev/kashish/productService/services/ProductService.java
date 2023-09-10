package dev.kashish.productService.services;

import dev.kashish.productService.dtos.GenericProductDto;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    GenericProductDto createProduct(GenericProductDto genericProductDto);

    GenericProductDto getProductById(Long id);
}
