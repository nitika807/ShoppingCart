package com.example.shoppingcart.repository;

import com.example.shoppingcart.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserRepo extends MongoRepository<UserInfo,  Integer> {

    @Query("{ 'userName' : ?0 }")
    List<UserInfo> findAllByUserName(String username);

}
