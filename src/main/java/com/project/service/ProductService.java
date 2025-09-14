package com.project.service;

import java.util.List;

import com.project.dto.ProductRequest;
import com.project.dto.ProductResponse;

public interface ProductService {

	
	// create a product here
    ProductResponse createProduct(ProductRequest request);
    
    
    //get product by id method
    ProductResponse getProductById(Long id);
    
    // get all products list 
    List<ProductResponse> getAllProducts(); 
    
    // update the product
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
