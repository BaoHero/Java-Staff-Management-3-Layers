package DTO;

public class chucvuDTO {
    private String MaCV;
    private String TenCVHT;
    private String PhongBan_MaPB;
    private String Luong_MaLuong;
    
    public String getMaCV(){
        return MaCV;
    }
    public void setMaCV(String MaCV){
        this.MaCV = MaCV;
    }
    public String getTenCVHT(){
        return TenCVHT;
    }
    public void setTenCVHT(String TenCVHT){
        this.TenCVHT = TenCVHT;
    }
    public void setPhongBan_MaPB(String PhongBan_MaPB){
        this.PhongBan_MaPB = PhongBan_MaPB;
    }
    public String getPhongBan_MaPB(){
        return PhongBan_MaPB;
    }
    public void setLuong_MaLuong(String Luong_MaLuong){
        this.Luong_MaLuong = Luong_MaLuong;
    }
    public String getLuong_MaLuong(){
        return Luong_MaLuong;
    }
}
