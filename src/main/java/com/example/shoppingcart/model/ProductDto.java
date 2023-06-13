package com.example.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;

//@Document(collection = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    String name;
    String desc;
    int price;
    String imgPath;

}
