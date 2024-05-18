package GUI.phongban;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BLL.phongbanBLL;
import DTO.phongbanDTO;

public class editPhongBanFrame extends JFrame {
    private phongbanGUI parentGUI;
    JTextField maPBTextField, tenPBTextField;
    JButton saveButton, cancelButton;

    phongbanBLL phongbanBLL = new phongbanBLL();

    public editPhongBanFrame(phongbanGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Chỉnh sửa phòng ban");

        int selectedRow = parentGUI.phongbanTable.getSelectedRow();

        maPBTextField = new JTextField(20);
        tenPBTextField = new JTextField(20);

        maPBTextField.setText(parentGUI.phongbanTable.getValueAt(selectedRow, 0).toString());
        tenPBTextField.setText(parentGUI.phongbanTable.getValueAt(selectedRow, 1).toString());

        saveButton = new JButton("Lưu"); 
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maPBTextField.getText().trim().equals("") || tenPBTextField.getText().trim().equals("")) 
                    JOptionPane.showMessageDialog(editPhongBanFrame.this,"Vui lòng nhập đủ thông tin");
                else{
                    phongbanDTO editingPhongBan = new phongbanDTO();
                    editingPhongBan.setMaPB(maPBTextField.getText());
                    editingPhongBan.setTenPB(tenPBTextField.getText());

                    //condition la ID của dữ liệu dùng để khi truy vấn câu lệnh sql tại WHERE = ...
                    String condition = parentGUI.phongbanTable.getValueAt(selectedRow, 0).toString();
                    String result = phongbanBLL.editPhongBan(editingPhongBan, condition);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadPhongBanList();
                    editPhongBanFrame.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPhongBanFrame.this.dispose();
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
