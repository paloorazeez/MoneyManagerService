package com.example.demo.service;

import com.example.demo.dao.primary.IncomeRepository;
import com.example.demo.dao.secondary.IncomeHistoryRepository;
import com.example.demo.model.primary.Income;
import com.example.demo.model.secondary.IncomeHistory;
import com.example.demo.util.DateUtil;
import com.example.demo.util.UserTransformerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class IncomeServiceImpl implements IIncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private IncomeHistoryRepository incomeHistoryRepository;


    public Income add(Income income){

        if(income != null && StringUtils.isEmpty(income.getId()))
        {
            income.setCreatedDate(new Date());
        }
        income.setUpdatedDate(new Date());
        income = incomeRepository.save(income);
        incomeHistoryRepository.save(transform(income));

        return income;
    }

    @Override
    public List<Income> findIncomeForOneMonth(String userId) {
        Date curDate = new Date();
        Date startDate = DateUtil.getStartOfTheMonth(curDate);
        Date lastDate = DateUtil.getLastDayOfMonth(curDate);
        return incomeRepository.findByUserIdAndIncomeDateBetweenOrderByIncomeDateDesc(userId,startDate,lastDate);

    }

    @Override
    public List<Income> findIncomeForDateRange(String userId, Date dateFrom, Date dateTo) {
        return incomeRepository.findByUserIdAndIncomeDateBetweenOrderByIncomeDateDesc(userId,dateFrom,dateTo);

    }

    @Override
    public void delete(List<String> idList) {

        idList.stream().forEach(id->incomeRepository.deleteById(id));

    }

    @Override
    public Map<String, Object[]> getSummaryByCategory(String userId, Date dateFrom, Date dateTo) {
        return incomeRepository.getSummaryByCategory(userId, dateFrom, dateTo);
    }

    @Override
    public Map<String, Object[]> getSummaryByCategoryForOneMonth(String userId) {
        Date curDate = new Date();
        Date startDate = DateUtil.getStartOfTheMonth(curDate);
        Date endDate = DateUtil.getLastDayOfMonth(curDate);
        return incomeRepository.getSummaryByCategory(userId, startDate, endDate);
    }

    @Override
    public List<String> getCategoryList(String userId) {
        return incomeRepository.getCateyList(userId);
    }

    private IncomeHistory transform(Income income) {
        IncomeHistory history = new IncomeHistory();
        history.setIncomeId(income.getId());
        history.setCategory(income.getCategory());
        history.setAmount(income.getAmount());
        history.setIncomeDate(income.getIncomeDate());
        history.setDescription(income.getDescription());
        history.setDetail(income.getDetail());
        history.setUser(UserTransformerUtil.transformToHist(income.getUser()));
        history.setProof(income.getProof());
        history.setCreatedDate(new Date());
        history.setUpdatedDate(new Date());
        return history;
    }

}
