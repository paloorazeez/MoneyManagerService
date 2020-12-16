package com.example.demo.dao.primary;

import com.example.demo.model.primary.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> , ExpenseRepositoryCustom{

    List<Expense> findByUserId(String userId);

    List<Expense> findByUserIdAndCategory(String userId, String category);

    List<Expense> findByUserIdAndExpenseDateBetweenOrderByExpenseDateDesc(String userId, Date from, Date to);
}
