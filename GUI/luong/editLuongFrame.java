package GUI.luong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BLL.luongBLL;
import DTO.luongDTO;

public class editLuongFrame extends JFrame {
    private luongGUI parentGUI;
    JTextField maLuongTextField, tienLuongTextField;
    JButton saveButton, cancelButton;

    luongBLL luongBLL = new luongBLL();

    public editLuongFrame(luongGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Chỉnh sửa lương");

        int selectedRow = parentGUI.luongTable.getSelectedRow();

        maLuongTextField = new JTextField(20);
        tienLuongTextField = new JTextField(20);

        maLuongTextField.setText(parentGUI.luongTable.getValueAt(selectedRow, 0).toString());
        tienLuongTextField.setText(parentGUI.luongTable.getValueAt(selectedRow, 1).toString());

        saveButton = new JButton("Lưu"); 
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maLuongTextField.getText().trim().equals("") || tienLuongTextField.getText().trim().equals("")) 
                    JOptionPane.showMessageDialog(editLuongFrame.this,"Vui lòng nhập đủ thông tin");
                else{
                    luongDTO editingluong = new luongDTO();
                    editingluong.setMaLuong(maLuongTextField.getText());
                    editingluong.setTienLuong(tienLuongTextField.getText());

                    //condition la ID của dữ liệu dùng để khi truy vấn câu lệnh sql tại WHERE = ...
                    String condition = parentGUI.luongTable.getValueAt(selectedRow, 0).toString();
                    String result = luongBLL.editLuong(editingluong, condition);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadLuongList();
                    editLuongFrame.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editLuongFrame.this.dispose();
            }            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Mã phòng ban: "));
        inputPanel.add(maLuongTextField);
        inputPanel.add(new JLabel("Tên phòng ban: "));
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
