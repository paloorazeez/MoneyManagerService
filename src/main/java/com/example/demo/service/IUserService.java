package com.example.demo.service;

import com.example.demo.model.primary.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public User signupUser(User user);

    public Optional<User> findById(String id);

    public List<User> findAll();

    public User findByUsername(String username);
}
