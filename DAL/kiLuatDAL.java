package DAL;
import DTO.kiLuatDTO;
import java.util.Vector;
import java.sql.*;

public class kiLuatDAL extends connection {
    
    public Vector<kiLuatDTO> getViPham(){
        Vector<kiLuatDTO> arr = new Vector<kiLuatDTO>();
        if(openConnection()){
            try{
                String sql = "Select * from KiLuat";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    kiLuatDTO kl = new kiLuatDTO();
                    kl.setLiDo(rs.getString("LiDo"));
                    kl.setMaKL(rs.getString("MaKL"));
                    kl.setNgayKL(rs.getTimestamp("NgayKL").toLocalDateTime().toLocalDate());
                    arr.add(kl);
                }
            } catch (SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return arr;
    }
    
    public Vector<kiLuatDTO> getSearchKiLuat(String searchString){
        Vector <kiLuatDTO> arr = new Vector<kiLuatDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT * FROM kiluat WHERE MaKL LIKE ? OR LiDo LIKE ? OR NgayKL LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                prestmt.setString(3, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    kiLuatDTO nv = new kiLuatDTO();
                    nv.setMaKL(rs.getString("MaKL"));
                    nv.setLiDo(rs.getString("LiDo"));
                    nv.setNgayKL(rs.getTimestamp("NgayKL").toLocalDateTime().toLocalDate());
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
    
    public boolean addViPham(kiLuatDTO kl){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "Insert into KiLuat values(?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, kl.getMaKL());
                stmt.setString(2, kl.getLiDo());
                stmt.setTimestamp(3, Timestamp.valueOf(kl.getNgayKL().atStartOfDay()));
                if(stmt.executeUpdate()>=1)
                    result = true;
                
            } catch(SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return result;
    }
    public boolean hasMaKL(String maKL){
        boolean result = false;
        if (openConnection()){
            try{
                String sql = "Select * from KiLuat where MaKL=?";
                PreparedStatement stmt =  con.prepareStatement(sql);
                stmt.setString(1 , maKL);
                ResultSet rs = stmt.executeQuery();
                result = rs.next();
            } catch (SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return result;
    }
    
    public boolean KTMaKL(String KiLuat_maKL){
        boolean result = false;
        if (openConnection()){
            try{
                String sql = "Select * from chitietbangchamcong where KiLuat_MaKL=?";
                PreparedStatement stmt =  con.prepareStatement(sql);
                stmt.setString(1 , KiLuat_maKL);
                ResultSet rs = stmt.executeQuery();
                result = rs.next();
            } catch (SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return result;
    }
    
    public boolean xoa(String kl) {
        if (openConnection()) {
        try {
            String sql = "delete from KiLuat where MaKL = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, kl);
            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException ex){
                System.out.println(ex);
            } finally{
                closeConnection();
            }
        } return false;
    }
    public boolean update(kiLuatDTO kl) {
        if (openConnection()) {

        try {
            String sql = "update kiluat set MaKL = ? , LiDo = ? , NgayKL = ? WHERE MaKL = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, kl.getMaKL());
            pst.setString(2, kl.getLiDo());
            pst.setTimestamp(3, Timestamp.valueOf(kl.getNgayKL().atStartOfDay()));
            pst.setString(4,kl.getMaKL());

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

