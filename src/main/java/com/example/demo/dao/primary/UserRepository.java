package com.example.demo.dao.primary;

import com.example.demo.model.primary.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User,String>{
    public User findByUsername(String username);
}
