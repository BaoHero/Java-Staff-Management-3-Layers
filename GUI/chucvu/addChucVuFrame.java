package GUI.chucvu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BLL.*;
import DTO.chucvuDTO;

public class addChucVuFrame extends JFrame {
    private chucvuGUI parentGUI;

    JTextField maCVTextField, tenCVHTTextField;
    JComboBox phongbanComboBox, luongComboBox;
    JButton saveButton, cancelButton;

    chucvuBLL chucvuBLL = new chucvuBLL();
    phongbanBLL phongbanBLL = new phongbanBLL();
    luongBLL luongBLL = new luongBLL();

    public addChucVuFrame(chucvuGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thêm chức vụ"); 
        maCVTextField = new JTextField(20);
        tenCVHTTextField = new JTextField(20);

        Vector <String> tenPhongBanList = new Vector<String>();
        Vector <String> maluongList = new Vector<String>();

        maluongList = luongBLL.getMaLuongList();
        tenPhongBanList = phongbanBLL.getTenPhongBanList();

        phongbanComboBox = new JComboBox <>(tenPhongBanList);
        luongComboBox = new JComboBox <>(maluongList);

        saveButton = new JButton("Lưu"); 
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maCVTextField.getText().trim().equals("") || tenCVHTTextField.getText().trim().equals("")) 
                    JOptionPane.showMessageDialog(addChucVuFrame.this,"Vui lòng nhập đủ thông tin");
                else{
                    chucvuDTO newChucVu = new chucvuDTO();
                    newChucVu.setMaCV(maCVTextField.getText());
                    newChucVu.setTenCVHT(tenCVHTTextField.getText());
                    newChucVu.setLuong_MaLuong(luongComboBox.getSelectedItem().toString());
                    newChucVu.setPhongBan_MaPB(phongbanBLL.getMaPB_by_TenPB(phongbanComboBox.getSelectedItem().toString()));

                    String result = chucvuBLL.addChucVu(newChucVu);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadChucVuList();
                    addChucVuFrame.this.dispose();
                }
            }            
        });

        cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addChucVuFrame.this.dispose();
            }            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Mã chức vụ: "));
        inputPanel.add(maCVTextField);
        inputPanel.add(new JLabel("Tên chức vụ: "));
        inputPanel.add(tenCVHTTextField);
        inputPanel.add(new JLabel("Phòng ban: "));
        inputPanel.add(phongbanComboBox);
        inputPanel.add(new JLabel("Mã lương: "));
        inputPanel.add(luongComboBox);

        this.setSize(320,200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
