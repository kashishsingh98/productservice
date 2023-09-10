package dev.kashish.productService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private double prices;
    private String category;
    private String description;
    private String image;
}
