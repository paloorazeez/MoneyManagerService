package com.example.demo.service;

import com.example.demo.dao.primary.ReceivableRepository;
import com.example.demo.dao.secondary.ReceivableHistoryRepository;
import com.example.demo.model.primary.Income;
import com.example.demo.model.primary.Receivable;
import com.example.demo.model.primary.Status;
import com.example.demo.model.secondary.ReceivableHistory;
import com.example.demo.util.DateUtil;
import com.example.demo.util.UserTransformerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReceivableServiceImpl implements IReceivableService {

    @Autowired
    private ReceivableRepository recRepository;


    @Autowired
    private ReceivableHistoryRepository recHistRepository;


    @Override
    public Receivable add(Receivable receivable) {

        if(receivable != null && StringUtils.isEmpty(receivable.getId()))
        {
            receivable.setCreatedDate(new Date());
            receivable.setPaymentStatus(Status.PENDING);
        }

        receivable.setUpdatedDate(new Date());
        receivable = recRepository.save(receivable);
        recHistRepository.save(transform(receivable));
        return receivable;
    }

    private ReceivableHistory transform(Receivable receivable) {
        ReceivableHistory history = new ReceivableHistory();
        history.setReceivableId(receivable.getId());
        history.setAmount(receivable.getAmount());
        history.setDescription(receivable.getDescription());
        history.setDetail(receivable.getDetail());
        history.setReceivableDate(receivable.getReceivableDate());
        history.setReceiveFrom(receivable.getReceiveFrom());
        history.setUser(UserTransformerUtil.transformToHist(receivable.getUser()));
        history.setCreatedDate(new Date());
        history.setUpdatedDate(new Date());
        history.setPaymentStatus(receivable.getPaymentStatus());
        return history;
    }

    @Override
    public List<Receivable> findReceivableForOneMonth(String userId) {
        Date curDate = new Date();
        Date startDate = DateUtil.getStartOfTheMonth(curDate);
        Date lastDate = DateUtil.getLastDayOfMonth(curDate);
        return recRepository.findByUserIdAndReceivableDateBetweenOrderByReceivableDateDesc(userId,startDate,lastDate);
    }

    @Override
    public List<Receivable> findReceivableForDateRange(String userId, Date dateFrom, Date dateTo) {
        return recRepository.findByUserIdAndReceivableDateBetweenOrderByReceivableDateDesc(userId,dateFrom,dateTo);

    }

    @Override
    public void delete(List<String> idList) {

        idList.stream().forEach(id->recRepository.deleteById(id));
    }

    @Override
    public Map<String, Object[]> getSummaryByReceiveFrom(String userId, Date dateFrom, Date dateTo) {
        return recRepository.getSummaryByReceiveFrom(userId, dateFrom, dateTo);
    }

    @Override
    public Map<String, Object[]> getSummaryByPReceiveFromForOneMonth(String userId) {
        Date curDate = new Date();
        Date startDate = DateUtil.getStartOfTheMonth(curDate);
        Date endDate = DateUtil.getLastDayOfMonth(curDate);
        return recRepository.getSummaryByReceiveFrom(userId, startDate, endDate);
    }

    @Override
    public List<String> getReceiveFromList(String userId) {
        return recRepository.getReceiveFromList(userId);
    }
}
