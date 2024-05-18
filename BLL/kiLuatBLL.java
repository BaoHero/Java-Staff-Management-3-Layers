package BLL;
import DAL.kiLuatDAL;
import DTO.kiLuatDTO;
import java.util.Vector;

public class kiLuatBLL {
    private Vector<kiLuatDTO> TTKL;
    kiLuatDAL klDAL = new kiLuatDAL();
    public Vector<kiLuatDTO> getViPham(){
        return klDAL.getViPham();
    }
    public String addViPham(kiLuatDTO kl){
        if(klDAL.hasMaKL(kl.getMaKL()))
            return "Mã kĩ luật đã tồn tại";
        if(klDAL.addViPham(kl))
            return "Thêm thành công";
        return "Thêm thất bại";
    }
    
    public String delKiLuat(String deletingMaKL){
        if (klDAL.KTMaKL(deletingMaKL))
            return "Không thể xóa do mã tồn tại trong Chi tiết chấm công";
        if (klDAL.xoa(deletingMaKL))
            return "Xóa thành công";
        return "Xóa thất bại";
    }

    public String editKiLuat( kiLuatDTO editingKL, String condition){
        if (!editingKL.getMaKL().equals(condition)){
            if (klDAL.hasMaKL(editingKL.getMaKL()))
                return "Mã kỉ luật đã tồn tại";
            if (klDAL.update(editingKL))
                return "Sửa thành công";
        }
        else if (klDAL.update(editingKL))
            return "Sửa thành công";
        return "Sửa thất bại";
    }
    
    public Vector<kiLuatDTO> getSearchKiLuat(String searchString){
        return klDAL.getSearchKiLuat(searchString);
    }
}
