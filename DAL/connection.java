package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    protected Connection con;
    public boolean openConnection(){
        try{
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/qlns";
            String username = "root";
            String password = "kizunaai1";
            con = DriverManager.getConnection(dbUrl,username,password);
            return true;
        } catch(Exception e){
            System.out.println("Connection Fail");
            return false;           
        }
    }
    public void closeConnection(){
        try{
            if(con!=null)
                con.close();
        } catch(SQLException ex){
            System.out.print(ex);
        }
    }
    
}
