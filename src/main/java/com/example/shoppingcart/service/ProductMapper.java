//package com.example.shoppingcart.service;
//
//import com.example.shoppingcart.model.Product;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ProductMapper implements RowMapper<Product> {
//
//    @Override
//
//    public Product mapRow(ResultSet rs, int id) throws SQLException{
//        Product product = new Product();
//        product.setId(rs.getInt(1));
//        product.setName(rs.getString(2));
//        product.setDesc(rs.getString(3));
//        product.setPrice(rs.getInt(4));
//        product.setImgPath(rs.getString(5));
//        product.setQtyAvailable(rs.getInt(6));
//        return product;
//    }
//}
