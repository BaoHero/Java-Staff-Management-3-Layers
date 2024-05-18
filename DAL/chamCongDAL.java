package DAL;

import java.sql.*;
import DTO.bangChamCongDTO;
import java.util.Vector;


public class chamCongDAL extends connection {
    
    
    
    
        
    
	public Vector <bangChamCongDTO> getAllBangChamCong(){
		Vector <bangChamCongDTO> arr = new Vector<bangChamCongDTO>();
		if (openConnection()) {
			try {
				String sql = "select * from bangchamcong";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					bangChamCongDTO bangChamCong = new bangChamCongDTO();
					bangChamCong.setMaBangChamCong(rs.getString("MaBangChamCong"));
					bangChamCong.setNHANVIEN_MaNV(rs.getString("NHANVIEN_MaNV"));
					bangChamCong.setChiTietBangChamCong_maBangChamCong(rs.getString("ChiTietBangChamCong_MaBangChamCong"));
				arr.add(bangChamCong);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				closeConnection();
			}
		}
		return arr;
	}	
        
        public Vector<bangChamCongDTO> getSearchBangChamCong(String searchString){
        Vector <bangChamCongDTO> arr = new Vector<bangChamCongDTO>();

        if (openConnection()){
            try {
                String sql = "SELECT * FROM bangchamcong WHERE MaBangChamCong LIKE ? OR NHANVIEN_MaNV LIKE ? OR ChiTietBangChamCong_MaBangChamCong LIKE ?";
                PreparedStatement prestmt = con.prepareStatement(sql);
                prestmt.setString(1, "%"+searchString+"%");
                prestmt.setString(2, "%"+searchString+"%");
                prestmt.setString(3, "%"+searchString+"%");
                ResultSet rs = prestmt.executeQuery();
                while (rs.next()){
                    bangChamCongDTO nv = new bangChamCongDTO();
                    nv.setMaBangChamCong(rs.getString("MaBangChamCong"));
                    nv.setNHANVIEN_MaNV(rs.getString("NHANVIEN_MaNV"));
                    nv.setChiTietBangChamCong_maBangChamCong(rs.getString("ChiTietBangChamCong_MaBangChamCong"));
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
	
        public boolean hasMaBangChamCong(String id) {
                if (openConnection()) {
                        try {
                                String sql = "select * from bangchamcong where MaBangChamCong = " + id;
                                Statement stmt = con.createStatement();
                                ResultSet rs = stmt.executeQuery(sql);
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
        }
	
public boolean addBangChamCong(bangChamCongDTO tmp) {
	if (openConnection()) {
		try {
			String sql = "insert into bangchamcong values(?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, tmp.getMaBangChamCong());
			stmt.setString(2, tmp.getNHANVIEN_MaNV());
			stmt.setString(3, tmp.getChiTietBangChamCong_maBangChamCong());
			int row = stmt.executeUpdate();
			return row >0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}}
		return false;
	}
        public boolean deleteBangChamCong(String maBangCanXoa) {
                if (openConnection()) {
                        try {
                                String sql = "delete from bangchamcong where MaBangChamCong= ?";
                                PreparedStatement stmt = con.prepareStatement(sql);
                                stmt.setString(1, maBangCanXoa);
                                int row = stmt.executeUpdate();
                                return row >0;
                        } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                        }
                        finally {
                                closeConnection();
                        }}
                        return false;
                }
	public boolean updateBangChamCong(bangChamCongDTO tmp) {
		if(openConnection()) {
			try {
			String sql = "UPDATE bangchamcong SET MaBangChamCong = ?, NHANVIEN_MaNV = ?, ChiTietBangChamCong_MaBangChamCong = ? WHERE MaBangChamCong = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, tmp.getMaBangChamCong());
			stmt.setString(2, tmp.getNHANVIEN_MaNV());
			stmt.setString(3, tmp.getChiTietBangChamCong_maBangChamCong());
                        stmt.setString(4 , tmp.getMaBangChamCong());
			int row = stmt.executeUpdate();
			return row >0;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				closeConnection();
			}
		}
			return false;
}
        
        public Vector <bangChamCongDTO> ThongKeBangChamCong(){
		Vector <bangChamCongDTO> arr = new Vector<bangChamCongDTO>();
		if (openConnection()) {
			try {
				String sql = "SELECT bangchamcong.NHANVIEN_MaNV, SUM(chitietbangchamcong.SoNgayNghi) AS SoNgayNghi FROM bangchamcong AS bangchamcong JOIN chitietbangchamcong AS chitietbangchamcong ON bangchamcong.ChiTietBangChamCong_MaBangChamCong = chitietbangchamcong.MaChiTietBangChamCong GROUP BY bangchamcong.NHANVIEN_MaNV";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					bangChamCongDTO bangChamCong = new bangChamCongDTO();
					bangChamCong.setNHANVIEN_MaNV(rs.getString("NHANVIEN_MaNV"));
					bangChamCong.setSoNgayNghi(rs.getInt("SoNgayNghi"));
				arr.add(bangChamCong);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				closeConnection();
			}
		}
		return arr;
	}

}


