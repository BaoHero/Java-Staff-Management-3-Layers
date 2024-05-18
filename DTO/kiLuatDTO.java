package DTO;
import java.time.LocalDate;


public class kiLuatDTO {
    private String maKL;
    private String liDo;
    private LocalDate ngayKL;

    public String getMaKL() {
        return maKL;
    }

    public void setMaKL(String maKL) {
        this.maKL = maKL;
    }

    public String getLiDo() {
        return liDo;
    }

    public void setLiDo(String liDo) {
        this.liDo = liDo;
    }

    public LocalDate getNgayKL() {
        return ngayKL;
    }

    public void setNgayKL(LocalDate ngayKL) {
        this.ngayKL = ngayKL;
    }
    

}

