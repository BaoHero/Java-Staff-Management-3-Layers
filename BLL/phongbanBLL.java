package BLL;

import DAL.phongbanDAL;
import DAL.chucvuDAL;
import DTO.phongbanDTO;
import java.util.Vector;

public class phongbanBLL {
    phongbanDAL phongbanDAL = new phongbanDAL();
    chucvuDAL chucvuDAL = new chucvuDAL();

    public Vector<phongbanDTO> getAllPhongBan(){
        return phongbanDAL.getAllPhongBan();
    }

    public Vector<phongbanDTO> getSearchPhongBan(String searchString){
        return phongbanDAL.getSearchPhongBan(searchString);
    }

    public String getMaPB_by_TenPB(String tenPB){
        return phongbanDAL.getMaPB_by_TenPB(tenPB);
    }

    public String getTenPB_by_MaPB(String maPB){
        return phongbanDAL.getTenPB_by_MaPB(maPB);
    }

    public Vector<String> getTenPhongBanList(){
        Vector <phongbanDTO> phongbanList = new Vector<phongbanDTO>();
        phongbanList = phongbanDAL.getAllPhongBan();
        
        Vector <String> tenPhongBanList = new Vector<String>();
        for (phongbanDTO pb : phongbanList) {
            tenPhongBanList.add(pb.getTenPB());
        }
        return tenPhongBanList;
    }

    public String addPhongBan(phongbanDTO phongban){
        if (phongbanDAL.hasMaPhongBan(phongban.getMaPB()))
            return "Mã phòng ban đã tồn tại";
        if (phongbanDAL.addPhongBan(phongban))
            return "Thêm thành công";
        return "Thêm thất bại";
    }

    public String delPhongBan(String deletingMaPB){
        if (phongbanDAL.KTMaPhongBan(deletingMaPB))
            return "Không thể xóa do tồn tại ràng buộc với dữ liệu tại bảng Chức Vụ";
        if (phongbanDAL.delPhongBan(deletingMaPB))
            return "Xóa thành công";
        return "Xóa thất bại";
    }

    public String editPhongBan(phongbanDTO editingPhongBan, String condition){
        if (!editingPhongBan.getMaPB().equals(condition)){
            if (phongbanDAL.hasMaPhongBan(editingPhongBan.getMaPB()))
                return "Mã phòng ban đã tồn tại";
            if (phongbanDAL.editPhongBan(editingPhongBan, condition))
                return "Sửa thành công";
        }
        else if (phongbanDAL.editPhongBan(editingPhongBan, condition))
            return "Sửa thành công";
        return "Sửa thất bại";
    }


}
