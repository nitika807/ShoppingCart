package com.example.shoppingcart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductWebController {
    @Autowired
    ProductController productController;

    @Autowired
    CartController cartController;

    @GetMapping("/productsonline")
    public String getAllCourses(Model model){
        model.addAttribute("product", this.productController.retrieveProducts() );
        return "products";
    }

    @GetMapping("/saveproduct/{id}")
    public String saveProduct(Model model, @PathVariable("id")  int id) {
        model.addAttribute("product", this.cartController.addItems(id));
        return "redirect:/productsonline";
    }

    @GetMapping("/showcartitems")
    public String showCartItems(Model model) {
        model.addAttribute("cart", this.cartController.showCartItems());
        return "cartItems";
    }

    @GetMapping("/addcartitem/{id}")
    public String addcartitem(Model model, @PathVariable("id")  int id) {
        model.addAttribute("product", this.cartController.addItems(id));
        return "redirect:/showcartitems";
    }

    @GetMapping("/updatecart/{id}")
    public String updateCart(Model model, @PathVariable int id){
        model.addAttribute("cart",this.cartController.deleteCart(id));
        return "redirect:/showcartitems";
    }
}
