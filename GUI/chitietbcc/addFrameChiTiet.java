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

public class addFrameChiTiet extends JFrame {
    private chitietbccGUI parentGUI;
    
    // Khai báo một đối tượng DateTimeFormatter
    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    JTextField mactTextField, NgayLapTextField , SoNNTextField , MaKTTextField , MaKLTextField;
    JButton saveButton, cancelButton;

    chiTietBangChamCongBUS ChiTietBLL = new chiTietBangChamCongBUS();

    public addFrameChiTiet(chitietbccGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thêm chi tiết của bảng chấm công"); 
        mactTextField = new JTextField(20);
        NgayLapTextField = new JTextField(20);
        SoNNTextField = new JTextField(20);
        MaKTTextField = new JTextField(20);
        MaKLTextField = new JTextField(20);
        
        saveButton = new JButton("Lưu"); 
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mactTextField.getText().trim().equals("") || NgayLapTextField.getText().trim().equals("") || SoNNTextField.getText().trim().equals("")) 
                    JOptionPane.showMessageDialog(addFrameChiTiet.this,"Vui lòng nhập đủ thông tin");
                else{
                    
                    chiTietBangChamCongDTO nv = new chiTietBangChamCongDTO();
                    nv.setmaChiTietBangChamCong(mactTextField.getText());
                    nv.setNgayLap(LocalDate.parse(NgayLapTextField.getText().trim(),Formatter));
                    nv.setsoNgayNghi(Integer.parseInt(SoNNTextField.getText()));
                    nv.setKhenThuong_MaKT(MaKTTextField.getText());
                    nv.setKiLuat_MaKL(MaKLTextField.getText());
                    
                    String result = ChiTietBLL.addChiTiet(nv);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadChiTietList();
                    addFrameChiTiet.this.dispose();
                }
            }            
        });

        cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrameChiTiet.this.dispose();
            }            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Mã chi tiết : "));
        inputPanel.add(mactTextField);
        inputPanel.add(new JLabel("Ngày Lập : "));
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
