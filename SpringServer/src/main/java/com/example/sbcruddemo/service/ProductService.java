package com.example.sbcruddemo.service;

import com.example.sbcruddemo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

//
@Service
public interface ProductService {

    public Integer saveProduct(Product product);

    public List<Product> getAllProducts();
    public Product getProductById(Integer pNo);

    public void deleteProduct ( Integer pNo);
}
