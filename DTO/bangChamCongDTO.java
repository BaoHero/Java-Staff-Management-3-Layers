package DTO;

public class bangChamCongDTO {
	private String maBangChamCong;
	private String NHANVIEN_MaNV;
	private String chiTietBangChamCong_maBangChamCong;
	private int SoNgayNghi;
	
	public String getMaBangChamCong() {
		return maBangChamCong;
	}
	public void setMaBangChamCong(String maBangChamCong) {
		this.maBangChamCong = maBangChamCong;
	}
	
	public String getNHANVIEN_MaNV() {
		return NHANVIEN_MaNV;
	}
	public void setNHANVIEN_MaNV(String NHANVIEN_MaNV) {
		this.NHANVIEN_MaNV = NHANVIEN_MaNV;
	}
	
	public String getChiTietBangChamCong_maBangChamCong() {
		return chiTietBangChamCong_maBangChamCong;
	}
	public void setChiTietBangChamCong_maBangChamCong(String chiTietBangChamCong_maBangChamCong) {
		this.chiTietBangChamCong_maBangChamCong = chiTietBangChamCong_maBangChamCong;
	}
        
        public int getSoNgayNghi() {
        return SoNgayNghi;
    }

    public void setSoNgayNghi(int SoNgayNghi) {
        this.SoNgayNghi = SoNgayNghi;
    }
}
