package com.project.product.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.product.dto.ProductDto;
import com.project.product.entity.Product;
import com.project.product.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping({ "", "/" })
	public String getAllProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "products/index";
	}

	@GetMapping("/addNewProduct")
	public String redirectAddNewProductPage(Model model, ProductDto productDto) {
		model.addAttribute("productDto", productDto);
		return "products/createNewProduct";
	}

	@PostMapping("/addNewProduct")
	public String addNewProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult bindingResult,
			Product product) {

		if (productDto.getProductImage().isEmpty()) {
			bindingResult.addError(new FieldError("productDto", "productImage", "The File Image is Not Present"));
		}
		if (bindingResult.hasErrors()) {
			return "products/createNewProduct";
		}

//		Saveing Image

		MultipartFile image = productDto.getProductImage();
		Date productCreatedDate = new Date();
		String storageFileName = productCreatedDate.getTime() + "_" + image.getOriginalFilename();

		try {
			String uploadDirectory = "public/images/";
			Path uploadPath = Paths.get(uploadDirectory);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputStream = image.getInputStream()) {
				Files.copy(inputStream, Paths.get(uploadDirectory + storageFileName),
						StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		product.setProductName(productDto.getProductName());
		product.setProductBrand(productDto.getProductBrand());
		product.setProductCategory(productDto.getProductCategory());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductCreatedDate(productCreatedDate);
		product.setImageFileName(storageFileName);

		productService.addNewProduct(product);

		return "redirect:/products";
	}
}
