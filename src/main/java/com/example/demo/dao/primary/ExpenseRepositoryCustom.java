package com.example.demo.dao.primary;

import com.example.demo.model.primary.Expense;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExpenseRepositoryCustom {


    List<Expense> findExpensesByDescOrDetail(String userId, String searchWord);

    Map<String, Object[]> getSummaryByCategory(String userId, Date dateFrom, Date dateTo);

    List<String> getCategories(String userId);
}
