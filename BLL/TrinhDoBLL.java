package BLL;

import DAL.TrinhDoDAL;
import DTO.TrinhDoDTO;
import java.util.Vector;

public class TrinhDoBLL {
    TrinhDoDAL nvDAL = new TrinhDoDAL();
    
    public Vector<TrinhDoDTO> getAllTrinhDo(){
        return nvDAL.getAllTrinhDo();
    }
    
    public Vector<TrinhDoDTO> getSearchTrinhDo(String searchString){
        return nvDAL.getSearchTrinhDo(searchString);
    }
    
    public String addTrinhDo(TrinhDoDTO nvm){
        if(nvDAL.hasTrinhDo(nvm.getMaTDHV()))
            return "Mã trình độ này đã tồn tại";
        if(nvDAL.addTrinhDo(nvm))
            return "Thêm thành công";
        return "Thêm thất bại";
    }
    
    public String delTrinhDo(String deletingMaTDHV){
        if(nvDAL.KTTrinhDo(deletingMaTDHV))
            return "Không xóa được do mã có tồn tại ở bảng Nhân viên";
        if (nvDAL.delTrinhDo(deletingMaTDHV))
            return "Xóa thành công";
        return "Xóa thất bại";
    }

    public String editTrinhDo(TrinhDoDTO editingTrinhDo, String condition){
        if (!editingTrinhDo.getMaTDHV().equals(condition)){
            if (nvDAL.hasTrinhDo(editingTrinhDo.getMaTDHV()))
                return "Mã trình độ này đã tồn tại";
            if (nvDAL.editTrinhDo(editingTrinhDo, condition))
                return "Sửa thành công";
        }
        else if (nvDAL.editTrinhDo(editingTrinhDo, condition))
            return "Sửa thành công";
        return "Sửa thất bại";
    }
    
    public Vector<String> getTrinhDoList(){
        Vector <TrinhDoDTO> nvList = new Vector<TrinhDoDTO>();
        nvList = nvDAL.getAllTrinhDo();
        
        Vector <String> tdList = new Vector<String>();
        for (TrinhDoDTO nv : nvList) {
            tdList.add(nv.getMaTDHV());
        }
        return tdList;
    }
    
}
