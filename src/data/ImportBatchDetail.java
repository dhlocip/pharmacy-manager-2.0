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
public class ImportBatchDetail {
    private int impId;
    private int proId;
    private double price;
    private int quantity;
    private String mfgDate;
    private String expDate;

    public ImportBatchDetail() {
    }

    public ImportBatchDetail(int impId, int proId, double price, int quantity, String mfgDate, String expDate) {
        this.impId = impId;
        this.proId = proId;
        this.price = price;
        this.quantity = quantity;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
    }

    public int getImpId() {
        return impId;
    }

    public void setImpId(int impId) {
        this.impId = impId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(String mfgDate) {
        this.mfgDate = mfgDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
    
    
}
