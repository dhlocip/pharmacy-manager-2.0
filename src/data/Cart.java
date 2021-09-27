/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author sa
 */
public class Cart {
    
    private int userId;
    private int billId;
    private int productId;
    private String productName;
    private int quantity;
    private String unit;
    private double price;
    private double amount;
    private String transactionTime;

    public Cart() {
    }

    public Cart(int userId, int billId, double amount, String transactionTime) {
        this.userId = userId;
        this.billId = billId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }
    
    public Cart(int userId, double amount, String transactionTime) {
        this.userId = userId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }
    
    public Cart(int billId, int productId, String productName, int quantity, String unit, double price, double amount) {
        this.billId = billId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.amount = amount;
    }

    public Cart(int billId, int productId, String productName, int quantity, String unit, double price, double amount, String transactionTime) {
        this.billId = billId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
}
