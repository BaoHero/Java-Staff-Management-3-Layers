package DTO;

import java.time.LocalDate;

public class chiTietBangChamCongDTO {
	private String maChiTietBangChamCong;
	private LocalDate ngayLap;
	private int soNgayNghi;
	private String KhenThuong_MaKT;
	private String KiLuat_MaKL;
	
        
	public String getmaChiTietBangChamCong() {
		return maChiTietBangChamCong;
	}
	public void setmaChiTietBangChamCong(String maChiTietBangChamCong) {
		this.maChiTietBangChamCong = maChiTietBangChamCong;
	}
	
	public LocalDate getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}
	
	public int getsoNgayNghi() {
		return soNgayNghi;
	}
	public void setsoNgayNghi(int soNgayNghi) {
		this.soNgayNghi = soNgayNghi;
	}

	public String getKhenThuong_MaKT() {
		return KhenThuong_MaKT;
	}
	public void setKhenThuong_MaKT(String KhenThuong_MaKT) {
		this.KhenThuong_MaKT = KhenThuong_MaKT;
	}
	
	public String getKiLuat_MaKL() {
		return KiLuat_MaKL;
	}
	public void setKiLuat_MaKL(String KiLuat_MaKL) {
		this.KiLuat_MaKL= KiLuat_MaKL;
	}
}
