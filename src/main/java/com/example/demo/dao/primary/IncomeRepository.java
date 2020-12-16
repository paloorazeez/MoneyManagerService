package com.example.demo.dao.primary;

import com.example.demo.model.primary.Income;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IncomeRepository extends MongoRepository<Income, String>, IncomeRepositoryCustom {
    List<Income> findByUserIdAndIncomeDateBetweenOrderByIncomeDateDesc(String userId, Date startDate, Date curDate);
}
