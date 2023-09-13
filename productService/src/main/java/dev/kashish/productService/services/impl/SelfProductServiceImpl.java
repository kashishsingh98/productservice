package dev.kashish.productService.services.impl;

import dev.kashish.productService.dtos.GenericProductDto;
import dev.kashish.productService.services.ProductService;

import org.springframework.stereotype.Service;

import java.util.List;

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

	@Override
	public List<GenericProductDto> getAllProducts() {
		return null;
	}

	@Override
	public GenericProductDto deleteProductById(Long id) {
		return null;
	}

	@Override
	public GenericProductDto updateProductById(Long id) {
		return null;
	}
}
