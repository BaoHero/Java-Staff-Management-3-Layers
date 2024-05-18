package BLL;

import DAL.chiTietChamCongDAL;
import DTO.chiTietBangChamCongDTO;
import java.util.Vector;

public class chiTietBangChamCongBUS {
    
	chiTietChamCongDAL dal = new chiTietChamCongDAL();

	public Vector<chiTietBangChamCongDTO> getallChiTietBangChamCong(){
		return dal.getAllChiTietBangChamCong();
	}
	
//	phuong thuc them sua xoa
        
	public String addChiTiet(chiTietBangChamCongDTO nvm){
        if(dal.hasMaChiTietBangChamCong(nvm.getmaChiTietBangChamCong()))
            return "Mã chi tiết đã tồn tại";
        if(dal.addChiTietBangChamCong(nvm))
            return "Thêm thành công";
        return "Thêm thất bại";
    }
    
    public String delChiTiet(String deletingMaChiTiet){
        if (dal.KTMaChiTietBangChamCong(deletingMaChiTiet))
            return "Không thể xóa do mã tồn tại trong bảng chấm công";
        if (dal.delete(deletingMaChiTiet))
            return "Xóa thành công";
        return "Xóa thất bại";
    }

    public String editChiTiet(chiTietBangChamCongDTO editingMaChiTiet, String condition){
        if (!editingMaChiTiet.getmaChiTietBangChamCong().equals(condition)){
            if (dal.hasMaChiTietBangChamCong(editingMaChiTiet.getmaChiTietBangChamCong()))
                return "Mã chi tiết đã tồn tại";
            if (dal.update(editingMaChiTiet))
                return "Sửa thành công";
        }
        else if (dal.update(editingMaChiTiet))
            return "Sửa thành công";
        return "Sửa thất bại";
    }
        
        public Vector<chiTietBangChamCongDTO> getSearchChiTiet(String searchString){
        return dal.getSearchChiTiet(searchString);
    }
        
    public Vector<String> getMaCTList(){
        Vector <chiTietBangChamCongDTO> ctList = new Vector<chiTietBangChamCongDTO>();
        ctList = dal.getAllChiTietBangChamCong();
        
        Vector <String> mactList = new Vector<String>();
        for (chiTietBangChamCongDTO ct : ctList) {
            mactList.add(ct.getmaChiTietBangChamCong());
        }
        return mactList;
    }
}
