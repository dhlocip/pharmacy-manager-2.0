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
public class ImportBatch {
    private int importId;
    private int userId;
    private String date;

    public ImportBatch(int userId) {
        this.userId = userId;
    }
    
    public ImportBatch(int importId, String date) {
        this.importId = importId;
        this.date = date;
    }

    public ImportBatch(int importId, int userId, String date) {
        this.importId = importId;
        this.userId = userId;
        this.date = date;
    }

    public int getImportId() {
        return importId;
    }

    public void setImportId(int importId) {
        this.importId = importId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
