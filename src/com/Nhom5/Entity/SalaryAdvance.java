 
package com.Nhom5.Entity;
 
import java.util.Date;

public class SalaryAdvance {
    private String idSalaryAdvance;
    private String dateSalaryAdvance;
    private int money;
    private String idEmployee;
    private String content;
    private String nameEmployee;
    private String imgEmployee;

    public SalaryAdvance() {
    }

    public SalaryAdvance(String idSalaryAdvance, String dateSalaryAdvance, int money, String idEmployee, String content, String nameEmployee, String imgEmployee) {
        this.idSalaryAdvance = idSalaryAdvance;
        this.dateSalaryAdvance = dateSalaryAdvance;
        this.money = money;
        this.idEmployee = idEmployee;
        this.content = content;
        this.nameEmployee = nameEmployee;
        this.imgEmployee = imgEmployee;
    }

    public String getImgEmployee() {
        return imgEmployee;
    }

    public void setImgEmployee(String imgEmployee) {
        this.imgEmployee = imgEmployee;
    }

    public SalaryAdvance(String idSalaryAdvance, String dateSalaryAdvance, int money, String idEmployee, String content) {
        this.idSalaryAdvance = idSalaryAdvance;
        this.dateSalaryAdvance = dateSalaryAdvance;
        this.money = money;
        this.idEmployee = idEmployee;
        this.content = content;
    }

    public SalaryAdvance(String idSalaryAdvance, String dateSalaryAdvance, int money, String idEmployee, String content, String nameEmployee) {
        this.idSalaryAdvance = idSalaryAdvance;
        this.dateSalaryAdvance = dateSalaryAdvance;
        this.money = money;
        this.idEmployee = idEmployee;
        this.content = content;
        this.nameEmployee = nameEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    
    
    public String getIdSalaryAdvance() {
        return idSalaryAdvance;
    }

    public void setIdSalaryAdvance(String idSalaryAdvance) {
        this.idSalaryAdvance = idSalaryAdvance;
    }

    public String getDateSalaryAdvance() {
        return dateSalaryAdvance;
    }

    public void setDateSalaryAdvance(String dateSalaryAdvance) {
        this.dateSalaryAdvance = dateSalaryAdvance;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
