/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Abdur Rahman
 */
public class GetCountries implements Model{
    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctx){
        try{
            Dao md = Dao.getInstance();
            Statement st = md.getConnection();
            String query = "SELECT * FROM country";
            ResultSet rs = md.getData(st, query);
            
            List<Country> countries = new ArrayList<>();
            
            while (rs.next()) {
                String countryCode = rs.getString("country_code");
                String countryName = rs.getString("country_name");

                // Assuming you have a Country class with getters and setters
                Country country = new Country();
                country.setCountryCode(countryCode);
                country.setCountryName(countryName);

                countries.add(country);
            }
            
            request.setAttribute("countries", countries);

            request.getRequestDispatcher("/WEB-INF/page/address.jsp").forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
