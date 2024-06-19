package com.project.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.product.entity.Product;

@Service
public interface ProductService {

	public List<Product> getAllProducts();

	public void addNewProduct(Product product);
	
	
}
