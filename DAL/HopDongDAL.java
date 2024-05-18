package DAL;

import DTO.HopDongDTO;
import java.sql.*;
import java.util.Vector;


public class HopDongDAL extends connection {
    
    public Vector<HopDongDTO> getAllHopDong(){
        Vector<HopDongDTO> arr = new Vector<HopDongDTO>();
        if(openConnection()){
            try{
                String sql = "Select * from hopdonglaodong";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    HopDongDTO nv = new HopDongDTO();
                    nv.setMaHD(rs.getString("MaHD"));
                    nv.setNHANVIEN_MaNV(rs.getString("NHANVIEN_MaNV"));
                    nv.setLoaiHD(rs.getString("LoaiHD"));
                    nv.setNgayBD(rs.getTimestamp("NgayBD").toLocalDateTime().toLocalDate());
                    nv.setNgayKT(rs.getTimestamp("NgayKT").toLocalDateTime().toLocalDate());
                    nv.setNgayNghiViec(rs.getTimestamp("NgayNghiViec").toLocalDateTime().toLocalDate());
                    arr.add(nv);
                    
                }
            }catch(SQLException ex){
                System.out.print(ex);
            } finally{
                closeConnection();
            }
        } return arr;
    }
    
    public Vector<HopDongDTO> getSearchHopDong(String searchString){
        Vector <HopDongDTO> arr = new Vector<HopDongDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT * FROM hopdonglaodong WHERE MaHD LIKE ? OR NHANVIEN_MaNV LIKE ? OR LoaiHD LIKE ? OR NgayBD LIKE ? OR NgayKT LIKE ? OR NgayNghiViec LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                prestmt.setString(3, "%"+searchString+"%");
                prestmt.setString(4, "%"+searchString+"%");
                prestmt.setString(5, "%"+searchString+"%");
                prestmt.setString(6, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    HopDongDTO nv = new HopDongDTO();
                    nv.setMaHD(rs.getString("MaHD"));
                    nv.setNHANVIEN_MaNV(rs.getString("NHANVIEN_MaNV"));
                    nv.setLoaiHD(rs.getString("LoaiHD"));
                    nv.setNgayBD(rs.getTimestamp("NgayBD").toLocalDateTime().toLocalDate());
                    nv.setNgayKT(rs.getTimestamp("NgayKT").toLocalDateTime().toLocalDate());
                    nv.setNgayNghiViec(rs.getTimestamp("NgayNghiViec").toLocalDateTime().toLocalDate());
                    arr.add(nv);
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
    
    public boolean addHopDong(HopDongDTO nvm){
        boolean result = false;
        if(openConnection()){
            try{
                String sql="Insert into hopdonglaodong values(?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, nvm.getMaHD());
                stmt.setString(2, nvm.getNHANVIEN_MaNV());
                stmt.setString(3, nvm.getLoaiHD());
                stmt.setTimestamp(4, Timestamp.valueOf(nvm.getNgayBD().atStartOfDay()));
                stmt.setTimestamp(5, Timestamp.valueOf(nvm.getNgayKT().atStartOfDay()));
                stmt.setTimestamp(6, Timestamp.valueOf(nvm.getNgayNghiViec().atStartOfDay()));
                if(stmt.executeUpdate()>=1)
                    result = true;
            } catch(SQLException ex){
                System.out.print(ex);
            }finally{
                closeConnection();
            }
        } return result;
    }
    public boolean hasHopDong(String mahd){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "Select * from hopdonglaodong where MaHD="+mahd;
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                result=rs.next();
                
            }catch(SQLException ex){
                System.out.print(ex);
            }finally{closeConnection();}
        }return result;
    }
    
    public boolean delHopDong(String deletingMaHD){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "DELETE FROM hopdonglaodong WHERE MaHD = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, deletingMaHD);

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

    public boolean editHopDong(HopDongDTO editingHopDong, String condition){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "UPDATE hopdonglaodong SET MaHD = ?, NHANVIEN_MaNV = ?, LoaiHD = ?, NgayBD = ? , NgayKT = ?, NgayNghiViec = ? WHERE MaHD = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, editingHopDong.getMaHD());
                prestmt.setString(2, editingHopDong.getNHANVIEN_MaNV());
                prestmt.setString(3, editingHopDong.getLoaiHD());
                prestmt.setTimestamp(4,Timestamp.valueOf(editingHopDong.getNgayBD().atStartOfDay()));
                prestmt.setTimestamp(5,Timestamp.valueOf(editingHopDong.getNgayKT().atStartOfDay()));
                prestmt.setTimestamp(6,Timestamp.valueOf(editingHopDong.getNgayNghiViec().atStartOfDay()));
                prestmt.setString(7, condition);
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
