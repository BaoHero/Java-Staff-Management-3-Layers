package DAL;

import DTO.chiTietBangChamCongDTO;
import java.sql.*;
import java.util.Vector;

public class chiTietChamCongDAL extends connection {
	
	public Vector<chiTietBangChamCongDTO> getAllChiTietBangChamCong(){
//		openConnection();
		Vector<chiTietBangChamCongDTO> array = new Vector<chiTietBangChamCongDTO>();
		if (openConnection()) {
			try {
				String sql = "Select * from chitietbangchamcong";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					chiTietBangChamCongDTO chamCong = new chiTietBangChamCongDTO();
					chamCong.setmaChiTietBangChamCong(rs.getString("MaChiTietBangChamCong"));
					chamCong.setNgayLap(rs.getTimestamp("NgayLap").toLocalDateTime().toLocalDate());
					chamCong.setsoNgayNghi(rs.getInt("SoNgayNghi"));
                                        chamCong.setKhenThuong_MaKT(rs.getString("KhenThuong_MaKT"));
                                        chamCong.setKiLuat_MaKL(rs.getString("KiLuat_MaKL"));
				array.add(chamCong);
				}
				
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("cannot set");
			}
			finally {
				closeConnection();
			}
		}
		return array;
	}
        
        public Vector<chiTietBangChamCongDTO> getSearchChiTiet(String searchString){
        Vector <chiTietBangChamCongDTO> arr = new Vector<chiTietBangChamCongDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT * FROM chitietbangchamcong WHERE MaChiTietBangChamCong LIKE ? OR NgayLap LIKE ? OR SoNgayNghi LIKE ? OR KhenThuong_MaKT LIKE ? OR KiLuat_MaKL LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                prestmt.setString(3, "%"+searchString+"%");
                prestmt.setString(4, "%"+searchString+"%");
                prestmt.setString(5, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    chiTietBangChamCongDTO nv = new chiTietBangChamCongDTO();
                    nv.setmaChiTietBangChamCong(rs.getString("MaChiTietBangChamCong"));
                    nv.setNgayLap(rs.getTimestamp("NgayLap").toLocalDateTime().toLocalDate());
                    nv.setsoNgayNghi(rs.getInt("SoNgayNghi"));
                    nv.setKhenThuong_MaKT(rs.getString("KhenThuong_MaKT"));
                    nv.setKiLuat_MaKL(rs.getString("KiLuat_MaKL"));
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
        
//	them
	public boolean addChiTietBangChamCong(chiTietBangChamCongDTO tmp) {
		if(openConnection()){
			try {
				String sql="insert into chitietbangchamcong values(?, ?, ? , ? , ?)";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, tmp.getmaChiTietBangChamCong());
				stmt.setTimestamp(2, Timestamp.valueOf(tmp.getNgayLap().atStartOfDay()));
				stmt.setInt(3, tmp.getsoNgayNghi());
				stmt.setString(4, tmp.getKhenThuong_MaKT());
                                stmt.setString(5, tmp.getKiLuat_MaKL());
				int row = stmt.executeUpdate();
				return row > 0;
				
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
				closeConnection();
				return false;
	} return false; }
//	xoa
	public boolean delete(String tmp) {
		if(openConnection()){
		try {
			String sql = "delete from ChiTietBangChamCong where MaChiTietBangChamCong = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, tmp);
			int rows = pst.executeUpdate();
			return rows > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return false;
	} return false ;}
//	sua
	public boolean update(chiTietBangChamCongDTO tmp) {
		if(openConnection()){
		
		try {
			String sql = "UPDATE chitietbangchamcong SET MaChiTietBangChamCong = ?, NgayLap = ?, SoNgayNghi = ?, KhenThuong_MaKT = ? , KiLuat_MaKL = ? WHERE MaChiTietBangChamCong = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, tmp.getmaChiTietBangChamCong());
			pst.setTimestamp(2, Timestamp.valueOf(tmp.getNgayLap().atStartOfDay()));
			pst.setInt(3, tmp.getsoNgayNghi());
			pst.setString(4, tmp.getKhenThuong_MaKT());
                        pst.setString(5, tmp.getKiLuat_MaKL());
                        pst.setString(6 , tmp.getmaChiTietBangChamCong());
                        
			int rows = pst.executeUpdate();
			return rows > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		closeConnection();
		return false;
	} return false ; }
	
	
	public boolean hasMaChiTietBangChamCong(String id) {
		if(openConnection()){
		if (con != null) {
			try {
				String sql = "select * from chitietbangchamcong where machitietbangchamcong=?";
				PreparedStatement stmt = con.prepareStatement(sql);
                                stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				return rs.next();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				closeConnection();
			}
		}
		return false;
	} return false ;}
        
        public boolean KTMaChiTietBangChamCong(String MaCT) {
		if(openConnection()){
		if (con != null) {
			try {
				String sql = "select * from bangchamcong where ChiTietBangChamCong_MaBangChamCong=?";
				PreparedStatement stmt = con.prepareStatement(sql);
                                stmt.setString(1, MaCT);
				ResultSet rs = stmt.executeQuery();
				return rs.next();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				closeConnection();
			}
		}
		return false;
	} return false ;}
	
}
