package com.example.myapplication.Model;

public class Users
{
    private String gst_number, password,company_name;
    public Users()
    {

    }
    public Users(String gst_number, String password,String company_name)
    {
        this.gst_number = gst_number;
        this.password = password;
        this.company_name = company_name;
    }
    public String getCompany_name()
    {
        return company_name;
    }
    public void setCompany_name(String company_name)
    {
        this.company_name = company_name;
    }
    public String getGst_number() {

        return gst_number;
    }

    public void setGst_number(String gst_number) {

        this.gst_number = gst_number;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
