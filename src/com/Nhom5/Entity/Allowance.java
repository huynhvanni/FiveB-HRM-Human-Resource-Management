 
package com.Nhom5.Entity;

import java.util.Date;

 
public class Allowance {
    private String idAllowance;
    private String idTA;
    private String nameTA;
    private String idEmployee;
    private String nameEmployee;
    private String imgEmployee;
    private String dateAllowance;
    private int money;
    private String content;
    
    
    public Allowance() {
    }

    public Allowance(String idAllowance, String idTA, String nameTA, String idEmployee, String nameEmployee, String imgEmployee, String dateAllowance, int money, String content) {
        this.idAllowance = idAllowance;
        this.idTA = idTA;
        this.nameTA = nameTA;
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.imgEmployee = imgEmployee;
        this.dateAllowance = dateAllowance;
        this.money = money;
        this.content = content;
    }

    public String getIdAllowance() {
        return idAllowance;
    }

    public void setIdAllowance(String idAllowance) {
        this.idAllowance = idAllowance;
    }

    public String getIdTA() {
        return idTA;
    }

    public void setIdTA(String idTA) {
        this.idTA = idTA;
    }

    public String getNameTA() {
        return nameTA;
    }

    public void setNameTA(String nameTA) {
        this.nameTA = nameTA;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getImgEmployee() {
        return imgEmployee;
    }

    public void setImgEmployee(String imgEmployee) {
        this.imgEmployee = imgEmployee;
    }

    public String getDateAllowance() {
        return dateAllowance;
    }

    public void setDateAllowance(String dateAllowance) {
        this.dateAllowance = dateAllowance;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    
}
