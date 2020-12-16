package com.example.demo.dao.primary;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PayableRepositoryCustom {
    Map<String, Object[]> getSummaryByPayTo(String userId, Date dateFrom, Date dateTo);

    List<String> getPayToList(String userId);
}
