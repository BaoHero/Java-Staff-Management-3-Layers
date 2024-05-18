package GUI.luong;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.luongBLL;
import DTO.luongDTO;
import DTO.phongbanDTO;

public class luongGUI extends JPanel{
    luongBLL luongBLL = new luongBLL();
    JTable luongTable = new JTable();
    DefaultTableModel luongDefaultTableModel;

    public luongGUI(){
        loadLuongList();
        initComponents();
    }

    public void initComponents(){
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(luongTable);
        JButton addButton = new JButton("Thêm"); 
        addButton.setBackground(Color.green);
        addButton.setForeground(Color.white);
        JButton delButton = new JButton("Xóa");
        delButton.setBackground(Color.blue);
        delButton.setForeground(Color.white);
        JButton editButton = new JButton("Sửa");
        editButton.setBackground(Color.black);
        editButton.setForeground(Color.white);
        JButton refreshButton = new JButton("refresh");
        refreshButton.setBackground(Color.blue);
        refreshButton.setForeground(Color.white);
        JButton searchButton = new JButton("Tìm kiếm");
        JTextField searchTextField = new JTextField(20);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addLuongFrame(luongGUI.this);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if (luongTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(luongGUI.this, "Hãy chọn dòng muốn sửa");
            else 
                new editLuongFrame(luongGUI.this);
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (luongTable.getSelectedRow() == -1)
                    JOptionPane.showMessageDialog(luongGUI.this, "Hãy chọn dòng muốn xóa");
                else{
                    String deletingMaLuong = luongTable.getValueAt(luongTable.getSelectedRow(), 0).toString();
                    String message = luongBLL.delLuong(deletingMaLuong);
                    JOptionPane.showMessageDialog(luongGUI.this, message);
                    if (message.equals("Xóa thành công"))
                        luongDefaultTableModel.removeRow(luongTable.getSelectedRow());
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadLuongList();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luongDefaultTableModel = new DefaultTableModel();
                luongDefaultTableModel.addColumn("Mã lương");
                luongDefaultTableModel.addColumn("Tiền lương");
               
                Vector<luongDTO> arr = new Vector<luongDTO>();
                arr = luongBLL.getSearchLuong(searchTextField.getText());
                for (int i=0; i<arr.size(); i++){
                    luongDTO luong = arr.get(i);
                    String maLuong = luong.getMaLuong();
                    String tienLuong = luong.getTienLuong();
                    Object[] row = {maLuong,tienLuong};
                    luongDefaultTableModel.addRow(row);
                }
        
                luongTable.setModel(luongDefaultTableModel);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton); 
        buttonPanel.add(delButton); 
        buttonPanel.add(editButton);

        JPanel northPanel = new JPanel();
        northPanel.add(refreshButton);
        northPanel.add(searchButton);
        northPanel.add(searchTextField);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void loadLuongList(){
        luongDefaultTableModel = new DefaultTableModel();
        luongDefaultTableModel.addColumn("Mã lương");
        luongDefaultTableModel.addColumn("Tiền lương");
       
        Vector<luongDTO> arr = new Vector<luongDTO>();
        arr = luongBLL.getAllLuong();
        for (int i=0; i<arr.size(); i++){
            luongDTO luong = arr.get(i);
            String maLuong = luong.getMaLuong();
            String tienLuong = luong.getTienLuong();
            Object[] row = {maLuong,tienLuong};
            luongDefaultTableModel.addRow(row);
        }

        luongTable.setModel(luongDefaultTableModel);
    }
}
