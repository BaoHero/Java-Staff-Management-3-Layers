package GUI.trinhdo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BLL.*;
import DTO.TrinhDoDTO;


public class addFrameTrinhDo extends JFrame {
    private trinhdoGUI parentGUI;

    JTextField matdTextField, tentdTextField , cnTextField ;
    JButton saveButton, cancelButton;

    TrinhDoBLL NhanVienBLL = new TrinhDoBLL();

    public addFrameTrinhDo(trinhdoGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thêm mục trình độ học vấn"); 
        matdTextField = new JTextField(20);
        tentdTextField = new JTextField(20);
        cnTextField = new JTextField(20);
        
        
        saveButton = new JButton("Lưu"); 
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (matdTextField.getText().trim().equals("") || tentdTextField.getText().trim().equals("") || cnTextField.getText().trim().equals("") ) 
                    JOptionPane.showMessageDialog(addFrameTrinhDo.this,"Vui lòng nhập đủ thông tin");
                else{
                    
                    TrinhDoDTO nv = new TrinhDoDTO();
                    nv.setMaTDHV(matdTextField.getText());
                    nv.setTenTDHV(tentdTextField.getText());
                    nv.setChuyenNganh(cnTextField.getText());
                  
                    String result = NhanVienBLL.addTrinhDo(nv);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadTrinhDoList();
                    addFrameTrinhDo.this.dispose();
                }
            }            
        });

        cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFrameTrinhDo.this.dispose();
            }            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Mã trình độ học vấn : "));
        inputPanel.add(matdTextField);
        inputPanel.add(new JLabel("Tên trình độ : "));
        inputPanel.add(tentdTextField);
        inputPanel.add(new JLabel("Chuyên ngành : "));
        inputPanel.add(cnTextField);

        this.setSize(400,200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
