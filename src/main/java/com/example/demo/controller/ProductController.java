package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController 
{
	
	@Autowired
	
	ProductService productService;
	
	/*@GetMapping("/productform")
	public String productForm()
	{
		return "add-product";
	}
	*/
	
	/*@PostMapping("/saveProduct")
	public String saveProduct(ProductModel productModel)
	{
		productService.saveProductDetails(productModel);
		
		return "success";
	}
	*/
	
	@GetMapping("/getAllProducts")
	public String getAllProducts(Model model)
	{
		List<ProductEntity> products=productService.getAllProducts();
		model.addAttribute("products", products);
		return "product-list";
	}
	
	@GetMapping("/searchForm")
	public String searchForm()
	{
		return "search-product";
	}
	
	@PostMapping("/searchById")
	public String searchById(@RequestParam Long id,Model model)
	{
		ProductEntity product=productService.searchById(id);
		model.addAttribute("product",product);
		return "search-product";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable("id") Long id)
	{
		productService.deleteProductById(id);
		return "redirect:/getAllProducts";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
	    ProductModel product = productService.getProductById(id);

	    if (product == null) {
	        model.addAttribute("errorMessage", "Product not found for ID: " + id);
	        return "error"; // Ensure an error.html page exists in src/main/resources/templates
	    }

	    model.addAttribute("product", product);
	    return "edit-form";
	}

	@PostMapping("/editproductsave/{id}")
	public String updateProduct(@PathVariable("id") Long id, ProductModel productModel) {
	    productService.updateProduct(id, productModel);
	    return "redirect:/getAllProducts"; // Redirects to the list of products after the update
	}
	
	@GetMapping("/productform")
	public String getProductForm(Model model)
	{
		ProductModel productModel=new ProductModel();
		productModel.setMadeIn("india");
		productModel.setQuantity(2);
		productModel.setDiscountrate(10.5);
		model.addAttribute("productModel",productModel);
		return "add-product";
	}
	
	
	@PostMapping("/save")
	public String saveProductDetails(@Valid ProductModel productModel, BindingResult bindingresult, Model model) {
		HashMap<String, String> validationErrors=new HashMap<String, String>();
		if(bindingresult.hasErrors())
		{
			for(FieldError fieldError:bindingresult.getFieldErrors())
			{
				validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			model.addAttribute("validationErrors",validationErrors);
			return "add-product";
		}
		
		productService.saveProductDetails(productModel);
		return "redirect:/getAllProducts";
	}
	
}

