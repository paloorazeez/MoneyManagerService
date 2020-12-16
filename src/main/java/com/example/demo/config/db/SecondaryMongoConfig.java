package com.example.demo.config.db;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = {"com.example.demo.model.secondary","com.example.demo.dao.secondary"},
        mongoTemplateRef = "secondaryMongoTemplate"
)
public class SecondaryMongoConfig {
}
