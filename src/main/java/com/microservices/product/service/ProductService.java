package com.microservices.product.service;

import com.microservices.product.dto.ProductRequest;
import com.microservices.product.dto.ProductResponse;
import com.microservices.product.model.Product;
import com.microservices.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

     public ProductResponse createProduct(ProductRequest request){
         Product product = new Product();
         product.setName(request.name());
         product.setDescription(request.description());
         product.setPrice(request.price());
         productRepository.save(product);
         return new ProductResponse(product.getName(), product.getDescription(), product.getPrice());

     }

    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }
}
