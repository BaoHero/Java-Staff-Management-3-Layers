package GUI.chitietbcc;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.chiTietBangChamCongBUS;
import DTO.chiTietBangChamCongDTO;
import java.time.LocalDate;

public class chitietbccGUI extends JPanel{
    chiTietBangChamCongBUS ChiTietBLL = new chiTietBangChamCongBUS();
    JTable nhanvienTable = new JTable();

    DefaultTableModel nhanvienDefaultTableModel;

    public chitietbccGUI(){
        loadChiTietList();
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
                new addFrameChiTiet(chitietbccGUI.this);
            } 
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(chitietbccGUI.this, "Hãy chọn dòng muốn xóa");
                else{
                    String deletingMaCT = nhanvienTable.getValueAt(nhanvienTable.getSelectedRow(), 0).toString();
                    String message = ChiTietBLL.delChiTiet(deletingMaCT);
                    JOptionPane.showMessageDialog(chitietbccGUI.this, message);
                    if (message.equals("Xóa thành công"))
                        nhanvienDefaultTableModel.removeRow(nhanvienTable.getSelectedRow());
                }
            }            
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(chitietbccGUI.this, "Hãy chọn dòng muốn sửa");
                else{
                    new editFrameChiTiet(chitietbccGUI.this);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadChiTietList();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã chi tiết");
                nhanvienDefaultTableModel.addColumn("Ngày lập");
                nhanvienDefaultTableModel.addColumn("Số ngày nghỉ");
                nhanvienDefaultTableModel.addColumn("Mã khen thưởng");
                nhanvienDefaultTableModel.addColumn("Mã kỉ luật");
               
                Vector<chiTietBangChamCongDTO> arr = new Vector<chiTietBangChamCongDTO>();
                arr = ChiTietBLL.getSearchChiTiet(searchTextField.getText());
                for (int i=0; i<arr.size(); i++){
                    chiTietBangChamCongDTO nv = arr.get(i);
                    String mact = nv.getmaChiTietBangChamCong();
                    LocalDate nl = nv.getNgayLap();
                    int snn = nv.getsoNgayNghi();
                    String makt = nv.getKhenThuong_MaKT();
                    String makl = nv.getKiLuat_MaKL();
                    
                    Object[] row = {mact , nl , snn , makt , makl };
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

    public void loadChiTietList(){
        nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã chi tiết");
                nhanvienDefaultTableModel.addColumn("Ngày lập");
                nhanvienDefaultTableModel.addColumn("Số ngày nghỉ");
                nhanvienDefaultTableModel.addColumn("Mã khen thưởng");
                nhanvienDefaultTableModel.addColumn("Mã kỉ luật");
                
        Vector<chiTietBangChamCongDTO> arr = new Vector<chiTietBangChamCongDTO>();
                arr = ChiTietBLL.getallChiTietBangChamCong();
                for (int i=0; i<arr.size(); i++){
                    chiTietBangChamCongDTO nv = arr.get(i);
                    String mact = nv.getmaChiTietBangChamCong();
                    LocalDate nl = nv.getNgayLap();
                    int snn = nv.getsoNgayNghi();
                    String makt = nv.getKhenThuong_MaKT();
                    String makl = nv.getKiLuat_MaKL();
                    
                    Object[] row = {mact , nl , snn , makt , makl };
                    nhanvienDefaultTableModel.addRow(row);
                }

        nhanvienTable.setModel(nhanvienDefaultTableModel);
    }


}

