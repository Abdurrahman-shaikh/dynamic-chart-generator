/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Abdur Rahman
 */
public class LoginModel implements Model{

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctxt) {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        Dao md = Dao.getInstance();
        try {
            Statement st = md.getConnection();
            String query = "SELECT password, status, role_id FROM login_table WHERE(email = '"+email+"')";
            String Upass;
            int Ustatus;
            int role_id;
            ResultSet rs = md.getData(st, query);
            if(!rs.next()){
                request.setAttribute("error", "Please Register First!");
                request.getRequestDispatcher("/WEB-INF/page/errorjsp.jsp").forward(request, response);
            }
            else {
                Upass = rs.getString(1);
                Ustatus = rs.getInt(2);
                role_id = rs.getInt(3);
                
                if(!pass.equals(Upass)){
                    request.setAttribute("error", "Wrong Password!");
                    request.getRequestDispatcher("/WEB-INF/page/errorjsp.jsp").forward(request, response);
                }
                else if(Ustatus == 0){
                    request.setAttribute("error", "Please Verify Email!");
                    request.getRequestDispatcher("/WEB-INF/page/errorjsp.jsp").forward(request, response);
                }
                else{
                    HttpSession session = request.getSession(true);
                        session.setAttribute("email", email);
                        String trackQuery = "INSERT INTO tracking_info(email, login_time, logout_time, status) VALUES('"+email+"',NOW(), NULL, 0)";
                        md.storeData(st, trackQuery);
                    if(role_id == 1){
                        request.getRequestDispatcher("/WEB-INF/page/userlogin.jsp").forward(request, response);
                    }
                    else{
                        request.getRequestDispatcher("/WEB-INF/page/adminlogin.jsp").forward(request, response);
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
