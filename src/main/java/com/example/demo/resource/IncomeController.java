package com.example.demo.resource;

import com.example.demo.model.primary.Income;
import com.example.demo.model.primary.IncomeList;
import com.example.demo.service.IIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private IIncomeService incomeService;

    @PostMapping
    public Income add(@RequestBody Income income){

        return incomeService.add(income);
    }

    @GetMapping(value = "/findIncomeForOneMonth")
    public List<Income> getIncomeForOneMonth(@RequestParam String userId)
    {
        return incomeService.findIncomeForOneMonth(userId);
    }


    @GetMapping(value = "/findIncomeForDateRange")
    public List<Income> getIncomeForDateRange(@RequestParam String userId, @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateTo)
    {
        return incomeService.findIncomeForDateRange(userId, dateFrom, dateTo);
    }


    @DeleteMapping
    public void deleteIncome(@RequestBody IncomeList incomeList)
    {

        System.out.println(incomeList);
        if(incomeList == null || CollectionUtils.isEmpty(incomeList.getIncomeList()))
            return;
        List<String> idList = incomeList.getIncomeList().stream().map(income -> income.getId()).collect(Collectors.toList());
        incomeService.delete(idList);

    }

    @GetMapping(value = "/findSummaryByCategoty")
    public Map<String, Object[]> getSummaryByCategory(@RequestParam String userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateTo)
    {
        return incomeService.getSummaryByCategory(userId, dateFrom, dateTo);
    }

    @GetMapping(value = "/findSummaryByCatForOneMonth")
    public Map<String, Object[]> getSummaryByCatForOneMonth(@RequestParam String userId)
    {
        return incomeService.getSummaryByCategoryForOneMonth(userId);
    }

    @GetMapping(value = "/findCategoryList")
    public List<String> getCategoryList(@RequestParam String userId)
    {
        return incomeService.getCategoryList(userId);
    }
}
