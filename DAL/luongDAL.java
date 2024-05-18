package DAL;

import java.util.Vector;
import DTO.luongDTO;

import java.sql.*;

public class luongDAL extends connection {
    
    public Vector<luongDTO> getAllLuong(){
        Vector <luongDTO> arr = new Vector <luongDTO>();
        if (openConnection()){
            try {
                String sql = "Select* from luong";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    luongDTO luong = new luongDTO();
                    luong.setMaLuong(rs.getString("MaLuong"));
                    luong.setTienLuong(rs.getString("TienLuong"));
                    arr.add(luong);
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

    public Vector<luongDTO> getSearchLuong(String searchString){
        Vector <luongDTO> arr = new Vector<luongDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT * FROM luong WHERE MaLuong LIKE ? OR TienLuong LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    luongDTO luong = new luongDTO();
                    luong.setMaLuong(rs.getString("MaLuong"));
                    luong.setTienLuong(rs.getString("TienLuong"));
                    arr.add(luong);
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
    
    public boolean addLuong(luongDTO luong){
        boolean result = false;
        if(openConnection()){
            try {
                String sql = "Insert into luong values(?,?)";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, luong.getMaLuong());
                prestmt.setString(2, luong.getTienLuong());
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

    public boolean hasMaLuong(String MaLuong){ 
        boolean result = false; 
        if (openConnection()) {
            try { 
                String sql = "Select * from luong where MaLuong=?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, MaLuong);
                ResultSet rs = prestmt.executeQuery();
                result = rs.next(); 
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
    
    public boolean KTMaLuong(String Luong_MaLuong){ 
        boolean result = false; 
        if (openConnection()) {
            try { 
                String sql = "Select * from chucvu where Luong_MaLuong=?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, Luong_MaLuong);
                ResultSet rs = prestmt.executeQuery();
                result = rs.next(); 
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

    public boolean editLuong(luongDTO editingLuong, String condition){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "UPDATE luong SET MaLuong = ?, TienLuong = ? WHERE MaLuong = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, editingLuong.getMaLuong());
                prestmt.setString(2, editingLuong.getTienLuong());
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

    public boolean delLuong(String deletingMaLuong){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "DELETE FROM luong WHERE MaLuong = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, deletingMaLuong);

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


}
