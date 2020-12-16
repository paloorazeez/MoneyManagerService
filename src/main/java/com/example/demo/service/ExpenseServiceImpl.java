package com.example.demo.service;

import com.example.demo.dao.primary.ExpenseRepository;
import com.example.demo.dao.secondary.ExpenseHistoryRepository;
import com.example.demo.model.primary.Expense;
import com.example.demo.model.secondary.ExpenseHistory;
import com.example.demo.util.DateUtil;
import com.example.demo.util.UserTransformerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseServiceImpl implements IExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseHistoryRepository expenseHistoryRepository;

    @Override
    public Expense add(Expense expense) {
        if(expense != null && StringUtils.isEmpty(expense.getId()))
        {
            expense.setCreatedDate(new Date());
        }
        expense.setUpdatedDate(new Date());
        expense= expenseRepository.save(expense);
        expenseHistoryRepository.save(transformExpense(expense));

        return expense;
    }

    @Override
    public List<Expense> findExpenseForOneMonth(String userId) {
        Date curDate = new Date();
        Date startDate = DateUtil.getStartOfTheMonth(curDate);
        Date endDate = DateUtil.getLastDayOfMonth(curDate);
        return expenseRepository.findByUserIdAndExpenseDateBetweenOrderByExpenseDateDesc(userId,startDate,endDate);
    }

    @Override
    public List<Expense> findExpenseForOneWeek(String userId) {
        Date curDate = new Date();
        Date startDate = DateUtil.getStartOfTheWeek(curDate);
        return expenseRepository.findByUserIdAndExpenseDateBetweenOrderByExpenseDateDesc(userId,startDate,curDate);
    }

    @Override
    public List<Expense> findExpenseForLastThreeMonths(String userId) {
        return null;
    }

    @Override
    public List<Expense> findExpenseForLastSixMonths(String userId) {
        return null;
    }

    @Override
    public List<Expense> findExpenseForLastOneYear(String userId) {
        return null;
    }

    @Override
    public List<Expense> findExpenseForDateRange(String userId, Date startDate, Date endDate) {
        startDate = DateUtil.setTimeToBeginning(startDate);
        endDate = DateUtil.setTimeToEnding(endDate);
        return expenseRepository.findByUserIdAndExpenseDateBetweenOrderByExpenseDateDesc(userId,startDate,endDate);
    }

    @Override
    public List<Expense> findExpensesByDescOrDetail(String userId, String searchWord) {
        return expenseRepository.findExpensesByDescOrDetail(userId, searchWord);
    }

    @Override
    public String delete(List<String> expenseList) {

        expenseList.stream().forEach(exp->expenseRepository.deleteById(exp));
         return "cNumber expense deleted "+expenseList.size();
    }

    @Override
    public List<Expense> findByUserId(String userId) {
        return expenseRepository.findByUserId(userId);
    }

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> findByUserIdAndCategory(String userId, String category) {
        return expenseRepository.findByUserIdAndCategory(userId, category);
    }

    @Override
    public Map<String, Object[]> getSummaryByCategory(String userId, Date dateFrom, Date dateTo) {
        return expenseRepository.getSummaryByCategory(userId, dateFrom, dateTo);
    }

    @Override
    public Map<String, Object[]> getSummaryByCategoryForOneMonth(String userId) {
        Date curDate = new Date();
        Date startDate = DateUtil.getStartOfTheMonth(curDate);
        Date endDate = DateUtil.getLastDayOfMonth(curDate);
        return expenseRepository.getSummaryByCategory(userId, startDate, endDate);
    }

    @Override
    public List<String> getCategoryList(String userId) {
        return expenseRepository.getCategories(userId);
    }


    private ExpenseHistory transformExpense(Expense expense) {

        ExpenseHistory history = new ExpenseHistory();
        history.setExpenseId(expense.getId());
        history.setUser(UserTransformerUtil.transformToHist(expense.getUser()));
        history.setAmount(expense.getAmount());
        history.setCategory(expense.getCategory());
        history.setCreatedDate(new Date());
        history.setUpdatedDate(new Date());
        history.setDescription(expense.getDescription());
        history.setDetail(expense.getDetail());
        history.setProof(expense.getProof());
        history.setExpenseDate(expense.getExpenseDate());
        return history;
    }
}
