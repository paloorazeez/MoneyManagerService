package com.example.demo.dao.primary;

import com.example.demo.model.primary.Income;
import com.example.demo.model.primary.Receivable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceivableRepositoryImpl implements  ReceivableRepositoryCustom {

    @Autowired
    private MongoTemplate primaryMongoTemplate;


    @Override
    public Map<String, Object[]> getSummaryByReceiveFrom(String userId, Date dateFrom, Date dateTo) {
        Map<String, Object[]> responseMap = new HashMap<>();
        MatchOperation matchStage = Aggregation.match( Criteria.where("user.$id").is(new ObjectId(userId)).and("receivableDate").gte(dateFrom).lte(dateTo) );
        GroupOperation groupingStage = Aggregation.group("receiveFrom").sum("amount").as("totalAmount");

        Aggregation aggregation = Aggregation.newAggregation( matchStage, groupingStage);

        AggregationResults<Document> expense = primaryMongoTemplate.aggregate(aggregation, "receivable", Document.class);
        if(expense != null) {

            List<Document> mappedResults = expense.getMappedResults();
            String[] categoryNames = new String[mappedResults.size()];
            Double totalAmount[] = new Double[mappedResults.size()];
            for (int i=0; i<mappedResults.size(); i++)
            {
                categoryNames[i] = (String) mappedResults.get(i).get("_id");
                totalAmount[i] = (Double) mappedResults.get(i).get("totalAmount");

            }
            responseMap.put("categoryArr", categoryNames);
            responseMap.put("totalAmtArr", totalAmount);

        }

        return responseMap;
    }

    @Override
    public List<String> getReceiveFromList(String userId) {
        Query query = new Query(Criteria.where("user.$id").is(new ObjectId(userId)));
        List<String> categoryList = primaryMongoTemplate.findDistinct(query, "receiveFrom", Receivable.class, String.class);

        return categoryList;
    }
}
