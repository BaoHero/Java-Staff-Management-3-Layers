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

public class addFrameKiLuat extends JFrame {
    private kiluatGUI parentGUI;
    
    // Khai báo một đối tượng DateTimeFormatter
    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    JTextField maklTextField, lidoTextField , NgayKLTextField ;
    JButton saveButton, cancelButton;

    kiLuatBLL NhanVienBLL = new kiLuatBLL();
    
    public addFrameKiLuat(kiluatGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thêm mục kỉ luật"); 
        maklTextField = new JTextField(20);
        lidoTextField = new JTextField(20);
        NgayKLTextField = new JTextField(20);
        
        
        saveButton = new JButton("Lưu"); 
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maklTextField.getText().trim().equals("") || lidoTextField.getText().trim().equals("") || NgayKLTextField.getText().trim().equals("")) 
                    JOptionPane.showMessageDialog(addFrameKiLuat.this,"Vui lòng nhập đủ thông tin");
                else{
                    
                    kiLuatDTO nv = new kiLuatDTO();
                    nv.setMaKL(maklTextField.getText());
                    nv.setLiDo(lidoTextField.getText());
                    nv.setNgayKL(LocalDate.parse(NgayKLTextField.getText().trim(),Formatter));
                    
                    String result = NhanVienBLL.addViPham(nv);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadKiLuatList();
                    addFrameKiLuat.this.dispose();
                }
            }            
        });

        cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrameKiLuat.this.dispose();
            }            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Mã kĩ luật : "));
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
