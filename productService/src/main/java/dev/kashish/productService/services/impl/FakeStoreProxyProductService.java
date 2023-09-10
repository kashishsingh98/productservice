package dev.kashish.productService.services.impl;

import dev.kashish.productService.dtos.FakeStoreProductDto;
import dev.kashish.productService.dtos.GenericProductDto;
import dev.kashish.productService.services.ProductService;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProxyProductService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;

    private final String getProductRequestURL = "https://fakestoreapi.com/products/{id}";
    private final String createProductRequestURL = "https://fakestoreapi.com/products";


    public FakeStoreProxyProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(createProductRequestURL, genericProductDto, GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestURL, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrices(fakeStoreProductDto.getPrices());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
}
