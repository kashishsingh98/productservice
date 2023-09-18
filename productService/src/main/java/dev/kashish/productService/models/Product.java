package dev.kashish.productService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseModel{
	private String title;
	private String description;
	private String image;
	@ManyToOne
	private Category category;
	private double price;

}
