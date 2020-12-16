package com.example.demo.model.secondary;

import com.example.demo.model.primary.Status;
import com.example.demo.model.primary.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "payable_hist")
public class PayableHistory {

    @Id
    private String id;


    private String payableId;

    @DBRef
    private UserHistory user;

    private String payTo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;

    private double amount;

    private Status status;

    private String description;

    private String detail;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayableId() {
        return payableId;
    }

    public void setPayableId(String payableId) {
        this.payableId = payableId;
    }

    public UserHistory getUser() {
        return user;
    }

    public void setUser(UserHistory user) {
        this.user = user;
    }

    public String getPayTo() {
        return payTo;
    }

    public void setPayTo(String payTo) {
        this.payTo = payTo;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "PayableHistory{" +
                "id='" + id + '\'' +
                ", payableId='" + payableId + '\'' +
                ", user=" + user +
                ", payTo='" + payTo + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
