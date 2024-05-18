package DAL;

import DTO.phongbanDTO;
import java.sql.*;
import java.util.Vector;

public class phongbanDAL extends connection {
    
    public Vector<phongbanDTO> getAllPhongBan(){
        Vector <phongbanDTO> arr = new Vector <phongbanDTO>();
        if (openConnection()){
            try {
                String sql = "Select* from phongban";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    phongbanDTO phongban = new phongbanDTO();
                    phongban.setMaPB(rs.getString("MaPB"));
                    phongban.setTenPB(rs.getString("TenPB"));
                    arr.add(phongban);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
        }
        return arr;
    }

    public boolean hasMaPhongBan(String MaPB){ 
        boolean result = false; 
        if (openConnection()) {
            try { 
                String sql = "Select * from phongban where MaPB=?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, MaPB);
                ResultSet rs = prestmt.executeQuery();
                result = rs.next(); 
            } 
            catch (SQLException e) {
                System.out.println(e); 
            } 
            finally{ 
                closeConnection(); 
            } 
        }
        return result;
    }
    
    public boolean KTMaPhongBan(String PhongBan_MaPB){ 
        boolean result = false; 
        if (openConnection()) {
            try { 
                String sql = "Select * from chucvu where PhongBan_MaPB=?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, PhongBan_MaPB);
                ResultSet rs = prestmt.executeQuery();
                result = rs.next(); 
            } 
            catch (SQLException e) {
                System.out.println(e); 
            } 
            finally{ 
                closeConnection(); 
            } 
        }
        return result;
    }

    public boolean addPhongBan(phongbanDTO phongban){
        boolean result = false;
        if(openConnection()){
            try {
                String sql = "Insert into phongban values(?,?)";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, phongban.getMaPB());
                prestmt.setString(2, phongban.getTenPB());
                if (prestmt.executeUpdate()>=1)
                    result = true;
            } 
            catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
            }     
        }
        return result;
    }

    public String getMaPB_by_TenPB(String TenPB){
        String result = ""; 
        if (openConnection()) {
            try { 
                String sql = "Select * from phongban where TenPB=?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, TenPB);
                ResultSet rs = prestmt.executeQuery();
                rs.next();
                result = rs.getString("MaPB");
            } 
            catch (SQLException e) {
                System.out.println(e); 
            } 
            finally{ 
                closeConnection(); 
            } 
        }
        return result;
    }

    public String getTenPB_by_MaPB(String MaPB){
        String result = ""; 
        if (openConnection()) {
            try { 
                String sql = "Select * from phongban where MaPB=?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, MaPB);
                ResultSet rs = prestmt.executeQuery();
                rs.next();
                result = rs.getString("TenPB");
            } 
            catch (SQLException e) {
                System.out.println(e); 
            } 
            finally{ 
                closeConnection(); 
            } 
        }
        return result;
    }

    public boolean editPhongBan(phongbanDTO editingPhongBan, String condition){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "UPDATE phongban SET MaPB = ?, TenPB = ? WHERE MaPB = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, editingPhongBan.getMaPB());
                prestmt.setString(2, editingPhongBan.getTenPB());
                prestmt.setString(3, condition);
                if (prestmt.executeUpdate()>=1)
                    result = true;
            }
            catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
        }
        return result;
    }
    
    public boolean delPhongBan(String deletingMaPB){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "DELETE FROM phongban WHERE MaPB = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, deletingMaPB);

                if (prestmt.executeUpdate()>=1)
                    result = true;
            }
            catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
        }
        return result;
    }

    public Vector<phongbanDTO> getSearchPhongBan(String searchString){
        Vector <phongbanDTO> arr = new Vector<phongbanDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT * FROM phongban WHERE MaPB LIKE ? OR TenPB LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    phongbanDTO phongban = new phongbanDTO();
                    phongban.setMaPB(rs.getString("MaPB"));
                    phongban.setTenPB(rs.getString("TenPB"));
                    arr.add(phongban);
                }
            } 
            catch (Exception e) {
                System.out.println(e);
            }
            finally{
                closeConnection();
            }
        }
        return arr;
    }
}
