package com.example.demo.service;

import com.example.demo.dao.primary.UserRepository;
import com.example.demo.dao.secondary.UserHistoryRepository;
import com.example.demo.model.primary.User;
import com.example.demo.model.secondary.UserHistory;
import com.example.demo.util.UserTransformerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements  IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Override
    public User signupUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        System.out.println(user.getId()+" saved...");

        UserHistory userHistory = userHistoryRepository.save(UserTransformerUtil.transformToHist(user));
        System.out.println(userHistory.getId()+" history saved...");

        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
