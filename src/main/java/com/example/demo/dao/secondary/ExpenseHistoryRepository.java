package com.example.demo.dao.secondary;

import com.example.demo.model.secondary.ExpenseHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseHistoryRepository extends MongoRepository<ExpenseHistory, String> {
}
