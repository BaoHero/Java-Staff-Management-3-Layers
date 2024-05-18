package GUI.bangchamcong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BLL.*;
import DTO.bangChamCongDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class addFrameBCC extends JFrame {
    private bangchamcongGUI parentGUI;
    
    JTextField mabccTextField ;
    JComboBox maNVComboBox, maCTComboBox  ;
    JButton saveButton, cancelButton;

    NhanVienBLL NhanVienBLL = new NhanVienBLL();
    chiTietBangChamCongBUS ChiTietBLL = new chiTietBangChamCongBUS();
    bangChamCongBUS ChamCongBLL = new bangChamCongBUS();

    public addFrameBCC(bangchamcongGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thêm bảng chấm công"); 
        mabccTextField = new JTextField(20);
        
        Vector <String> MaNVList = new Vector<String>();
        MaNVList = NhanVienBLL.getMaNVList();
        maNVComboBox = new JComboBox <>(MaNVList);
        
        Vector <String> MaCTList = new Vector<String>();
        MaCTList = ChiTietBLL.getMaCTList();
        maCTComboBox = new JComboBox <>(MaCTList);
        
        saveButton = new JButton("Lưu"); 
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mabccTextField.getText().trim().equals("") ) 
                    JOptionPane.showMessageDialog(addFrameBCC.this,"Vui lòng nhập đủ thông tin");
                else{
                    
                    bangChamCongDTO nv = new bangChamCongDTO();
                    nv.setMaBangChamCong(mabccTextField.getText());
                    nv.setNHANVIEN_MaNV(maNVComboBox.getSelectedItem().toString());
                    nv.setChiTietBangChamCong_maBangChamCong(maCTComboBox.getSelectedItem().toString());
                    
                    String result = ChamCongBLL.addChamCong(nv);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadChamCongList();
                    addFrameBCC.this.dispose();
                }
            }            
        });

        cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrameBCC.this.dispose();
            }            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Mã bảng chấm công : "));
        inputPanel.add(mabccTextField);
        inputPanel.add(new JLabel("Mã nhân viên : "));
        inputPanel.add(maNVComboBox);
        inputPanel.add(new JLabel("Mã chi tiết của bảng : "));
        inputPanel.add(maCTComboBox);
        

        this.setSize(400,170);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
