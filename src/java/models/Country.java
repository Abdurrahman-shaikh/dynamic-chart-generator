/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Abdur Rahman
 */
public class Country{
    public String country_name;
    public String country_code;
    public void setCountryCode(String country_code){
        this.country_code = country_code;
    }
    public void setCountryName(String country_name){
        this.country_name = country_name;
    }
    public String getCountryCode() {
        return country_code;
    }
    public String getCountryName() {
        return country_name;
    }
}
