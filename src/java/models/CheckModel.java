/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Abdur Rahman
 */
public class CheckModel implements Model{
    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctxt){
        try{
            String data = request.getParameter("data");
            String id = request.getParameter("id");
            Dao md = Dao.getInstance();
            Statement st = md.getConnection();
            if(id.equals("em")){
                String Equery = "SELECT * FROM  login_table WHERE email = '"+data+"'";
                ResultSet rs = md.getData(st, Equery);
                if(rs.next()){
                    request.setAttribute("msg", "Email Already Exists!");
                }else{
                    request.setAttribute("msg", "Proceed E!");
                }
            }
            if(id.equals("mb")){
                String Mquery = "SELECT * FROM  user_table WHERE mobileno = '"+data+"'";
                ResultSet rs = md.getData(st, Mquery);
                if(rs.next()){
                    request.setAttribute("msg", "Mobile no alredy Exists!");
                }else{
                    request.setAttribute("msg", "Proceed M!");
                    
                }
            }
            request.getRequestDispatcher("/WEB-INF/page/check.jsp").forward(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
