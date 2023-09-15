package dev.kashish.productService.client.fakestore;

import dev.kashish.productService.dtos.GenericProductDto;
import dev.kashish.productService.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Controller
public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl ;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsPath;

    private final String getAllProductsRequestURL = fakeStoreApiUrl+fakeStoreProductsPath;

    private final String specificProductRequestURL = fakeStoreApiUrl+fakeStoreProductsPath+"/{id}";
    private final String createProductRequestURL = fakeStoreApiUrl+fakeStoreProductsPath;
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }


    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(createProductRequestURL, genericProductDto, FakeStoreProductDto.class);
        return response.getBody();
    }


    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestURL, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id: "+id+" does not exists.");
        }
        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        List<FakeStoreProductDto> listOfProducts = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(getAllProductsRequestURL, FakeStoreProductDto[].class);
        for (FakeStoreProductDto fakeStoreProductDto : Arrays.stream(response.getBody()).toList()) {
            listOfProducts.add(fakeStoreProductDto);
        }
        return listOfProducts;
    }


    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto updateProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestURL, HttpMethod.PUT, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }
}
