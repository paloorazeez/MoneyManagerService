package com.example.demo.dao.primary;

import com.example.demo.model.primary.Payable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface PayableRepository extends MongoRepository<Payable, String>, PayableRepositoryCustom {
    List<Payable> findByUserIdAndPaymentDateBetweenOrderByPaymentDateDesc(String userId, Date startDate, Date lastDate);
}
