package com.example.demo.resource;

import com.example.demo.model.primary.Expense;
import com.example.demo.model.primary.ExpenseList;
import com.example.demo.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/expenses")
public class ExpenseController {


    @Autowired
    private IExpenseService expenseService;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense)
    {
        return expenseService.add(expense);
    }


    @GetMapping(value = "/findExpenseByUserId")
    public List<Expense> getExpensesByUserId(@RequestParam String userId)
    {
        return expenseService.findByUserId(userId);
    }

    @GetMapping
    public List<Expense> findAll()
    {
        return expenseService.findAll();
    }

    @GetMapping(value = "/findExpenseByUserIdAndCategory")
    public List<Expense> getExpensesByUserIdAndCategory(@RequestParam String userId, @RequestParam String category)
    {
        return expenseService.findByUserIdAndCategory(userId, category);
    }


    @GetMapping(value = "/findExpensesByDescOrDetail")
    public List<Expense> getExpensesByDescOrDetail(@RequestParam String userId, @RequestParam String searchWord)
    {
        return expenseService.findExpensesByDescOrDetail(userId, searchWord);
    }

    @GetMapping(value = "/findExpenseForOneMonth")
    public List<Expense> getExpensesForOneMonth(@RequestParam String userId)
    {
        return expenseService.findExpenseForOneMonth(userId);
    }


    @GetMapping(value = "/findExpenseForDateRange")
    public List<Expense> getExpensesForDateRange(@RequestParam String userId, @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateTo)
    {
        return expenseService.findExpenseForDateRange(userId, dateFrom, dateTo);
    }


    @DeleteMapping
    public void deleteExpense(@RequestBody ExpenseList expenseList)
    {

        System.out.println(expenseList);
        if(expenseList == null || CollectionUtils.isEmpty(expenseList.getExpenseList()))
            return;
        List<String> idList = expenseList.getExpenseList().stream().map(exp -> exp.getId()).collect(Collectors.toList());
         expenseService.delete(idList);

    }

    @GetMapping(value = "/findSummaryByCategoty")
    public Map<String, Object[]> getSummaryByCategory(@RequestParam String userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateTo)
    {
       return expenseService.getSummaryByCategory(userId, dateFrom, dateTo);
    }

    @GetMapping(value = "/findSummaryByCatForOneMonth")
    public Map<String, Object[]> getSummaryByCatForOneMonth(@RequestParam String userId)
    {
        return expenseService.getSummaryByCategoryForOneMonth(userId);
    }

    @GetMapping(value = "/findCategoryList")
    public List<String> getCategoryList(@RequestParam String userId)
    {
        return expenseService.getCategoryList(userId);
    }

}
