 
package com.Nhom5.Entity;

 
public class TableSalary {
    private String idEmployee;
    private String nameEmp;
    private String imgEmp;
    private int salaryBasic;
    private int sumOT;
    private int sumAllowance;
    private int sumAdvance;
    private String date;

    public TableSalary() {
    }

    public TableSalary(String idEmployee, String nameEmp, String imgEmp, int salaryBasic, int sumOT, int sumAllowance, int sumAdvance, String date) {
        this.idEmployee = idEmployee;
        this.nameEmp = nameEmp;
        this.imgEmp = imgEmp;
        this.salaryBasic = salaryBasic;
        this.sumOT = sumOT;
        this.sumAllowance = sumAllowance;
        this.sumAdvance = sumAdvance;
        this.date = date;
    }

    
    
    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmp() {
        return nameEmp;
    }

    public void setNameEmp(String nameEmp) {
        this.nameEmp = nameEmp;
    }

    public String getImgEmp() {
        return imgEmp;
    }

    public void setImgEmp(String imgEmp) {
        this.imgEmp = imgEmp;
    }

    public int getSalaryBasic() {
        return salaryBasic;
    }

    public void setSalaryBasic(int salaryBasic) {
        this.salaryBasic = salaryBasic;
    }

    public int getSumOT() {
        return sumOT;
    }

    public void setSumOT(int sumOT) {
        this.sumOT = sumOT;
    }

    public int getSumAllowance() {
        return sumAllowance;
    }

    public void setSumAllowance(int sumAllowance) {
        this.sumAllowance = sumAllowance;
    }

    public int getSumAdvance() {
        return sumAdvance;
    }

    public void setSumAdvance(int sumAdvance) {
        this.sumAdvance = sumAdvance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
