package DAL;
import DTO.khenThuongDTO;
import java.util.Vector;
import java.sql.*;

public class khenThuongDAL extends connection {
    
    public Vector<khenThuongDTO> getKhen(){
        Vector<khenThuongDTO> arr = new Vector<khenThuongDTO>();
        if(openConnection()){
            try{
                String sql = "Select * from KhenThuong";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    khenThuongDTO kt = new khenThuongDTO();
                    kt.setLiDo(rs.getString("LiDo"));
                    kt.setMaKT(rs.getString("MaKT"));
                    kt.setNgayKT(rs.getTimestamp("NgayKT").toLocalDateTime().toLocalDate());
                    arr.add(kt);
                }
            } catch (SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return arr;
    }
    
    public Vector<khenThuongDTO> getSearchKhenThuong(String searchString){
        Vector <khenThuongDTO> arr = new Vector<khenThuongDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT * FROM khenthuong WHERE MaKT LIKE ? OR LiDo LIKE ? OR NgayKT LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                prestmt.setString(3, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    khenThuongDTO nv = new khenThuongDTO();
                    nv.setMaKT(rs.getString("MaKT"));
                    nv.setLiDo(rs.getString("LiDo"));
                    nv.setNgayKT(rs.getTimestamp("NgayKT").toLocalDateTime().toLocalDate());
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
    
    public boolean addKhen(khenThuongDTO kt){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "Insert into KhenThuong values(?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, kt.getMaKT());
                stmt.setString(2, kt.getLiDo());
                stmt.setTimestamp(3, Timestamp.valueOf(kt.getNgayKT().atStartOfDay()));
                if(stmt.executeUpdate()>=1)
                    result = true;
                
            } catch(SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return result;
    }
    public boolean hasMaKT(String maKT){
        boolean result = false;
        if (openConnection()){
            try{
                String sql = "Select * from KhenThuong where MaKT=?";
                PreparedStatement stmt =  con.prepareStatement(sql);
                stmt.setString(1 , maKT);
                ResultSet rs = stmt.executeQuery();
                result = rs.next();
            } catch (SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return result;
    }
    
    public boolean KTMaKT(String KhenThuong_maKT){
        boolean result = false;
        if (openConnection()){
            try{
                String sql = "Select * from chitietbangchamcong where KhenThuong_MaKT=?";
                PreparedStatement stmt =  con.prepareStatement(sql);
                stmt.setString(1 , KhenThuong_maKT);
                ResultSet rs = stmt.executeQuery();
                result = rs.next();
            } catch (SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return result;
    }
    
    public boolean xoa(String kt) {
        if (openConnection()) {
        try {
            String sql = "delete from KhenThuong where MaKT = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, kt);
            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return false;
    }
    public boolean sua(khenThuongDTO kt) {
        if (openConnection()) {

        try {
            String sql = "update KhenThuong set MaKT = ? , LiDo = ? , NgayKT = ? WHERE MaKT=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, kt.getMaKT());
            pst.setString(2, kt.getLiDo());
            pst.setTimestamp(3, Timestamp.valueOf(kt.getNgayKT().atStartOfDay()));
            pst.setString(4 , kt.getMaKT());

            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return false;
    }    
}

