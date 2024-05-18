package DAL;
import DTO.chucvuDTO;

import java.sql.*;
import java.util.Vector;

public class chucvuDAL extends connection {

    public Vector<chucvuDTO> getAllChucVu(){
        Vector <chucvuDTO> arr = new Vector <chucvuDTO>();
        if (openConnection()){
            try {
                String sql = "Select* from chucvu";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    chucvuDTO chucvu = new chucvuDTO();
                    chucvu.setMaCV(rs.getString("MaCV"));
                    chucvu.setTenCVHT(rs.getString("TenCVHT"));
                    chucvu.setPhongBan_MaPB(rs.getString("PhongBan_MaPB"));
                    chucvu.setLuong_MaLuong(rs.getString("Luong_MaLuong"));

                    arr.add(chucvu);
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

    public Vector<chucvuDTO> getSearchChucVu(String searchString){
        Vector <chucvuDTO> arr = new Vector<chucvuDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT * FROM chucvu WHERE MaCV LIKE ? OR TenCVHT LIKE ? OR PhongBan_MaPB LIKE ? OR Luong_MaLuong LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                prestmt.setString(3, "%"+searchString+"%");
                prestmt.setString(4, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    chucvuDTO chucvu = new chucvuDTO();
                    chucvu.setMaCV(rs.getString("MaCV"));
                    chucvu.setTenCVHT(rs.getString("TenCVHT"));
                    chucvu.setPhongBan_MaPB(rs.getString("PhongBan_MaPB"));
                    chucvu.setLuong_MaLuong(rs.getString("Luong_MaLuong"));
                    arr.add(chucvu);
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

    public boolean hasMaCV(String MaCV){
        boolean result = false; 
        if (openConnection()) {
            try { 
                String sql = "Select * from chucvu where MaCV=?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, MaCV);
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
    
    public boolean KTMaCV(String ChucVu_MaCV){
        boolean result = false; 
        if (openConnection()) {
            try { 
                String sql = "Select * from nhanvien where ChucVu_MaCV=?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, ChucVu_MaCV);
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

    public boolean addChucVu(chucvuDTO chucvu){
        boolean result = false;
        if(openConnection()){
            try {
                String sql = "Insert into chucvu values(?,?,?,?)";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, chucvu.getMaCV());
                prestmt.setString(2, chucvu.getTenCVHT());
                prestmt.setString(3, chucvu.getPhongBan_MaPB());
                prestmt.setString(4, chucvu.getLuong_MaLuong());
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

    public boolean delChucVu(String deletingMaCV){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "DELETE FROM chucvu WHERE MaCV = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, deletingMaCV);

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

    public boolean editChucVu(chucvuDTO editingChucVu, String condition){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "UPDATE chucvu SET MaCV = ?, TenCVHT = ?, PhongBan_MaPB = ?, Luong_MaLuong = ? WHERE MaCV = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, editingChucVu.getMaCV());
                prestmt.setString(2, editingChucVu.getTenCVHT());
                prestmt.setString(3, editingChucVu.getPhongBan_MaPB());
                prestmt.setString(4, editingChucVu.getLuong_MaLuong());
                prestmt.setString(5, condition);
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
