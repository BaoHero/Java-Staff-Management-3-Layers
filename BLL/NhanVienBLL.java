package BLL;

import DAL.NhanVienDAL;
import DTO.NhanVienDTO;
import java.util.Vector;

public class NhanVienBLL {
    NhanVienDAL nvDAL = new NhanVienDAL();
    
    public Vector<NhanVienDTO> getAllNhanVien(){
        return nvDAL.getAllNhanVien();
    }
    
    public Vector<NhanVienDTO> ThongKeNhanVien(){
        return nvDAL.ThongKeNhanVien();
    }
    
    public Vector<NhanVienDTO> getSearchNhanVien(String searchString){
        return nvDAL.getSearchNhanVien(searchString);
    }
    
    public Vector<NhanVienDTO> getSearchTk(String searchString){
        return nvDAL.getSearchTk(searchString);
    }
    
    public String addNhanVien(NhanVienDTO nvm){
        if(nvDAL.hasNhanVien(nvm.getMaNV()))
            return "Mã nhân viên đã tồn tại";
        if(nvDAL.addNhanVien(nvm))
            return "Thêm thành công";
        return "Thêm thất bại";
    }
    
    public String delNhanVien(String deletingMaNV){
        if (nvDAL.delNhanVien(deletingMaNV))
            return "Xóa thành công";
        return "Xóa thất bại";
    }

    public String editNhanVien(NhanVienDTO editingNhanVien, String condition){
        if (!editingNhanVien.getMaNV().equals(condition)){
            if (nvDAL.hasNhanVien(editingNhanVien.getMaNV()))
                return "Mã chức vụ đã tồn tại";
            if (nvDAL.editNhanVien(editingNhanVien, condition))
                return "Sửa thành công";
        }
        else if (nvDAL.editNhanVien(editingNhanVien, condition))
            return "Sửa thành công";
        return "Sửa thất bại";
    }
    
    public Vector<String> getMaNVList(){
        Vector <NhanVienDTO> nvList = new Vector<NhanVienDTO>();
        nvList = nvDAL.getAllNhanVien();
        
        Vector <String> tdList = new Vector<String>();
        for (NhanVienDTO nv : nvList) {
            tdList.add(nv.getMaNV());
        }
        return tdList;
    }
    
}
