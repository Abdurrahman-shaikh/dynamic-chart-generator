/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Abdur Rahman
 */
public class AdminLogin implements Model{

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctxt) {
        
        Dao md = Dao.getInstance();
        try{
            String duration = request.getParameter("dur");
            Statement st = md.getConnection();
            String query = "";
            switch (duration) {
                case "pm":
                    query = "SELECT email, sum(TIMESTAMPDIFF(SECOND, login_time, logout_time)) FROM tracking_info WHERE status = 1 AND (MONTH(logout_time) = MONTH(NOW())-1) AND (YEAR(logout_time) = YEAR(NOW())) GROUP BY email";
                            break;
                case "cm":
                    query = "SELECT email, sum(TIMESTAMPDIFF(SECOND, login_time, logout_time)) FROM tracking_info WHERE status = 1 AND (MONTH(logout_time) = MONTH(NOW())) AND (YEAR(logout_time) = YEAR(NOW())) GROUP BY email";
                            break;
                case "py":
                    query = "SELECT email, sum(TIMESTAMPDIFF(SECOND, login_time, logout_time)) FROM tracking_info WHERE status = 1 AND (YEAR(logout_time) = YEAR(NOW())-1) GROUP BY email";
                            break;
                case "cy":
                    query = "SELECT email, sum(TIMESTAMPDIFF(SECOND, login_time, logout_time)) FROM tracking_info WHERE status = 1 AND (YEAR(logout_time) = YEAR(NOW())) GROUP BY email";
                            break;
                default:
                            break;
            }
            ResultSet rs = md.getData(st, query);

            JSONObject obj = new JSONObject();
            JSONArray list = new JSONArray();
            
            while (rs.next()) {
                JSONObject temp = new JSONObject();
                temp.put("Email",rs.getString(1));
                temp.put("Duration", rs.getString(2));
                list.add(temp);
            }
            obj.put("data",list);
            String str = obj.toJSONString();
            
            request.setAttribute("data", str);
            
            response.getWriter().write(str);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
