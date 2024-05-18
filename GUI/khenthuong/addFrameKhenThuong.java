package GUI.khenthuong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BLL.*;
import DTO.khenThuongDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class addFrameKhenThuong extends JFrame {
    private khenThuongGUI parentGUI;
    
    // Khai báo một đối tượng DateTimeFormatter
    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    JTextField maktTextField, lidoTextField , NgayKTTextField ;
    JButton saveButton, cancelButton;

    khenThuongBLL NhanVienBLL = new khenThuongBLL();
    
    public addFrameKhenThuong(khenThuongGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thêm mục khen thưởng"); 
        maktTextField = new JTextField(20);
        lidoTextField = new JTextField(20);
        NgayKTTextField = new JTextField(20);
        
        
        saveButton = new JButton("Lưu"); 
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maktTextField.getText().trim().equals("") || lidoTextField.getText().trim().equals("") || NgayKTTextField.getText().trim().equals("")) 
                    JOptionPane.showMessageDialog(addFrameKhenThuong.this,"Vui lòng nhập đủ thông tin");
                else{
                    
                    khenThuongDTO nv = new khenThuongDTO();
                    nv.setMaKT(maktTextField.getText());
                    nv.setLiDo(lidoTextField.getText());
                    nv.setNgayKT(LocalDate.parse(NgayKTTextField.getText().trim(),Formatter));
                    
                    String result = NhanVienBLL.addKhen(nv);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadKhenThuongList();
                    addFrameKhenThuong.this.dispose();
                }
            }            
        });

        cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrameKhenThuong.this.dispose();
            }            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Mã khen thưởng : "));
        inputPanel.add(maktTextField);
        inputPanel.add(new JLabel("Lí do : "));
        inputPanel.add(lidoTextField);
        inputPanel.add(new JLabel("Ngày lập : "));
        inputPanel.add(NgayKTTextField);
        
        this.setSize(600,170);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
