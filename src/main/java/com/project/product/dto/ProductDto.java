package com.project.product.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductDto {

	@NotEmpty(message = "Product Name is Required")
	private String productName;

	@NotEmpty(message = "Product Brand is Required")
	private String productBrand;

	@NotEmpty(message = "Product Category is Required")
	private String productCategory;

	@Min(0)
	private double productPrice;

	@Size(max = 1000, message = "The Description cannot exeed 1000 characters")
	@Size(min = 10, message = "The Description shuold be at least 10 characters")
	private String productDescription;

	private MultipartFile productImage;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

}
