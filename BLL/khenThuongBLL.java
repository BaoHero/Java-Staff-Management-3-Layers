package BLL;
import DAL.khenThuongDAL;
import DTO.khenThuongDTO;
import java.util.Vector;

public class khenThuongBLL {
    private Vector<khenThuongDTO> TTKT;
    khenThuongDAL ktDAL = new khenThuongDAL();
    public Vector<khenThuongDTO> getKhen(){
        return ktDAL.getKhen();
    }
    public String addKhen(khenThuongDTO kt){
        if(ktDAL.hasMaKT(kt.getMaKT()))
            return "Mã khen thưởng đã tồn tại.";
        if(ktDAL.addKhen(kt))
            return "Thêm thành công.";
        return "Thêm thất bại.";
    }
    
    public String delKhenThuong(String deletingMaKT){
        if (ktDAL.KTMaKT(deletingMaKT))
            return "Không thể xóa do mã tồn tại trong bảng Chi tiết chấm công";
        if (ktDAL.xoa(deletingMaKT))
            return "Xóa thành công";
        return "Xóa thất bại";
    }

    public String editKhenThuong( khenThuongDTO editingKT, String condition){
        if (!editingKT.getMaKT().equals(condition)){
            if (ktDAL.hasMaKT(editingKT.getMaKT()))
                return "Mã khen thưởng đã tồn tại";
            if (ktDAL.sua(editingKT))
                return "Sửa thành công";
        }
        else if (ktDAL.sua(editingKT))
            return "Sửa thành công";
        return "Sửa thất bại";
    }
    
    public Vector<khenThuongDTO> getSearchKhenThuong(String searchString){
        return ktDAL.getSearchKhenThuong(searchString);
    }
}
