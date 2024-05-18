package GUI.chitietbcc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BLL.*;
import DTO.chiTietBangChamCongDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class editFrameChiTiet extends JFrame {
    private chitietbccGUI parentGUI;
    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    JTextField mactTextField, NgayLapTextField , SoNNTextField , MaKTTextField, MaKLTextField ;
    JButton saveButton, cancelButton;

    chiTietBangChamCongBUS ChiTietBLL = new chiTietBangChamCongBUS();

    public editFrameChiTiet(chitietbccGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Chỉnh sửa chi tiết bảng chấm công"); 

        int selectedRow = parentGUI.nhanvienTable.getSelectedRow();

        mactTextField = new JTextField(20);
        NgayLapTextField = new JTextField(20);
        SoNNTextField = new JTextField(20);
        MaKTTextField = new JTextField(20);
        MaKLTextField = new JTextField(20);
        
        mactTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString());
        NgayLapTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 1).toString());
        SoNNTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 2).toString());
        MaKTTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 3).toString());
        MaKLTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 4).toString());
        
        saveButton = new JButton("Lưu");
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mactTextField.getText().trim().equals("") || NgayLapTextField.getText().trim().equals("") || SoNNTextField.getText().trim().equals("") )  
                    JOptionPane.showMessageDialog(editFrameChiTiet.this,"Vui lòng nhập đủ thông tin");
                else{
                    chiTietBangChamCongDTO nv = new chiTietBangChamCongDTO();
                    nv.setmaChiTietBangChamCong(mactTextField.getText());
                    nv.setNgayLap(LocalDate.parse(NgayLapTextField.getText().trim(),Formatter));
                    nv.setsoNgayNghi(Integer.parseInt(SoNNTextField.getText()));
                    nv.setKhenThuong_MaKT(MaKTTextField.getText());
                    nv.setKiLuat_MaKL(MaKLTextField.getText());
                    //condition la ID của dữ liệu dùng để khi truy vấn câu lệnh sql tại WHERE = ...
                    String condition = parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString();
                    String result = ChiTietBLL.editChiTiet(nv, condition);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadChiTietList();
                    editFrameChiTiet.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFrameChiTiet.this.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Mã chi tiết : "));
        inputPanel.add(mactTextField);
        inputPanel.add(new JLabel("Ngày lập : "));
        inputPanel.add(NgayLapTextField);
        inputPanel.add(new JLabel("Số ngày nghỉ : "));
        inputPanel.add(SoNNTextField);
        inputPanel.add(new JLabel("Mã khen thưởng : "));
        inputPanel.add(MaKTTextField);
        inputPanel.add(new JLabel("Mã kỉ luật : "));
        inputPanel.add(MaKLTextField);
        
        this.setSize(250,320);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
