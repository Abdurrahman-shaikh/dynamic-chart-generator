/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.ResultSet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Statement;

/**
 *
 * @author Abdur Rahman
 */
public class RegistrationModel implements Model{
    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctx){
        String mobileno = request.getParameter("mobileno");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
            
        Dao md = Dao.getInstance();
        
        try (PrintWriter out = response.getWriter()){
            Statement st = md.getConnection();
            
            int gen = -1;
            if(gender.equals("Male")) gen = 1;
            else if(gender.equals("Female")) gen = 0;
            
            String getUid = "SELECT MAX(uid) FROM user_table";
            ResultSet rs = md.getData(st, getUid);
            int id;
            if(rs.next()){
                id = rs.getInt(1);
            }
            else{
                id = 0;
            }
            id++;
            
            String queryLogin = "INSERT INTO login_table(email, password, status, role_id) VALUES('"+email+"','"+pass+"', 0, 1)";
            md.storeData(st, queryLogin);
            
            String queryUser = "INSERT INTO user_table(uid, firstname, lastname, dob, mobileno, gender, email) VALUES('"+id+"','"+fname+"','"+lname+"','"+dob+"','"+mobileno+"','"+gen+"','"+email+"')";
            md.storeData(st,queryUser);
            
            MailSender ms = new MailSender();
            ms.sendMail(email);
            
            request.getRequestDispatcher("/WEB-INF/page/registrationjsp.jsp").forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
