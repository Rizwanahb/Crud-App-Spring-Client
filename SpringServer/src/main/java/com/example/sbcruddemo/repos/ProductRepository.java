package com.example.sbcruddemo.repos;

import com.example.sbcruddemo.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//crud repository is an interface
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}