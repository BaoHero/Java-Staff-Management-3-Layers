package BLL;

import DAL.chucvuDAL;
import DTO.chucvuDTO;
import java.util.Vector;

public class chucvuBLL {
    chucvuDAL chucvuDAL = new chucvuDAL();

    public Vector<chucvuDTO> getAllChucVu(){
        return chucvuDAL.getAllChucVu();
    }
    
    public Vector<chucvuDTO> getSearchChucVu(String searchString){
        return chucvuDAL.getSearchChucVu(searchString);
    }

    public String addChucVu(chucvuDTO chucvu){
        if (chucvuDAL.hasMaCV(chucvu.getMaCV()))
            return "Mã chức vụ đã tồn tại";
        if (chucvuDAL.addChucVu(chucvu))
            return "Thêm thành công";
        return "Thêm thất bại";
    }

    public String delChucVu(String deletingMaCV){
        if(chucvuDAL.KTMaCV(deletingMaCV))
            return "Không thể xóa do tồn tại mã trong dữ liệu tại bảng Nhân Viên";
        if (chucvuDAL.delChucVu(deletingMaCV))
            return "Xóa thành công";
        return "Xóa thất bại";
    }

    public String editChucVu(chucvuDTO editingChucVu, String condition){
        if (!editingChucVu.getMaCV().equals(condition)){
            if (chucvuDAL.hasMaCV(editingChucVu.getMaCV()))
                return "Mã chức vụ đã tồn tại";
            if (chucvuDAL.editChucVu(editingChucVu, condition))
                return "Sửa thành công";
        }
        else if (chucvuDAL.editChucVu(editingChucVu, condition))
            return "Sửa thành công";
        return "Sửa thất bại";
    }
    
    public Vector<String> getMaCVList(){
        Vector <chucvuDTO> nvList = new Vector<chucvuDTO>();
        nvList = chucvuDAL.getAllChucVu();
        
        Vector <String> cvList = new Vector<String>();
        for (chucvuDTO cv : nvList) {
            cvList.add(cv.getMaCV());
        }
        return cvList;
    }
}
