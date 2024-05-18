package GUI.luong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BLL.luongBLL;
import DTO.luongDTO;

public class addLuongFrame extends JFrame {
    private luongGUI parentGUI;
    JTextField maLuongTextField, tienLuongTextField;
    JButton saveButton, cancelButton;

    luongBLL luongBLL = new luongBLL();

    public addLuongFrame(luongGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thêm vào danh sách lương");

        maLuongTextField = new JTextField(20);
        tienLuongTextField = new JTextField(20);

        saveButton = new JButton("Lưu"); 
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maLuongTextField.getText().trim().equals("") || tienLuongTextField.getText().trim().equals("")) 
                    JOptionPane.showMessageDialog(addLuongFrame.this,"Vui lòng nhập đủ thông tin");
                else{
                    luongDTO newLuong = new luongDTO();
                    newLuong.setMaLuong(maLuongTextField.getText());
                    newLuong.setTienLuong(tienLuongTextField.getText());
                    String result = luongBLL.addLuong(newLuong);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadLuongList();
                    addLuongFrame.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLuongFrame.this.dispose();
            }            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Mã lương: "));
        inputPanel.add(maLuongTextField);
        inputPanel.add(new JLabel("Tiền lương: "));
        inputPanel.add(tienLuongTextField);

        this.setSize(350,150);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
