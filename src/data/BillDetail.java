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
public class BillDetail {
    private int billId;
    private int productId;
    private int saleQuantity;
    private String mfgDate;
    private String expDate;

    public BillDetail() {
    }

    public BillDetail(int billId, int productId, int saleQuantity, String mfgDate, String expDate) {
        this.billId = billId;
        this.productId = productId;
        this.saleQuantity = saleQuantity;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
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

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
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
