package com.example.demo.resource;

import com.example.demo.model.primary.Income;
import com.example.demo.model.primary.IncomeList;
import com.example.demo.model.primary.Receivable;
import com.example.demo.model.primary.ReceivableList;
import com.example.demo.service.IReceivableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/receivables")
public class ReceivableController {

    @Autowired
    private IReceivableService receivableService;

    @PostMapping
    public Receivable add(@RequestBody Receivable receivable){

        return receivableService.add(receivable);
    }

    @GetMapping(value = "/findReceivableForOneMonth")
    public List<Receivable> getReceivableForOneMonth(@RequestParam String userId)
    {
        return receivableService.findReceivableForOneMonth(userId);
    }


    @GetMapping(value = "/findReceivableForDateRange")
    public List<Receivable> getReceivableForDateRange(@RequestParam String userId, @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateTo)
    {
        return receivableService.findReceivableForDateRange(userId, dateFrom, dateTo);
    }


    @DeleteMapping
    public void deleteIncome(@RequestBody ReceivableList receivableList)
    {

        System.out.println(receivableList);
        if(receivableList == null || CollectionUtils.isEmpty(receivableList.getReceivableList() ))
            return;
        List<String> idList = receivableList.getReceivableList().stream().map(income -> income.getId()).collect(Collectors.toList());
        receivableService.delete(idList);

    }

    @GetMapping(value = "/findSummaryByReceivableFrom")
    public Map<String, Object[]> getSummaryByRecFrom(@RequestParam String userId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date dateTo)
    {
        return receivableService.getSummaryByReceiveFrom(userId, dateFrom, dateTo);
    }

    @GetMapping(value = "/findSummaryByReceivableOneMonth")
    public Map<String, Object[]> getSummaryByRecFromForOneMonth(@RequestParam String userId)
    {
        return receivableService.getSummaryByPReceiveFromForOneMonth(userId);
    }

    @GetMapping(value = "/findReceivableFromList")
    public List<String> getReceivableFromList(@RequestParam String userId)
    {
        return receivableService.getReceiveFromList(userId);
    }
}
