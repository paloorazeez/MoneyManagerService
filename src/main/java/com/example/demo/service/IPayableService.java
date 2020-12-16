package com.example.demo.service;

import com.example.demo.model.primary.Payable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IPayableService {
    Payable add(Payable payable);

    List<Payable> findPayableForOneMonth(String userId);

    List<Payable> findPayableForDateRange(String userId, Date dateFrom, Date dateTo);

    void delete(List<String> idList);

    Map<String, Object[]> getSummaryByPayTo(String userId, Date dateFrom, Date dateTo);

    Map<String, Object[]> getSummaryByPayToOneMonth(String userId);

    List<String> getPayToList(String userId);
}
