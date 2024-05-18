package GUI.bangchamcong;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.bangChamCongBUS;
import DTO.bangChamCongDTO;
import java.time.LocalDateTime;

public class bangchamcongGUI extends JPanel{
    bangChamCongBUS NhanVienBLL = new bangChamCongBUS();
    JTable nhanvienTable = new JTable();

    DefaultTableModel nhanvienDefaultTableModel;

    public bangchamcongGUI(){
        loadChamCongList();
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
        JButton tkbutton = new JButton("Thống kê");
        

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addFrameBCC(bangchamcongGUI.this);
            } 
        });
        
        tkbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TkFrameBCC(bangchamcongGUI.this);
            } 
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(bangchamcongGUI.this, "Hãy chọn dòng muốn xóa");
                else{
                    String deletingMaBCC = nhanvienTable.getValueAt(nhanvienTable.getSelectedRow(), 0).toString();
                    String message = NhanVienBLL.delChamCong(deletingMaBCC);
                    JOptionPane.showMessageDialog(bangchamcongGUI.this, message);
                    if (message.equals("Xóa thành công"))
                        nhanvienDefaultTableModel.removeRow(nhanvienTable.getSelectedRow());
                }
            }            
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(bangchamcongGUI.this, "Hãy chọn dòng muốn sửa");
                else{
                    new editFrameBCC(bangchamcongGUI.this);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadChamCongList();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã bảng chấm công");
                nhanvienDefaultTableModel.addColumn("Mã nhân viên");
                nhanvienDefaultTableModel.addColumn("Mã chi tiết bảng");
                
                Vector<bangChamCongDTO> arr = new Vector<bangChamCongDTO>();
                arr = NhanVienBLL.getSearchBangChamCong(searchTextField.getText());
                for (int i=0; i<arr.size(); i++){
                    bangChamCongDTO nv = arr.get(i);
                    String mabcc = nv.getMaBangChamCong();
                    String manv = nv.getNHANVIEN_MaNV();
                    String mact = nv.getChiTietBangChamCong_maBangChamCong();
                    
                    Object[] row = {mabcc , manv , mact };
                    nhanvienDefaultTableModel.addRow(row);
                }
        
                nhanvienTable.setModel(nhanvienDefaultTableModel);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton); 
        buttonPanel.add(delButton); 
        buttonPanel.add(editButton);
        buttonPanel.add(tkbutton);

        JPanel northPanel = new JPanel();
        northPanel.add(refreshButton);
        northPanel.add(searchButton);
        northPanel.add(searchTextField);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void loadChamCongList(){
        nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã bảng chấm công");
                nhanvienDefaultTableModel.addColumn("Mã nhân viên");
                nhanvienDefaultTableModel.addColumn("Mã chi tiết");
                
        Vector<bangChamCongDTO> arr = new Vector<bangChamCongDTO>();
        arr = NhanVienBLL.getAllChamCong();
        for (int i=0; i<arr.size(); i++){
            bangChamCongDTO nv = arr.get(i);
                String mabcc = nv.getMaBangChamCong();
                String manv = nv.getNHANVIEN_MaNV();
                String mact = nv.getChiTietBangChamCong_maBangChamCong();
                
                Object[] row = {mabcc , manv , mact };
                nhanvienDefaultTableModel.addRow(row);
        }

        nhanvienTable.setModel(nhanvienDefaultTableModel);
    }


}

