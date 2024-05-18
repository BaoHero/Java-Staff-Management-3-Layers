package DAL;

import DTO.NhanVienDTO;
import java.sql.*;
import java.util.Vector;


public class NhanVienDAL extends connection {
    
    public Vector<NhanVienDTO> getAllNhanVien(){
        Vector<NhanVienDTO> arr = new Vector<NhanVienDTO>();
        if(openConnection()){
            try{
                String sql = "Select * from nhanvien";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setHoTen(rs.getString("HoTen"));
                    nv.setGioiTinh(rs.getString("GioiTinh"));
                    nv.setNgaySinh(rs.getTimestamp("NgaySinh").toLocalDateTime().toLocalDate());
                    nv.setSoDienThoai(rs.getString("SoDienThoai"));
                    nv.setTrangThai(rs.getString("TrangThai"));
                    nv.setChucVu_MaCV(rs.getString("ChucVu_MaCV"));
                    nv.setNgayNhanCV(rs.getTimestamp("NgayNhanCV").toLocalDateTime().toLocalDate());
                    nv.setTrinhDoHocVan_MaTDHV(rs.getString("TrinhDoHocVan_MaTDHV"));
                    arr.add(nv);
                    
                }
            }catch(SQLException ex){
                System.out.print(ex);
            } finally{
                closeConnection();
            }
        } return arr;
    }
    
    public Vector<NhanVienDTO> getSearchNhanVien(String searchString){
        Vector <NhanVienDTO> arr = new Vector<NhanVienDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT * FROM nhanvien WHERE MaNV LIKE ? OR HoTen LIKE ? OR GioiTinh LIKE ? OR NgaySinh LIKE ? OR SoDienThoai LIKE ? OR TrangThai LIKE ? OR ChucVu_MaCV LIKE ? OR NgayNhanCV LIKE ? OR TrinhDoHocVan_MaTDHV LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                prestmt.setString(3, "%"+searchString+"%");
                prestmt.setString(4, "%"+searchString+"%");
                prestmt.setString(5, "%"+searchString+"%");
                prestmt.setString(6, "%"+searchString+"%");
                prestmt.setString(7, "%"+searchString+"%");
                prestmt.setString(8, "%"+searchString+"%");
                prestmt.setString(9, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setHoTen(rs.getString("HoTen"));
                    nv.setGioiTinh(rs.getString("GioiTinh"));
                    nv.setNgaySinh(rs.getTimestamp("NgaySinh").toLocalDateTime().toLocalDate());
                    nv.setSoDienThoai(rs.getString("SoDienThoai"));
                    nv.setTrangThai(rs.getString("TrangThai"));
                    nv.setChucVu_MaCV(rs.getString("ChucVu_MaCV"));
                    nv.setNgayNhanCV(rs.getTimestamp("NgayNhanCV").toLocalDateTime().toLocalDate());
                    nv.setTrinhDoHocVan_MaTDHV(rs.getString("TrinhDoHocVan_MaTDHV"));
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
    
    public boolean addNhanVien(NhanVienDTO nvm){
        boolean result = false;
        if(openConnection()){
            try{
                String sql="Insert into nhanvien values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, nvm.getMaNV());
                stmt.setString(2, nvm.getHoTen());
                stmt.setString(3, nvm.getGioiTinh());
                stmt.setTimestamp(4, Timestamp.valueOf(nvm.getNgaySinh().atStartOfDay()));
                stmt.setString(5, nvm.getSoDienThoai());
                stmt.setString(6, nvm.getTrangThai());
                stmt.setString(7, nvm.getChucVu_MaCV());
                stmt.setTimestamp(8, Timestamp.valueOf(nvm.getNgayNhanCV().atStartOfDay()));
                stmt.setString(9, nvm.getTrinhDoHocVan_MaTDHV());
                if(stmt.executeUpdate()>=1)
                    result = true;
            } catch(SQLException ex){
                System.out.print(ex);
            }finally{
                closeConnection();
            }
        } return result;
    }
    public boolean hasNhanVien(String MaNV){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "Select * from nhanvien where MaNV=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1,MaNV);
                ResultSet rs = stmt.executeQuery();
                result=rs.next();
                
            }catch(SQLException ex){
                System.out.print(ex);
            }finally{closeConnection();}
        }return result;
    }
    
    public boolean delNhanVien(String deletingMaNV){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "DELETE FROM nhanvien WHERE MaNV = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, deletingMaNV);

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

    public boolean editNhanVien(NhanVienDTO editingNhanVien, String condition){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "UPDATE nhanvien SET MaNV = ?, HoTen = ?, GioiTinh = ?, NgaySinh = ? , SoDienThoai = ?, TrangThai = ?, ChucVu_MaCV = ?, NgayNhanCV = ?, TrinhDoHocVan_MaTDHV = ? WHERE MaNV = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, editingNhanVien.getMaNV());
                prestmt.setString(2, editingNhanVien.getHoTen());
                prestmt.setString(3, editingNhanVien.getGioiTinh());
                prestmt.setTimestamp(4,Timestamp.valueOf(editingNhanVien.getNgaySinh().atStartOfDay()));
                prestmt.setString(5, editingNhanVien.getSoDienThoai());
                prestmt.setString(6, editingNhanVien.getTrangThai());
                prestmt.setString(7, editingNhanVien.getChucVu_MaCV());
                prestmt.setTimestamp(8, Timestamp.valueOf(editingNhanVien.getNgayNhanCV().atStartOfDay()));
                prestmt.setString(9, editingNhanVien.getTrinhDoHocVan_MaTDHV());
                prestmt.setString(10, condition);
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
    
    public Vector<NhanVienDTO> ThongKeNhanVien(){
        Vector<NhanVienDTO> arr = new Vector<NhanVienDTO>();
        if(openConnection()){
            try{
                String sql = "Select HoTen,TrangThai from nhanvien";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setHoTen(rs.getString("HoTen"));
                    nv.setTrangThai(rs.getString("TrangThai"));
                    arr.add(nv);
                    
                }
            }catch(SQLException ex){
                System.out.print(ex);
            } finally{
                closeConnection();
            }
        } return arr;
    }
    
    public Vector<NhanVienDTO> getSearchTk(String searchString){
        Vector <NhanVienDTO> arr = new Vector<NhanVienDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT HoTen,TrangThai FROM nhanvien WHERE HoTen LIKE ? OR TrangThai LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setHoTen(rs.getString("HoTen"));
                    nv.setTrangThai(rs.getString("TrangThai"));
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
    
}
