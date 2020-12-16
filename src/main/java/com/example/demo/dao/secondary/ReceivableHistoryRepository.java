package com.example.demo.dao.secondary;

import com.example.demo.model.secondary.ReceivableHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReceivableHistoryRepository extends MongoRepository<ReceivableHistory, String> {
}
