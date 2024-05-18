package BLL;

import DAL.luongDAL;
import DAL.chucvuDAL;
import DTO.luongDTO;
import java.util.Vector;

public class luongBLL {
    luongDAL luongDAL = new luongDAL();
    chucvuDAL chucvuDAL = new chucvuDAL();

    public Vector<luongDTO> getAllLuong(){
        return luongDAL.getAllLuong();
    }

    public Vector<luongDTO> getSearchLuong(String searchString){
        return luongDAL.getSearchLuong(searchString);
    }

    public Vector<String> getMaLuongList(){
        Vector <luongDTO> luongList = new Vector<luongDTO>();
        luongList = luongDAL.getAllLuong();
        
        Vector <String> maluongList = new Vector<String>();
        for (luongDTO luong : luongList) {
            maluongList.add(luong.getMaLuong());
        }
        return maluongList;
    }

    public String addLuong(luongDTO luong){
        if (luongDAL.hasMaLuong(luong.getMaLuong()))
            return "Mã lương đã tồn tại";
        if (luongDAL.addLuong(luong))
            return "Thêm thành công";
        return "Thêm thất bại";
    }

    public String editLuong(luongDTO editingLuong, String condition){
        if (!editingLuong.getMaLuong().equals(condition)){
            if (luongDAL.hasMaLuong(editingLuong.getMaLuong()))
                return "Mã lương đã tồn tại";
            if (luongDAL.editLuong(editingLuong, condition))
                return "Sửa thành công";
        }
        else if (luongDAL.editLuong(editingLuong, condition))
            return "Sửa thành công";
        return "Sửa thất bại";
    }

    public String delLuong(String deletingMaLuong){
        if (luongDAL.KTMaLuong(deletingMaLuong))
            return "Không thể xóa do tồn tại ràng buộc với dữ liệu tại bảng Chức Vụ";
        if (luongDAL.delLuong(deletingMaLuong))
            return "Xóa thành công";
        return "Xóa thất bại";
    }
}
