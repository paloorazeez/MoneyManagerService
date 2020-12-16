package com.example.demo.resource;


import com.example.demo.model.primary.Payable;
import com.example.demo.model.primary.PayableList;
import com.example.demo.service.IPayableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("payables")
public class PayableController {

    @Autowired
    private IPayableService payableService;

    @PostMapping
    public Payable add(@RequestBody Payable payable){

        return payableService.add(payable);
    }

    @GetMapping(value = "/findPayableForOneMonth")
    public List<Payable> getPayableForOneMonth(@RequestParam String userId)
    {
        return payableService.findPayableForOneMonth(userId);
    }


    @GetMapping(value = "/findPayableForDateRange")
    public List<Payable> getPayableForDateRange(@RequestParam String userId, @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateTo)
    {
        return payableService.findPayableForDateRange(userId, dateFrom, dateTo);
    }


    @DeleteMapping
    public void deleteIncome(@RequestBody PayableList payableList)
    {

        System.out.println(payableList);
        if(payableList == null || CollectionUtils.isEmpty(payableList.getPayableList() ))
            return;
        List<String> idList = payableList.getPayableList().stream().map(income -> income.getId()).collect(Collectors.toList());
        payableService.delete(idList);

    }

    @GetMapping(value = "/findSummaryByPayToFrom")
    public Map<String, Object[]> getSummaryByPayTo(@RequestParam String userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateTo)
    {
        return payableService.getSummaryByPayTo(userId, dateFrom, dateTo);
    }

    @GetMapping(value = "/findSummaryByPayToOneMonth")
    public Map<String, Object[]> getSummaryByPayToForOneMonth(@RequestParam String userId)
    {
        return payableService.getSummaryByPayToOneMonth(userId);
    }

    @GetMapping(value = "/findPayToList")
    public List<String> getPayToList(@RequestParam String userId)
    {
        return payableService.getPayToList(userId);
    }
}
