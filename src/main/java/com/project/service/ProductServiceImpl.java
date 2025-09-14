package com.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dto.ProductRequest;
import com.project.dto.ProductResponse;
import com.project.entity.Product;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	// connect the repository
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository pr) {
        this.productRepository = pr;
    }
    
    
    // create the product method
    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Product p = new Product(request.getName(), request.getDescription(), request.getPrice(), request.getStock());
        Product saved = productRepository.save(p);
        return toResponse(saved);
    }
    
    
    // get prdduct by id here
    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not found on id " + id));
        return toResponse(p);
    }
    
    
    // get all producrts here
    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }
    
    
    // here update the product 
    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product  is  not found on id " + id));
        p.setName(request.getName());
        p.setDescription(request.getDescription());
        p.setPrice(request.getPrice());
        p.setStock(request.getStock());
        Product updated = productRepository.save(p);
        return toResponse(updated);
    }
    
    
    // delete the product here
     
    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product    is not found on id " + id);
        }
        productRepository.deleteById(id);
    }

   
    private ProductResponse toResponse(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getStock());
    }
}
