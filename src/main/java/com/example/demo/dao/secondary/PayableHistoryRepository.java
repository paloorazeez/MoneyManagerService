package com.example.demo.dao.secondary;

import com.example.demo.model.secondary.PayableHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PayableHistoryRepository extends MongoRepository<PayableHistory, String> {
}
