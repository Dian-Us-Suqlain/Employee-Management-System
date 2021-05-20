package com.example.employeemanagementsystem.ModelClass;

public class Admin {
    private String adminName;
    private String companyName;
    private String adminDesign;
    private String adminMobNo;
    private String adminEmail;

    public Admin(){ }

    public Admin(String adminName, String companyName, String adminDesign, String adminMobNo, String adminEmail) {
        this.adminName = adminName;
        this.companyName = companyName;
        this.adminDesign = adminDesign;
        this.adminMobNo = adminMobNo;
        this.adminEmail = adminEmail;
    }

    public String getAdminName() { return adminName; }

    public void setAdminName(String adminName) { this.adminName = adminName; }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getAdminDesign() { return adminDesign; }

    public void setAdminDesign(String adminDesign) { this.adminDesign = adminDesign; }

    public String getAdminMobNo() { return adminMobNo; }

    public void setAdminMobNo(String adminMobNo) { this.adminMobNo = adminMobNo; }

    public String getAdminEmail() { return adminEmail; }

    public void setAdminEmail(String adminEmail) { this.adminEmail = adminEmail; }
}
