package GUI.hopdong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BLL.*;
import DTO.HopDongDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class addFrameHopDong extends JFrame {
    private hopdongGUI parentGUI;
    
    // Khai báo một đối tượng DateTimeFormatter
    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    JTextField maHDTextField, NgayBDTextField , NgayKTTextField , NgayNVTextField;
    JComboBox maNVComboBox, loaiHDComboBox  ;
    JButton saveButton, cancelButton;

    NhanVienBLL NhanVienBLL = new NhanVienBLL();
    HopDongBLL HopDongBLL = new HopDongBLL();

    public addFrameHopDong(hopdongGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thêm hợp đồng"); 
        maHDTextField = new JTextField(20);
        NgayBDTextField = new JTextField(20);
        NgayKTTextField = new JTextField(20);
        NgayNVTextField = new JTextField(20);
        
        JComboBox<String> loaiHDComboBox = new JComboBox<>();
        loaiHDComboBox.addItem("Có thời hạn");
        loaiHDComboBox.addItem("Theo mùa vụ");
        
        Vector <String> MaNVList = new Vector<String>();
        MaNVList = NhanVienBLL.getMaNVList();
        maNVComboBox = new JComboBox <>(MaNVList);
        
        saveButton = new JButton("Lưu"); 
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maHDTextField.getText().trim().equals("") || NgayBDTextField.getText().trim().equals("") || NgayKTTextField.getText().trim().equals("") || NgayNVTextField.getText().trim().equals("")) 
                    JOptionPane.showMessageDialog(addFrameHopDong.this,"Vui lòng nhập đủ thông tin");
                else{
                    
                    HopDongDTO nv = new HopDongDTO();
                    nv.setMaHD(maHDTextField.getText());
                    nv.setNHANVIEN_MaNV(maNVComboBox.getSelectedItem().toString());
                    nv.setLoaiHD(loaiHDComboBox.getSelectedItem().toString());
                    nv.setNgayBD(LocalDate.parse(NgayBDTextField.getText().trim(),Formatter));
                    nv.setNgayKT(LocalDate.parse(NgayKTTextField.getText().trim(),Formatter));
                    nv.setNgayNghiViec(LocalDate.parse(NgayNVTextField.getText().trim(),Formatter));
                    
                    String result = HopDongBLL.addHopDong(nv);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadHopDongList();
                    addFrameHopDong.this.dispose();
                }
            }            
        });

        cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrameHopDong.this.dispose();
            }            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Mã hợp đồng : "));
        inputPanel.add(maHDTextField);
        inputPanel.add(new JLabel("Mã nhân viên : "));
        inputPanel.add(maNVComboBox);
        inputPanel.add(new JLabel("Loại hợp đồng : "));
        inputPanel.add(loaiHDComboBox);
        inputPanel.add(new JLabel("Ngày bắt đầu : "));
        inputPanel.add(NgayBDTextField);
        inputPanel.add(new JLabel("Ngày kết thúc: "));
        inputPanel.add(NgayKTTextField);
        inputPanel.add(new JLabel("Ngày nghỉ : "));
        inputPanel.add(NgayNVTextField);

        this.setSize(670,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
