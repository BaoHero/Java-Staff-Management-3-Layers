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

public class editFrameHopDong extends JFrame {
    private hopdongGUI parentGUI;
    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    JTextField maHDTextField, NgayBDTextField , NgayKTTextField , NgayNVTextField;
    JComboBox maNVComboBox, loaiHDComboBox ;
    JButton saveButton, cancelButton;

    NhanVienBLL NhanVienBLL = new NhanVienBLL();
    HopDongBLL HopDongBLL = new HopDongBLL();

    public editFrameHopDong(hopdongGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Chỉnh sửa hợp đồng"); 

        int selectedRow = parentGUI.nhanvienTable.getSelectedRow();

        maHDTextField = new JTextField(20);
        NgayBDTextField = new JTextField(20);
        NgayKTTextField = new JTextField(20);
        NgayNVTextField = new JTextField(20);
        
        maHDTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString());
        NgayBDTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 3).toString());
        NgayKTTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 4).toString());
        NgayNVTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 5).toString());
       
        JComboBox<String> loaiHDComboBox = new JComboBox<>();
        loaiHDComboBox.addItem("Có thời hạn");
        loaiHDComboBox.addItem("Theo mùa vụ");
        
        Vector <String> MaNVList = new Vector<String>();
        MaNVList = NhanVienBLL.getMaNVList();
        maNVComboBox = new JComboBox <>(MaNVList);

        String selectedmanv = parentGUI.nhanvienTable.getValueAt(selectedRow, 1).toString();
        String selectedloaihd = parentGUI.nhanvienTable.getValueAt(selectedRow, 2).toString();

        maNVComboBox.setSelectedItem(selectedmanv);
        loaiHDComboBox.setSelectedItem(selectedloaihd);

        saveButton = new JButton("Lưu");
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maHDTextField.getText().trim().equals("") || NgayBDTextField.getText().trim().equals("") || NgayKTTextField.getText().trim().equals("") || NgayNVTextField.getText().trim().equals(""))  
                    JOptionPane.showMessageDialog(editFrameHopDong.this,"Vui lòng nhập đủ thông tin");
                else{
                    HopDongDTO nv = new HopDongDTO();
                    nv.setMaHD(maHDTextField.getText());
                    nv.setNHANVIEN_MaNV(maNVComboBox.getSelectedItem().toString());
                    nv.setLoaiHD(loaiHDComboBox.getSelectedItem().toString());
                    nv.setNgayBD(LocalDate.parse(NgayBDTextField.getText().trim(),Formatter));
                    nv.setNgayKT(LocalDate.parse(NgayKTTextField.getText().trim(),Formatter));
                    nv.setNgayNghiViec(LocalDate.parse(NgayNVTextField.getText().trim(),Formatter));
                    //condition la ID của dữ liệu dùng để khi truy vấn câu lệnh sql tại WHERE = ...
                    String condition = parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString();
                    String result = HopDongBLL.editHopDong(nv, condition);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadHopDongList();
                    editFrameHopDong.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFrameHopDong.this.dispose();
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
        inputPanel.add(new JLabel("Ngày kết thúc : "));
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
