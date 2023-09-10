package dev.kashish.productService.services.impl;

import dev.kashish.productService.dtos.GenericProductDto;
import dev.kashish.productService.services.ProductService;

import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
	@Override
	public GenericProductDto createProduct(GenericProductDto genericProductDto) {
		return null;
	}

	@Override
	public GenericProductDto getProductById(Long id) {
		return null;
	}
}
