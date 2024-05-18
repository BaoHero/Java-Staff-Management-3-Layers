package DAL;


import DTO.TrinhDoDTO;
import java.sql.*;
import java.util.Vector;


public class TrinhDoDAL extends connection {
    
    public Vector<TrinhDoDTO> getAllTrinhDo(){
        Vector<TrinhDoDTO> arr = new Vector<TrinhDoDTO>();
        if(openConnection()){
            try{
                String sql = "Select * from trinhdohocvan";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    TrinhDoDTO nv = new TrinhDoDTO();
                    nv.setMaTDHV(rs.getString("MaTDHV"));
                    nv.setTenTDHV(rs.getString("TenTDHV"));
                    nv.setChuyenNganh(rs.getString("ChuyenNganh"));
                    arr.add(nv);
                    
                }
            }catch(SQLException ex){
                System.out.print(ex);
            } finally{
                closeConnection();
            }
        } return arr;
    }
    
    public Vector<TrinhDoDTO> getSearchTrinhDo(String searchString){
        Vector <TrinhDoDTO> arr = new Vector<TrinhDoDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT * FROM trinhdohocvan WHERE MaTDHV LIKE ? OR TenTDHV LIKE ? OR ChuyenNganh LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                prestmt.setString(3, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    TrinhDoDTO nv = new TrinhDoDTO();
                    nv.setMaTDHV(rs.getString("MaTDHV"));
                    nv.setTenTDHV(rs.getString("TenTDHV"));
                    nv.setChuyenNganh(rs.getString("ChuyenNganh"));
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
    
    public boolean addTrinhDo(TrinhDoDTO nvm){
        boolean result = false;
        if(openConnection()){
            try{
                String sql="Insert into trinhdohocvan values(?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, nvm.getMaTDHV());
                stmt.setString(2, nvm.getTenTDHV());
                stmt.setString(3, nvm.getChuyenNganh());
                if(stmt.executeUpdate()>=1)
                    result = true;
            } catch(SQLException ex){
                System.out.print(ex);
            }finally{
                closeConnection();
            }
        } return result;
    }
    public boolean hasTrinhDo(String MaTDHV){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "Select * from trinhdohocvan where MaTDHV=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, MaTDHV);
                ResultSet rs = stmt.executeQuery();
                result=rs.next();
                
            }catch(SQLException ex){
                System.out.print(ex);
            }finally{closeConnection();}
        }return result;
    }
    
    public boolean KTTrinhDo(String TrinhDoHocVan_MaTDHV){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "Select * from nhanvien where TrinhDoHocVan_MaTDHV=?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, TrinhDoHocVan_MaTDHV);
                ResultSet rs = stmt.executeQuery();
                result=rs.next();
                
            }catch(SQLException ex){
                System.out.print(ex);
            }finally{closeConnection();}
        }return result;
    }
    
    public boolean delTrinhDo(String deletingMaTDHV){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "DELETE FROM trinhdohocvan WHERE MaTDHV = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, deletingMaTDHV);

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

    public boolean editTrinhDo(TrinhDoDTO editingTrinhDo, String condition){
        boolean result = false;
        if (openConnection()){
            try {
                String sql = "UPDATE trinhdohocvan SET MaTDHV = ?, TenTDHV = ?, ChuyenNganh = ? WHERE MaTDHV = ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, editingTrinhDo.getMaTDHV());
                prestmt.setString(2, editingTrinhDo.getTenTDHV());
                prestmt.setString(3, editingTrinhDo.getChuyenNganh());
                prestmt.setString(4, condition);
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

