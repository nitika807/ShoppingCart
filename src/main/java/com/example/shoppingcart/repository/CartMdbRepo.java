package com.example.shoppingcart.repository;

import com.example.shoppingcart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartMdbRepo extends MongoRepository<Cart, Integer> {
}
