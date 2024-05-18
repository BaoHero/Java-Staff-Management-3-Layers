package GUI.kiluat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BLL.*;
import DTO.kiLuatDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class editFrameKiLuat extends JFrame {
    private kiluatGUI parentGUI;
    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    JTextField maklTextField, lidoTextField , NgayKLTextField ;
    JButton saveButton, cancelButton;

    kiLuatBLL NhanVienBLL = new kiLuatBLL();
    
    public editFrameKiLuat(kiluatGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Chỉnh sửa thông tin kỉ luật"); 

        int selectedRow = parentGUI.nhanvienTable.getSelectedRow();

        maklTextField = new JTextField(20);
        lidoTextField = new JTextField(20);
        NgayKLTextField = new JTextField(20);
        
        maklTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString());
        lidoTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 1).toString());
        NgayKLTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 2).toString());
        

        saveButton = new JButton("Lưu");
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maklTextField.getText().trim().equals("") || lidoTextField.getText().trim().equals("") || NgayKLTextField.getText().trim().equals("") )  
                    JOptionPane.showMessageDialog(editFrameKiLuat.this,"Vui lòng nhập đủ thông tin");
                else{
                    kiLuatDTO nv = new kiLuatDTO();
                    nv.setMaKL(maklTextField.getText());
                    nv.setLiDo(lidoTextField.getText());
                    nv.setNgayKL(LocalDate.parse(NgayKLTextField.getText().trim(),Formatter));
                    
                    //condition la ID của dữ liệu dùng để khi truy vấn câu lệnh sql tại WHERE = ...
                    String condition = parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString();
                    String result = NhanVienBLL.editKiLuat(nv, condition);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadKiLuatList();
                    editFrameKiLuat.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFrameKiLuat.this.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Mã kỉ luật : "));
        inputPanel.add(maklTextField);
        inputPanel.add(new JLabel("Lí do : "));
        inputPanel.add(lidoTextField);
        inputPanel.add(new JLabel("Ngày lập : "));
        inputPanel.add(NgayKLTextField);

        this.setSize(600,170);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
