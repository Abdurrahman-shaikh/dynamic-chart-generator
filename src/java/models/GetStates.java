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
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 *
 * @author Abdur Rahman
 */
public class GetStates implements Model{

    @Override
    public void doBusiness(HttpServletRequest request, HttpServletResponse response, ServletContext ctxt) {
        try{
            
            String country_code = request.getParameter("code");
            
            Dao md = Dao.getInstance();
            Statement st = md.getConnection();
            String query = "SELECT * FROM state where country_code = '"+country_code+"'";
            ResultSet rs = md.getData(st, query);
            
            JSONObject obj = new JSONObject();
            JSONArray list = new JSONArray();
            
            while (rs.next()) {
                JSONObject temp = new JSONObject();
                temp.put(rs.getString("state_id"),rs.getString("state_name"));
                list.add(temp);
            }
            obj.put("states",list);
            String str = obj.toJSONString();
            
            request.setAttribute("states", str);
            
            response.getWriter().write(str);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
