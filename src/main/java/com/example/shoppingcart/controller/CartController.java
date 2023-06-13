package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.Cart;
import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.CartMdbRepo;
import com.example.shoppingcart.repository.ProductMdbRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CartController {
    @Autowired
    private CartMdbRepo cartMdbRepo;

    @Autowired
    ProductController productController;

    @Autowired
    ProductMdbRepo productMdbRepo;
    @Autowired
    Cart cart;


    @GetMapping("/showitems")
    public List<Cart> showCartItems() {
        return this.cartMdbRepo.findAll();
    }

    @PostMapping("/additems/{id}")
    public ResponseEntity<Cart> addItems(@PathVariable int id) {
        Optional<Product> product = this.productMdbRepo.findById(id);
        Optional<Cart> existingCart = this.cartMdbRepo.findById(id);
            if (existingCart.isPresent()) {
                Cart cartUpdate = existingCart.get();
                cartUpdate.setQty(cartUpdate.getQty()+1);
                cartUpdate.setPrice(product.get().getPrice()*cartUpdate.getQty());
                this.cartMdbRepo.save(cartUpdate);
            } else {
                Cart cart = new Cart();
                cart.setId(product.get().getId());
                cart.setName(product.get().getName());
                cart.setPrice(product.get().getPrice());
                cart.setProduct_id(product.get().getId());
                cart.setQty(1);
                this.cartMdbRepo.save(cart);
            }

        return ResponseEntity.created(null).build();
    }

        @DeleteMapping("/deleteitem/{id}")
        public ResponseEntity<Cart> deleteCart(@PathVariable int id){
            Optional<Product> product = this.productMdbRepo.findById(id);
            Optional<Cart> optionalCart = this.cartMdbRepo.findById(id);
            if (optionalCart.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            if(optionalCart.isPresent()){
                Cart cartProd = optionalCart.get();
                cartProd.setQty(cartProd.getQty()-1);
                cartProd.setPrice(product.get().getPrice()*cartProd.getQty());
                this.cartMdbRepo.save(cartProd);
            }
            if(optionalCart.get().getQty()==0){
                this.cartMdbRepo.delete(optionalCart.get());
            }
            return ResponseEntity.created(null).build();
        }
}
