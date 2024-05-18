package BLL;

import DTO.bangChamCongDTO;
import DAL.chamCongDAL;
import java.util.Vector;

public class bangChamCongBUS {
	chamCongDAL dal = new chamCongDAL();

	public Vector<bangChamCongDTO> getAllChamCong() {
		return dal.getAllBangChamCong();
	}
        
        public Vector<bangChamCongDTO> ThongKeBCC(){
            return dal.ThongKeBangChamCong();
        }
	

//	chuc nang them sua xoa
        
	public String addChamCong(bangChamCongDTO nvm){
        if(dal.hasMaBangChamCong(nvm.getMaBangChamCong()))
            return "Mã bảng đã tồn tại";
        if(dal.addBangChamCong(nvm))
            return "Thêm thành công";
        return "Thêm thất bại";
    }
    
    public String delChamCong(String deletingMaBCC){
        if (dal.deleteBangChamCong(deletingMaBCC))
            return "Xóa thành công";
        return "Xóa thất bại";
    }

    public String editChamCong(bangChamCongDTO editingBCC, String condition){
        if (!editingBCC.getMaBangChamCong().equals(condition)){
            if (dal.hasMaBangChamCong(editingBCC.getMaBangChamCong()))
                return "Mã bảng đã tồn tại";
            if (dal.updateBangChamCong(editingBCC))
                return "Sửa thành công";
        }
        else if (dal.updateBangChamCong(editingBCC))
            return "Sửa thành công";
        return "Sửa thất bại";
    }
        
        public Vector<bangChamCongDTO> getSearchBangChamCong(String searchString){
        return dal.getSearchBangChamCong(searchString);
    }
	
}
