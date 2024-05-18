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

public class editFrameKhenThuong extends JFrame {
    private khenThuongGUI parentGUI;
    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    JTextField maktTextField, lidoTextField , NgayKTTextField ;
    JButton saveButton, cancelButton;

    khenThuongBLL NhanVienBLL = new khenThuongBLL();
    
    public editFrameKhenThuong(khenThuongGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Chỉnh sửa thông tin khen thưởng"); 

        int selectedRow = parentGUI.nhanvienTable.getSelectedRow();

        maktTextField = new JTextField(20);
        lidoTextField = new JTextField(20);
        NgayKTTextField = new JTextField(20);
        
        maktTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString());
        lidoTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 1).toString());
        NgayKTTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 2).toString());
        

        saveButton = new JButton("Lưu");
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maktTextField.getText().trim().equals("") || lidoTextField.getText().trim().equals("") || NgayKTTextField.getText().trim().equals("") )  
                    JOptionPane.showMessageDialog(editFrameKhenThuong.this,"Vui lòng nhập đủ thông tin");
                else{
                    khenThuongDTO nv = new khenThuongDTO();
                    nv.setMaKT(maktTextField.getText());
                    nv.setLiDo(lidoTextField.getText());
                    nv.setNgayKT(LocalDate.parse(NgayKTTextField.getText().trim(),Formatter));
                    
                    //condition la ID của dữ liệu dùng để khi truy vấn câu lệnh sql tại WHERE = ...
                    String condition = parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString();
                    String result = NhanVienBLL.editKhenThuong(nv, condition);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadKhenThuongList();
                    editFrameKhenThuong.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFrameKhenThuong.this.dispose();
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
