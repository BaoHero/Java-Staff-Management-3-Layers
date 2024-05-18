package BLL;

import DAL.HopDongDAL;
import DTO.HopDongDTO;
import java.util.Vector;

public class HopDongBLL {
    HopDongDAL nvDAL = new HopDongDAL();
    
    public Vector<HopDongDTO> getAllHopDong(){
        return nvDAL.getAllHopDong();
    }
    
    public Vector<HopDongDTO> getSearchHopDong(String searchString){
        return nvDAL.getSearchHopDong(searchString);
    }
    
    public String addHopDong(HopDongDTO nvm){
        if(nvDAL.hasHopDong(nvm.getMaHD()))
            return "Mã hợp đồng đã tồn tại";
        if(nvDAL.addHopDong(nvm))
            return "Thêm thành công";
        return "Thêm thất bại";
    }
    
    public String delHopDong(String deletingMaHD){
        if (nvDAL.delHopDong(deletingMaHD))
            return "Xóa thành công";
        return "Xóa thất bại";
    }

    public String editHopDong(HopDongDTO editingHopDong, String condition){
        if (!editingHopDong.getMaHD().equals(condition)){
            if (nvDAL.hasHopDong(editingHopDong.getMaHD()))
                return "Mã hợp đồng đã tồn tại";
            if (nvDAL.editHopDong(editingHopDong, condition))
                return "Sửa thành công";
        }
        else if (nvDAL.editHopDong(editingHopDong, condition))
            return "Sửa thành công";
        return "Sửa thất bại";
    }
    
    
    
}
