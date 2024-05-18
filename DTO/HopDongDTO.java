package DTO;

import java.time.LocalDate;

public class HopDongDTO {
    private String MaHD;
    private String NHANVIEN_MaNV;
    private String LoaiHD;
    private LocalDate NgayBD;
    private LocalDate NgayKT;
    private LocalDate NgayNghiViec;
    
    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getNHANVIEN_MaNV() {
        return NHANVIEN_MaNV;
    }

    public void setNHANVIEN_MaNV(String NHANVIEN_MaNV) {
        this.NHANVIEN_MaNV = NHANVIEN_MaNV;
    }

    public String getLoaiHD() {
        return LoaiHD;
    }

    public void setLoaiHD(String LoaiHD) {
        this.LoaiHD = LoaiHD;
    }

    public LocalDate getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(LocalDate NgayBD) {
        this.NgayBD = NgayBD;
    }

    public LocalDate getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(LocalDate NgayKT) {
        this.NgayKT = NgayKT;
    }
    
    public LocalDate getNgayNghiViec() {
        return NgayNghiViec;
    }

    public void setNgayNghiViec(LocalDate NgayNghiViec) {
        this.NgayNghiViec = NgayNghiViec;
    }
}

