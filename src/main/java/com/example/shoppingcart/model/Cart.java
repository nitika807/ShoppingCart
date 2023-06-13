package com.example.shoppingcart.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Document(collection = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    int product_id;
    String name;
    String desc;
    int price;
    String imgPath;
    int qtyAvailable;
    int id;
    int qty;
}
