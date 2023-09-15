package dev.kashish.productService.services.impl;

import dev.kashish.productService.client.fakestore.FakeStoreProductDto;
import dev.kashish.productService.client.fakestore.FakeStoreProductServiceClient;
import dev.kashish.productService.dtos.GenericProductDto;
import dev.kashish.productService.exception.NotFoundException;
import dev.kashish.productService.services.ProductService;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductServiceImpl")
public class FakeStoreProxyProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;


    public FakeStoreProxyProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
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
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductServiceClient.createProduct(genericProductDto));
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> productDtosGeneric = new ArrayList<>();
        List<FakeStoreProductDto> productDtosFakeStore = fakeStoreProductServiceClient.getAllProducts();
        for(FakeStoreProductDto productDto : productDtosFakeStore){
            productDtosGeneric.add(convertFakeStoreDtoToGenericProductDto(productDto));
        }
        return productDtosGeneric;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(Long id) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductServiceClient.updateProductById(id));
    }
}
