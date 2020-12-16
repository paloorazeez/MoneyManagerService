package com.example.demo.resource;


import com.example.demo.model.primary.User;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/signup")
    public User signupUser(@RequestBody User user)
    {
        return userService.signupUser(user);
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id)
    {
        return userService.findById(id);
    }
}
