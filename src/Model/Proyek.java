/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author The Keong's
 */
public class Proyek {
    private String idProyek;
    private String judulProyek;
    private String detail;
    private String status;
    private String frontEnd;
    private String backEnd;
    private String database;

    public Proyek(String idProyek, String judulProyek, String detail, String frontEnd, String backEnd, String database) {
        this.idProyek = idProyek;
        this.judulProyek = judulProyek;
        this.detail = detail;
        this.frontEnd = frontEnd;
        this.backEnd = backEnd;
        this.database = database;
    }

    public Proyek(String idProyek, String judulProyek, String detail, String status, String frontEnd, String backEnd, String database) {
        this.idProyek = idProyek;
        this.judulProyek = judulProyek;
        this.detail = detail;
        this.status = status;
        this.frontEnd = frontEnd;
        this.backEnd = backEnd;
        this.database = database;
    }

    

    public String getIdProyek() {
        return idProyek;
    }

    public void setIdProyek(String idProyek) {
        this.idProyek = idProyek;
    }

    public String getJudulProyek() {
        return judulProyek;
    }

    public void setJudulProyek(String judulProyek) {
        this.judulProyek = judulProyek;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrontEnd() {
        return frontEnd;
    }

    public void setFrontEnd(String frontEnd) {
        this.frontEnd = frontEnd;
    }

    public String getBackEnd() {
        return backEnd;
    }

    public void setBackEnd(String backEnd) {
        this.backEnd = backEnd;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
    
    
}
