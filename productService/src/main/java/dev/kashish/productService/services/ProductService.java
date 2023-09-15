package dev.kashish.productService.services;

import dev.kashish.productService.client.fakestore.FakeStoreProductDto;
import dev.kashish.productService.dtos.GenericProductDto;

import dev.kashish.productService.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    GenericProductDto createProduct(GenericProductDto genericProductDto);

    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id);

    GenericProductDto updateProductById(Long id);
}
