/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Abdur Rahman
 */
public class LogOutModel implements Model{

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctxt) {
        HttpSession session = request.getSession(false);
        
        String email = (String)session.getAttribute("email");
        
        Dao md = Dao.getInstance();
        
        String logoutQuery = "UPDATE tracking_info SET logout_time = NOW(), status = 1 where( (email = '"+email+"') and (status = 0))";
        try {
            md.storeData(md.getConnection(), logoutQuery);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        session.invalidate();
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
