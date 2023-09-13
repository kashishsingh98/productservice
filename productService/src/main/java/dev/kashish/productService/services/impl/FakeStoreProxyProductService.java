package dev.kashish.productService.services.impl;

import dev.kashish.productService.dtos.FakeStoreProductDto;
import dev.kashish.productService.dtos.GenericProductDto;
import dev.kashish.productService.exception.NotFoundException;
import dev.kashish.productService.services.ProductService;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProxyProductService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;

    private final String getAllProductsRequestURL = "https://fakestoreapi.com/products";

    private final String specificProductRequestURL = "https://fakestoreapi.com/products/{id}";
    private final String createProductRequestURL = "https://fakestoreapi.com/products";


    public FakeStoreProxyProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrices(fakeStoreProductDto.getPrices());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(createProductRequestURL, genericProductDto, GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestURL, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id: "+id+" does not exists.");
        }
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
    }

    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> listOfProducts = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(getAllProductsRequestURL, FakeStoreProductDto[].class);
        for (FakeStoreProductDto fakeStoreProductDto : Arrays.stream(response.getBody()).toList()) {
            listOfProducts.add(convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto));
        }
        return listOfProducts;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto updateProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestURL, HttpMethod.PUT, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
    }
}
