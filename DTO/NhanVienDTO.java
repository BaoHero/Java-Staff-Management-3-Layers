package DTO;

import java.time.LocalDate;

public class NhanVienDTO {
    private String MaNV;
    private String HoTen;
    private String GioiTinh;
    private LocalDate NgaySinh;
    private String SoDienThoai;
    private String TrangThai;
    private String ChucVu_MaCV;
    private LocalDate NgayNhanCV;
    private String TrinhDoHocVan_MaTDHV;
    
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getChucVu_MaCV() {
        return ChucVu_MaCV;
    }

    public void setChucVu_MaCV(String ChucVu_MaCV) {
        this.ChucVu_MaCV = ChucVu_MaCV;
    }

    public LocalDate getNgayNhanCV() {
        return NgayNhanCV;
    }

    public void setNgayNhanCV(LocalDate NgayNhanCV) {
        this.NgayNhanCV = NgayNhanCV;
    }

    public String getTrinhDoHocVan_MaTDHV() {
        return TrinhDoHocVan_MaTDHV;
    }

    public void setTrinhDoHocVan_MaTDHV(String TrinhDoHocVan_MaTDHV) {
        this.TrinhDoHocVan_MaTDHV = TrinhDoHocVan_MaTDHV;
    }
}
