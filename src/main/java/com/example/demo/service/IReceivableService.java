package com.example.demo.service;

import com.example.demo.model.primary.Income;
import com.example.demo.model.primary.Receivable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IReceivableService {
    Receivable add(Receivable receivable);

    List<Receivable> findReceivableForOneMonth(String userId);

    List<Receivable> findReceivableForDateRange(String userId, Date dateFrom, Date dateTo);

    void delete(List<String> idList);

    Map<String, Object[]> getSummaryByReceiveFrom(String userId, Date dateFrom, Date dateTo);

    Map<String, Object[]> getSummaryByPReceiveFromForOneMonth(String userId);

    List<String> getReceiveFromList(String userId);
}
