package dev.kashish.productService.controllers;

import dev.kashish.productService.dtos.GenericProductDto;
import dev.kashish.productService.services.ProductService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    //Autowired used only in cass of field injection
    // Constructor injection
    public ProductController(@Qualifier("fakeStoreProductServiceImpl") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public void getAllProducts() {

    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) {

        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById() {

    }

    @PutMapping("{id}")
    public void updateProductById() {

    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        return productService.createProduct(product);
    }
}
