package com.example.demo.service;

import com.example.demo.model.primary.Expense;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IExpenseService {

    Expense add(Expense expense);

    List<Expense> findExpenseForOneMonth(String userId);

    List<Expense> findExpenseForOneWeek(String userId);

    List<Expense> findExpenseForLastThreeMonths(String userId);

    List<Expense> findExpenseForLastSixMonths(String userId);

    List<Expense> findExpenseForLastOneYear(String userId);

    List<Expense> findExpenseForDateRange(String userId, Date startDate, Date endDate);

    List<Expense> findExpensesByDescOrDetail(String userId, String searchWord);

    String delete(List<String> idList);

    List<Expense> findByUserId(String userId);

    List<Expense> findAll();

    List<Expense> findByUserIdAndCategory(String userId, String category);

    Map<String, Object[]> getSummaryByCategory(String userId, Date dateFrom, Date dateTo);

    Map<String, Object[]> getSummaryByCategoryForOneMonth(String userId);

    List<String> getCategoryList(String userId);
}
