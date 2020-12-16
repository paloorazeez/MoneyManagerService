package com.example.demo.service;

import com.example.demo.dao.primary.PayableRepository;
import com.example.demo.dao.secondary.PayableHistoryRepository;
import com.example.demo.model.primary.Payable;
import com.example.demo.model.primary.Status;
import com.example.demo.model.secondary.PayableHistory;
import com.example.demo.util.DateUtil;
import com.example.demo.util.UserTransformerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PayableServiceImpl implements IPayableService{


    @Autowired
    private PayableRepository payableRepository;

    @Autowired
    private PayableHistoryRepository historyRepository;

    @Override
    public Payable add(Payable payable) {

        if(payable != null && StringUtils.isEmpty(payable.getId()))
        {
            payable.setCreatedDate(new Date());
            payable.setStatus(Status.PENDING);
        }

        payable.setUpdatedDate(new Date());
        payable = payableRepository.save(payable);
        historyRepository.save(transform(payable));

        return payable;
    }

    private PayableHistory transform(Payable payable) {

        PayableHistory history = new PayableHistory();
        history.setPayableId(payable.getId());
        history.setAmount(payable.getAmount());
        history.setDescription(payable.getDescription());
        history.setDetail(payable.getDetail());
        history.setPaymentDate(payable.getPaymentDate());
        history.setPayTo(payable.getPayTo());
        history.setStatus(payable.getStatus());
        history.setUser(UserTransformerUtil.transformToHist(payable.getUser()));
        history.setCreatedDate(new Date());
        history.setUpdatedDate(new Date());
        return history;
    }

    @Override
    public List<Payable> findPayableForOneMonth(String userId) {
        Date curDate = new Date();
        Date startDate = DateUtil.getStartOfTheMonth(curDate);
        Date lastDate = DateUtil.getLastDayOfMonth(curDate);
        return payableRepository.findByUserIdAndPaymentDateBetweenOrderByPaymentDateDesc(userId,startDate,lastDate);
    }

    @Override
    public List<Payable> findPayableForDateRange(String userId, Date dateFrom, Date dateTo) {
        return payableRepository.findByUserIdAndPaymentDateBetweenOrderByPaymentDateDesc(userId,dateFrom,dateTo);
    }

    @Override
    public void delete(List<String> idList) {

        idList.stream().forEach(id->payableRepository.deleteById(id));

    }

    @Override
    public Map<String, Object[]> getSummaryByPayTo(String userId, Date dateFrom, Date dateTo) {
        return payableRepository.getSummaryByPayTo(userId, dateFrom, dateTo);
    }

    @Override
    public Map<String, Object[]> getSummaryByPayToOneMonth(String userId) {
        Date curDate = new Date();
        Date startDate = DateUtil.getStartOfTheMonth(curDate);
        Date lastDate = DateUtil.getLastDayOfMonth(curDate);
        return payableRepository.getSummaryByPayTo(userId, startDate, lastDate);

    }

    @Override
    public List<String> getPayToList(String userId) {
        return payableRepository.getPayToList(userId);
    }
}
