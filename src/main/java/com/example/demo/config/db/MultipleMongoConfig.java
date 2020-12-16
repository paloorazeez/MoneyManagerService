package com.example.demo.config.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.internal.MongoClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoDatabaseFactorySupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

@EnableConfigurationProperties(MultipleMongoProperties.class)
@Configuration
@ComponentScan(basePackages = {"com.example.demo"})
public class MultipleMongoConfig {

    @Autowired
    private  MultipleMongoProperties mongoProperties;

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() throws Exception {
        return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
    }

    @Bean
    @Primary
    public MongoDatabaseFactory primaryFactory(MongoProperties mongo) {
        return new SimpleMongoClientDatabaseFactory("mongodb://"+mongo.getHost()+":"+mongo.getPort()+"/"+mongo.getDatabase());
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
    }


    @Bean
    public MongoDatabaseFactory secondaryFactory(MongoProperties mongo) {
        return new SimpleMongoClientDatabaseFactory("mongodb://"+mongo.getHost()+":"+mongo.getPort()+"/"+mongo.getDatabase());
    }

}
