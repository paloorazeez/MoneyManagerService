package com.example.demo.dao.secondary;

import com.example.demo.model.secondary.UserHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends MongoRepository<UserHistory, String> {
}
