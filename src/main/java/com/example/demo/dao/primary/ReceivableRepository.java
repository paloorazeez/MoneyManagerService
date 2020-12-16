package com.example.demo.dao.primary;

import com.example.demo.model.primary.Receivable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ReceivableRepository extends MongoRepository<Receivable, String>, ReceivableRepositoryCustom {
    List<Receivable> findByUserIdAndReceivableDateBetweenOrderByReceivableDateDesc(String userId, Date startDate, Date lastDate);
}
