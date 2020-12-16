package com.example.demo.util;

import com.example.demo.model.primary.User;
import com.example.demo.model.secondary.UserHistory;

public class UserTransformerUtil {

    public static UserHistory transformToHist(User user) {

        UserHistory userHistory = new UserHistory();
        userHistory.setId(user.getId());
        userHistory.setEmail(user.getEmail());
        userHistory.setFirstName(user.getFirstName());
        userHistory.setLastName(user.getLastName());
        userHistory.setMobile(user.getMobile());
        userHistory.setPassword(user.getPassword());
        userHistory.setUsername(user.getUsername());
        return userHistory;
    }
}
