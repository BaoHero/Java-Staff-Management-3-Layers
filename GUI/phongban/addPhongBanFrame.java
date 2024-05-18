package GUI.phongban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BLL.phongbanBLL;
import DTO.phongbanDTO;

public class addPhongBanFrame extends JFrame {
    private phongbanGUI parentGUI;
    JTextField maPBTextField, tenPBTextField;
    JButton saveButton, cancelButton;

    phongbanBLL phongbanBLL = new phongbanBLL();

    public addPhongBanFrame(phongbanGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thêm phòng ban");

        maPBTextField = new JTextField(20);
        tenPBTextField = new JTextField(20);

        saveButton = new JButton("Lưu"); 
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maPBTextField.getText().trim().equals("") || tenPBTextField.getText().trim().equals("")) 
                    JOptionPane.showMessageDialog(addPhongBanFrame.this,"Vui lòng nhập đủ thông tin");
                else{
                    phongbanDTO newPhongBan = new phongbanDTO();
                    newPhongBan.setMaPB(maPBTextField.getText());
                    newPhongBan.setTenPB(tenPBTextField.getText());
                    String result = phongbanBLL.addPhongBan(newPhongBan);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadPhongBanList();
                    addPhongBanFrame.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPhongBanFrame.this.dispose();
            }            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Mã phòng ban: "));
        inputPanel.add(maPBTextField);
        inputPanel.add(new JLabel("Tên phòng ban: "));
        inputPanel.add(tenPBTextField);

        this.setSize(350,150);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
