package dev.kashish.productService.dtos;

import dev.kashish.productService.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double prices;
    private String category;
    private String description;
    private String image;

}
