package GUI.chucvu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BLL.*;
import DTO.chucvuDTO;

public class editChucVuFrame extends JFrame {
    private chucvuGUI parentGUI;

    JTextField maCVTextField, tenCVHTTextField;
    JComboBox phongbanComboBox, luongComboBox;
    JButton saveButton, cancelButton;

    phongbanBLL phongbanBLL = new phongbanBLL();
    luongBLL luongBLL = new luongBLL();  
    chucvuBLL chucvuBLL = new chucvuBLL();

    public editChucVuFrame(chucvuGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Chỉnh sửa chức vụ"); 

        int selectedRow = parentGUI.chucvuTable.getSelectedRow();

        maCVTextField = new JTextField(20);
        tenCVHTTextField = new JTextField(20);

        maCVTextField.setText(parentGUI.chucvuTable.getValueAt(selectedRow, 0).toString());
        tenCVHTTextField.setText(parentGUI.chucvuTable.getValueAt(selectedRow, 1).toString());

        Vector <String> tenPhongBanList = new Vector<>();
        Vector <String> maluongList = new Vector<>();

        tenPhongBanList = phongbanBLL.getTenPhongBanList();
        maluongList = luongBLL.getMaLuongList();

        phongbanComboBox = new JComboBox <String>(tenPhongBanList);
        luongComboBox = new JComboBox <String>(maluongList);

        String selectedMaPhongBan = parentGUI.chucvuTable.getValueAt(selectedRow, 2).toString();
        String selectedMaLuong = parentGUI.chucvuTable.getValueAt(selectedRow, 3).toString();

        phongbanComboBox.setSelectedItem(phongbanBLL.getTenPB_by_MaPB(selectedMaPhongBan));
        luongComboBox.setSelectedItem(selectedMaLuong);

        saveButton = new JButton("Lưu");
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maCVTextField.getText().trim().equals("") || tenCVHTTextField.getText().trim().equals("")) 
                    JOptionPane.showMessageDialog(editChucVuFrame.this,"Vui lòng nhập đủ thông tin");
                else{
                    chucvuDTO editingChucVu = new chucvuDTO();
                    editingChucVu.setMaCV(maCVTextField.getText());
                    editingChucVu.setTenCVHT(tenCVHTTextField.getText());
                    String tenPhongBanMoi = phongbanComboBox.getSelectedItem().toString();
                    editingChucVu.setPhongBan_MaPB(phongbanBLL.getMaPB_by_TenPB(tenPhongBanMoi));
                    editingChucVu.setLuong_MaLuong(luongComboBox.getSelectedItem().toString());

                    System.out.println(maCVTextField.getText());
                    System.out.println(tenCVHTTextField.getText());
                    System.out.println(phongbanComboBox.getSelectedItem().toString());
                    System.out.println(luongComboBox.getSelectedItem().toString());

                    //condition la ID của dữ liệu dùng để khi truy vấn câu lệnh sql tại WHERE = ...
                    String condition = parentGUI.chucvuTable.getValueAt(selectedRow, 0).toString();
                    String result = chucvuBLL.editChucVu(editingChucVu, condition);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadChucVuList();
                    editChucVuFrame.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editChucVuFrame.this.dispose();
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

        this.setSize(370,170);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
