package com.fin.track.Controllers.Client;

public class Transaction {
    private String purpose;
    private String category;
    private double sum;
    private String date;

    public Transaction(String purpose, String category, double sum, String date) {
        this.purpose = purpose;
        this.category = category;
        this.sum = sum;
        this.date = date;
    }

    // Public getters and setters
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public double getSum() { return sum; }
    public void setSum(double sum) { this.sum = sum; }
    
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
} 