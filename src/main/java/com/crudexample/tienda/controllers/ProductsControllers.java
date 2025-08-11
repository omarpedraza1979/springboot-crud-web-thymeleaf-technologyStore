package com.crudexample.tienda.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

import com.crudexample.tienda.models.Product;
import com.crudexample.tienda.models.ProductDto;
import com.crudexample.tienda.services.ProductsRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsControllers {

	public ProductsControllers() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private ProductsRepository repo;

	@GetMapping({ "", "/" })
	public String showProductList(Model model) {
		List<Product> productsList = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
		model.addAttribute("products", productsList);
		return "products/index";
	}

	@GetMapping("/create")
	public String showCreatePage(Model model) {

		ProductDto productDto = new ProductDto();
		model.addAttribute("productDto", productDto);
		return "products/CreateProduct";
	}

	@PostMapping("/create")
	public String createProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result) {

		if (productDto.getImageFile().isEmpty()) {
			result.addError(new FieldError("productDto", "imageFile", "the imageFile is empty"));
		}

		if (result.hasErrors()) {
			return "products/CreateProduct";
		}

		MultipartFile image = productDto.getImageFile();
		Date createAt = new Date();
		String storageFilename = createAt.getTime() + "_" + image.getOriginalFilename();

		try {
			String uploaddDir = "public/images/";
			Path uploadPath = Paths.get(uploaddDir);

			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			try (InputStream inputStream = image.getInputStream()) {
				Files.copy(inputStream, Paths.get(uploaddDir + storageFilename), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
		}

		Product product = new Product();
		product.setName(productDto.getName());
		product.setBrand(productDto.getBrand());
		product.setCategory(productDto.getCategory());
		product.setPrice(String.valueOf(productDto.getPrice()));
		product.setDescription(productDto.getDescription());
		product.setCreatedAt(createAt);
		product.setImagefilename(storageFilename);

		repo.save(product);

		return "redirect:/products";
	}

	@GetMapping("/edit")
	public String showEditPage(Model model, @RequestParam int id) {

		try {

			Product product = repo.findById(id).get();
			model.addAttribute("product", product);

			ProductDto productDto = new ProductDto();

			productDto.setName(product.getName());
			productDto.setBrand(product.getBrand());
			productDto.setCategory(product.getCategory());
			productDto.setPrice(Double.parseDouble(product.getPrice()));
			productDto.setDescription(product.getDescription());

			/*
			 * productdto.setCreatedAt(createAt);
			 * productdto.setImagefilename(storageFilename);
			 */
			model.addAttribute("productDto", productDto);

		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
			return "redirect:/products";
		}
		return "products/EditProduct";
	}

	@PostMapping("/edit")
	public String updateProduct(Model model, @RequestParam int id, @Valid @ModelAttribute ProductDto productDto,
			BindingResult result) {
		try {

			Product product = repo.findById(id).get();
			model.addAttribute("product", product);

			if (result.hasErrors()) {
				return "products/EditProduct";
			}

			if (!productDto.getImageFile().isEmpty()) {
				String uploaddDir = "public/images/";
				Path oldImagePath = Paths.get(uploaddDir + product.getImagefilename());

				try {
					Files.delete(oldImagePath);
				} catch (Exception ex) {
					System.out.println("Exception : " + ex.getMessage());
				}

				MultipartFile image = productDto.getImageFile();
				Date createAt = new Date();
				String storageFilename = createAt.getTime() + "_" + image.getOriginalFilename();

				try (InputStream inputStream = image.getInputStream()) {
					Files.copy(inputStream, Paths.get(uploaddDir + storageFilename),
							StandardCopyOption.REPLACE_EXISTING);
				}

				product.setImagefilename(storageFilename);
			}

			product.setName(productDto.getName());
			product.setBrand(productDto.getBrand());
			product.setCategory(productDto.getCategory());
			product.setPrice(String.valueOf(productDto.getPrice()));
			product.setDescription(productDto.getDescription());

			repo.save(product);

		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
		}

		return "redirect:/products";
	}

	@GetMapping("/delete")
	public String deleteProduct(Model model, @RequestParam int id) {

		try {

			Product product = repo.findById(id).get();
			// model.addAttribute("product", product);

			String uploaddDir = "public/images/";
			Path imagePath = Paths.get(uploaddDir + product.getImagefilename());

			try {
				Files.delete(imagePath);
			} catch (Exception ex) {
				System.out.println("Exception : " + ex.getMessage());
			}

			repo.delete(product);

		} catch (Exception ex) {
			System.out.println("Exception : " + ex.getMessage());
		}
		return "redirect:/products";
	}

}
