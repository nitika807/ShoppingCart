package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.model.ProductDto;
import com.example.shoppingcart.repository.ProductMdbRepo;
//import com.example.shoppingcart.service.ProductDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductMdbRepo productMdbRepo;
    @Autowired
    ModelMapper modelMapper;


    @GetMapping("/products")//requestMapping can be applied on
    //class as well as method level
    public List<Product> retrieveProducts(){
        return this.productMdbRepo.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id){
        return this.productMdbRepo.findById(id).get();
    }


    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProducts(@RequestBody Product product){
        this.productMdbRepo.save(product);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return ResponseEntity.created(null).body(productDto);
    }

    @GetMapping("/specprod/{id}")
    public ProductDto specProd(@PathVariable int id){
        Product product = getProductById(id);
        return modelMapper.map(product, ProductDto.class);

    }

    @DeleteMapping("/productsdel/{id}")
    public ResponseEntity<Product> removeProduct(@PathVariable int id){
        Optional<Product> optionalProduct = this.productMdbRepo.findById(id);
       if(optionalProduct.isEmpty()){
           return ResponseEntity.notFound().build();
       }
       Product product = optionalProduct.get();
       this.productMdbRepo.delete(product);
       return ResponseEntity.created(null).body(product);
    }

    @PutMapping("/prodUpdate")
    public ResponseEntity<Product> prodUpdate(@RequestBody Product product){
        Optional<Product> optionalProduct = this.productMdbRepo.findById(product.getId());
        if(optionalProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Product newProduct = optionalProduct.get();
        newProduct.setName(product.getName());
        newProduct.setDesc(product.getDesc());
        newProduct.setPrice(product.getPrice());
        newProduct.setImgPath(product.getImgPath());

        this.productMdbRepo.save(newProduct);
        return ResponseEntity.created(null).body(newProduct);
    }
    @PatchMapping ("/partUpdate")
    public ResponseEntity<Product> part(@RequestBody Product product){
        Optional<Product> optionalProduct = this.productMdbRepo.findById(product.getId());
        if(optionalProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Product newProduct = optionalProduct.get();
        if(product.getName()!=null)
        newProduct.setName(product.getName());
        if(product.getDesc()!=null)
        newProduct.setDesc(product.getDesc());
        if(product.getPrice()!=0)
        newProduct.setPrice(product.getPrice());
        if(product.getImgPath()!=null)
        newProduct.setImgPath(product.getImgPath());

        this.productMdbRepo.save(newProduct);
        return ResponseEntity.created(null).body(newProduct);
    }
}
