package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DTO.LoginDTO;

public class LoginDAL extends connection {
    
    public boolean LoginUser(LoginDTO user) {
        boolean result = false;
        if(openConnection()){
            try {
                String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                ResultSet rs = stmt.executeQuery();
                result = rs.next();
            } catch (SQLException ex) {
                System.out.println(ex);
        } finally{closeConnection();}
        }return result ;
    }
}

