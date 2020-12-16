package com.example.demo.dao.primary;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReceivableRepositoryCustom {
    Map<String, Object[]> getSummaryByReceiveFrom(String userId, Date dateFrom, Date dateTo);

    List<String> getReceiveFromList(String userId);
}
