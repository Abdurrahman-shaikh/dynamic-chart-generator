/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Statement;

/**
 *
 * @author Abdur Rahman
 */
public class EmailVerificationModel implements Model{

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctxt) {
        String email = request.getParameter("email");
        
        String query = "UPDATE login_table SET status = 1 WHERE(email = '"+email+"')";
        
        Dao md = Dao.getInstance();
        
        try (PrintWriter out = response.getWriter()) {
            Statement st = md.getConnection();
            md.storeData(st, query);
            out.println("Verification Complete");
            out.println("Go to <a href='/mp/index.jsp'>Home</a>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
