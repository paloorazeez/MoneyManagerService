package com.example.demo.dao.primary;

import com.example.demo.model.primary.Expense;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseRepositoryImpl implements  ExpenseRepositoryCustom {

    @Autowired
    private MongoTemplate primaryMongoTemplate;

    @Override
    public List<Expense> findExpensesByDescOrDetail(String userId, String searchWord) {

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingAny(searchWord);

        TextQuery query = TextQuery.queryText(textCriteria);
        query.addCriteria(Criteria.where("user.id").is(userId));
        return primaryMongoTemplate.find(query, Expense.class);
    }

    @Override
    public Map<String, Object[]> getSummaryByCategory(String userId, Date dateFrom, Date dateTo) {

        Map<String, Object[]> responseMap = new HashMap<>();
        MatchOperation matchStage = Aggregation.match( Criteria.where("user.$id").is(new ObjectId(userId)).and("expenseDate").gte(dateFrom).lte(dateTo) );
        //MatchOperation matchStage = Aggregation.match( Criteria.where("user.$id").is(new ObjectId(userId)));
        GroupOperation groupingStage = Aggregation.group("category").sum("amount").as("totalAmount");

        Aggregation aggregation = Aggregation.newAggregation( matchStage, groupingStage);

        AggregationResults<Document> expense = primaryMongoTemplate.aggregate(aggregation, "expense", Document.class);
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
    public List<String> getCategories(String userId) {

        Query query = new Query(Criteria.where("user.$id").is(new ObjectId(userId)));
        List<String> categoryList = primaryMongoTemplate.findDistinct(query, "category", Expense.class, String.class);

        return categoryList;
    }


}
