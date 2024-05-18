package GUI.kiluat;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.kiLuatBLL;
import DTO.kiLuatDTO;
import java.time.LocalDate;

public class kiluatGUI extends JPanel{
    kiLuatBLL NhanVienBLL = new kiLuatBLL();
    JTable nhanvienTable = new JTable();

    DefaultTableModel nhanvienDefaultTableModel;

    public kiluatGUI(){
        loadKiLuatList();
        initComponents();
    }

    public void initComponents(){
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(nhanvienTable);

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
                new addFrameKiLuat(kiluatGUI.this);
            } 
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(kiluatGUI.this, "Hãy chọn dòng muốn xóa");
                else{
                    String deletingMaKL = nhanvienTable.getValueAt(nhanvienTable.getSelectedRow(), 0).toString();
                    String message = NhanVienBLL.delKiLuat(deletingMaKL);
                    JOptionPane.showMessageDialog(kiluatGUI.this, message);
                    if (message.equals("Xóa thành công"))
                        nhanvienDefaultTableModel.removeRow(nhanvienTable.getSelectedRow());
                }
            }            
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(kiluatGUI.this, "Hãy chọn dòng muốn sửa");
                else{
                    new editFrameKiLuat(kiluatGUI.this);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadKiLuatList();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã kĩ luật");
                nhanvienDefaultTableModel.addColumn("Lí do");
                nhanvienDefaultTableModel.addColumn("Ngày lập");
                
                Vector<kiLuatDTO> arr = new Vector<kiLuatDTO>();
                arr = NhanVienBLL.getSearchKiLuat(searchTextField.getText());
                for (int i=0; i<arr.size(); i++){
                    kiLuatDTO nv = arr.get(i);
                    String makt = nv.getMaKL();
                    String lido = nv.getLiDo();
                    LocalDate nkl = nv.getNgayKL();
                    
                    Object[] row = {makt , lido , nkl };
                    nhanvienDefaultTableModel.addRow(row);
                }
        
                nhanvienTable.setModel(nhanvienDefaultTableModel);
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

    public void loadKiLuatList(){
        nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã khen thưởng");
                nhanvienDefaultTableModel.addColumn("Lí do");
                nhanvienDefaultTableModel.addColumn("Ngày lập");
                
        Vector<kiLuatDTO> arr = new Vector<kiLuatDTO>();
        arr = NhanVienBLL.getViPham();
        for (int i=0; i<arr.size(); i++){
            kiLuatDTO nv = arr.get(i);
                    String makt = nv.getMaKL();
                    String lido = nv.getLiDo();
                    LocalDate nkl = nv.getNgayKL();
                    Object[] row = {makt , lido , nkl };
                nhanvienDefaultTableModel.addRow(row);
        }

        nhanvienTable.setModel(nhanvienDefaultTableModel);
    }


}

