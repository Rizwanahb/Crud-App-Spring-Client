package com.example.sbcruddemo.service;

import com.example.sbcruddemo.entity.Product;
import com.example.sbcruddemo.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired      //dependency injection mechanism
    private ProductRepository repository;


    @Override
    public Integer saveProduct(Product product) {
        return repository.save(product).getpNo();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(products::add);
        return products;
        //return (List<Product>) repository.findAll();
    }

    @Override
    public Product getProductById(Integer pNo) {
        return repository.findById(pNo).get();
    }

    @Override
    public void deleteProduct(Integer pNo) {
        repository.deleteById(pNo);

    }

}
