package com.example.demo.service;

import com.example.demo.model.primary.Income;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IIncomeService {
    Income add(Income income);

    List<Income> findIncomeForOneMonth(String userId);

    List<Income> findIncomeForDateRange(String userId, Date dateFrom, Date dateTo);

    void delete(List<String> idList);

    Map<String, Object[]> getSummaryByCategory(String userId, Date dateFrom, Date dateTo);

    Map<String, Object[]> getSummaryByCategoryForOneMonth(String userId);

    List<String> getCategoryList(String userId);
}
