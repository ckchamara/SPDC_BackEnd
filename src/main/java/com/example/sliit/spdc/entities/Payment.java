package com.example.sliit.spdc.entities;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment")
public class Payment {

    @Id
    private String pid;
    private String uid;
    private double amount;
    private double loyaltyPoints;
    private Map<String, Integer> itemsAndCounts;
    private String paymentType;
    private Date paymentDate;
    private Map<String, String> paymentDetails;

    // two payment types:- credit card and dialog post paid.
    public Payment() { }

    public String getPid() {
        return pid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPid(String pid2) {
        this.pid = pid2;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Map<String, Integer> getItemsAndCounts() {
        return itemsAndCounts;
    }

    public void setItemsAndCounts(Map<String, Integer> itemsAndCounts) {
        this.itemsAndCounts = itemsAndCounts;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Map<String, String> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Map<String, String> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}

