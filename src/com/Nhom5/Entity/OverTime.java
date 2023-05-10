package com.Nhom5.Entity;

import java.util.Date;

public class OverTime {

    private String idOverTime;
    private String dateOverTime;
    private float hoursNumber;
    private String idEmployee;
    private String nameEmployee;
    private String imgEmployee;
    private String idTypeShift;
    private String nameTypeShift;
    private int money;

    public String getImgEmployee() {
        return imgEmployee;
    }

    public void setImgEmployee(String imgEmployee) {
        this.imgEmployee = imgEmployee;
    }

    public OverTime(String idOverTime, String dateOverTime, float hoursNumber, String idEmployee, String nameEmployee, String idTypeShift, String nameTypeShift, int money) {
        this.idOverTime = idOverTime;
        this.dateOverTime = dateOverTime;
        this.hoursNumber = hoursNumber;
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.idTypeShift = idTypeShift;
        this.nameTypeShift = nameTypeShift;
        this.money = money;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public OverTime() {
    }

    public OverTime(String idOverTime, String dateOverTime, float hoursNumber, String idEmployee, String idTypeShift, String nameTypeShift, int money) {
        this.idOverTime = idOverTime;
        this.dateOverTime = dateOverTime;
        this.hoursNumber = hoursNumber;
        this.idEmployee = idEmployee;
        this.idTypeShift = idTypeShift;
        this.nameTypeShift = nameTypeShift;
        this.money = money;
    }

    public String getIdOverTime() {
        return idOverTime;
    }

    public void setIdOverTime(String idOverTime) {
        this.idOverTime = idOverTime;
    }

    public String getDateOverTime() {
        return dateOverTime;
    }

    public void setDateOverTime(String dateOverTime) {
        this.dateOverTime = dateOverTime;
    }

    public float getHoursNumber() {
        return hoursNumber;
    }

    public void setHoursNumber(float hoursNumber) {
        this.hoursNumber = hoursNumber;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getIdTypeShift() {
        return idTypeShift;
    }

    public void setIdTypeShift(String idTypeShift) {
        this.idTypeShift = idTypeShift;
    }

    public String getNameTypeShift() {
        return nameTypeShift;
    }

    public void setNameTypeShift(String nameTypeShift) {
        this.nameTypeShift = nameTypeShift;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
