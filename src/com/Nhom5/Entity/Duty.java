 
package com.Nhom5.Entity;

 
public class Duty {
    private int idDuty;
    private String nameDuty;
    private String img;

    public Duty() {
    }

    public Duty(int idDuty, String nameDuty, String img) {
        this.idDuty = idDuty;
        this.nameDuty = nameDuty;
        this.img = img;
    }

    
    
    public int getIdDuty() {
        return idDuty;
    }

    public void setIdDuty(int idDuty) {
        this.idDuty = idDuty;
    }

    public String getNameDuty() {
        return nameDuty;
    }

    public void setNameDuty(String nameDuty) {
        this.nameDuty = nameDuty;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
}
