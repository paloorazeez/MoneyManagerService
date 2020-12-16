package com.example.demo.dao.primary;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IncomeRepositoryCustom {
    Map<String, Object[]> getSummaryByCategory(String userId, Date dateFrom, Date dateTo);

    List<String> getCateyList(String userId);
}
