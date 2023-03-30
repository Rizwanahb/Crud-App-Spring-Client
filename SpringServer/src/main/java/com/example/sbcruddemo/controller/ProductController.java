package com.example.sbcruddemo.controller;

import com.example.sbcruddemo.entity.Product;
import com.example.sbcruddemo.entity.ProductResponse;
import com.example.sbcruddemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productDetails")    //this annotation is used to show the path
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("/saveProduct")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody Product product){
        Integer id = service.saveProduct(product);
        ProductResponse p =new ProductResponse();
        p.setStatus("true");
        return new ResponseEntity<ProductResponse>(p,HttpStatus.OK);
    }

    @GetMapping("/productList")
    public  ResponseEntity<List<Product>> getAllProducts(){
        List<Product> list = service.getAllProducts();
        return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
    }
    @GetMapping("/getProductById/{pNo}")
    public ResponseEntity<Product> getProductById(@PathVariable ("pNo") Integer pNo){
        Product product = service.getProductById(pNo);
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ProductResponse> updateProduct( @RequestBody Product product ){
        Product productFromDb = service.getProductById(product.getpNo());
        productFromDb.setpName(product.getpName());
        productFromDb.setpPrice(product.getpPrice());
        productFromDb.setpType(product.getpType());
        Integer id = service.saveProduct(productFromDb);
        ProductResponse p =new ProductResponse();
        p.setStatus("true");
        return new ResponseEntity<ProductResponse>( p, HttpStatus.OK );
    }

    @DeleteMapping("/deleteProduct/{pNo}")
        public ResponseEntity<ProductResponse> deleteProduct(@PathVariable("pNo") Integer pNo){
        service.deleteProduct(pNo);
        ProductResponse p =new ProductResponse();
        p.setStatus("true");
        return new ResponseEntity<ProductResponse>(p,HttpStatus.OK);

    }
}
