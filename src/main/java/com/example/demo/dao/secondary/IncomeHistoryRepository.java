package com.example.demo.dao.secondary;

import com.example.demo.model.secondary.IncomeHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IncomeHistoryRepository extends MongoRepository<IncomeHistory, String> {
}
