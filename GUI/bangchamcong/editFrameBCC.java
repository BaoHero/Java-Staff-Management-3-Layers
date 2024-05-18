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

public class editFrameBCC extends JFrame {
    private bangchamcongGUI parentGUI;

    JTextField mabccTextField ;
    JComboBox maNVComboBox, maCTComboBox ;
    JButton saveButton, cancelButton;

    NhanVienBLL NhanVienBLL = new NhanVienBLL();
    chiTietBangChamCongBUS ChiTietBLL = new chiTietBangChamCongBUS();
    bangChamCongBUS ChamCongBLL = new bangChamCongBUS();

    public editFrameBCC(bangchamcongGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Sửa bảng chấm công"); 

        int selectedRow = parentGUI.nhanvienTable.getSelectedRow();

        mabccTextField = new JTextField(20);
        
        mabccTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString());
       
        Vector <String> MaNVList = new Vector<String>();
        MaNVList = NhanVienBLL.getMaNVList();
        maNVComboBox = new JComboBox <>(MaNVList);
        
        Vector <String> MaCTList = new Vector<String>();
        MaCTList = ChiTietBLL.getMaCTList();
        maCTComboBox = new JComboBox <>(MaCTList);

        String selectedmanv = parentGUI.nhanvienTable.getValueAt(selectedRow, 1).toString();
        String selectedmact = parentGUI.nhanvienTable.getValueAt(selectedRow, 2).toString();

        maNVComboBox.setSelectedItem(selectedmanv);
        maCTComboBox.setSelectedItem(selectedmact);

        saveButton = new JButton("Lưu");
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mabccTextField.getText().trim().equals("") )  
                    JOptionPane.showMessageDialog(editFrameBCC.this,"Vui lòng nhập đủ thông tin");
                else{
                    bangChamCongDTO nv = new bangChamCongDTO();
                    nv.setMaBangChamCong(mabccTextField.getText());
                    nv.setNHANVIEN_MaNV(maNVComboBox.getSelectedItem().toString());
                    nv.setChiTietBangChamCong_maBangChamCong(maCTComboBox.getSelectedItem().toString());
                   
                    //condition la ID của dữ liệu dùng để khi truy vấn câu lệnh sql tại WHERE = ...
                    String condition = parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString();
                    String result = ChamCongBLL.editChamCong(nv, condition);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadChamCongList();
                    editFrameBCC.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFrameBCC.this.dispose();
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
        inputPanel.add(new JLabel("Mã chi tiết : "));
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
