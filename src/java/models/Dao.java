package models;

import java.sql.*;
import java.io.Serializable;

/**
 *
 * @author Abdur Rahman
 */
public class Dao implements Serializable{
    private Dao(){}
    private static Dao md = null;
    public synchronized static Dao getInstance(){
        if(md == null){
            md = new Dao();
        }
        return md;
    }
    public Statement getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojectdb?useSSL=false&usePublicKeyRetrieval=false","azmi","Aza@#11221");
        return con.createStatement();
    }
    public boolean storeData(Statement st, String query) throws SQLException{
        return st.execute(query);
    }
    public ResultSet getData(Statement st, String query) throws SQLException{
        return st.executeQuery(query);
    }
}

