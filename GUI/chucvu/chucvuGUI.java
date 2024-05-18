package GUI.chucvu;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.chucvuBLL;
import DTO.chucvuDTO;

public class chucvuGUI extends JPanel{
    chucvuBLL chucvuBLL = new chucvuBLL();
    JTable chucvuTable = new JTable();

    DefaultTableModel chucvuDefaultTableModel;

    public chucvuGUI(){
        loadChucVuList();
        initComponents();
    }

    public void initComponents(){
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(chucvuTable);

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
                new addChucVuFrame(chucvuGUI.this);
            } 
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chucvuTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(chucvuGUI.this, "Hãy chọn dòng muốn xóa");
                else{
                    String deletingMaCV = chucvuTable.getValueAt(chucvuTable.getSelectedRow(), 0).toString();
                    String message = chucvuBLL.delChucVu(deletingMaCV);
                    JOptionPane.showMessageDialog(chucvuGUI.this, message);
                    if (message.equals("Xóa thành công"))
                        chucvuDefaultTableModel.removeRow(chucvuTable.getSelectedRow());
                }
            }            
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chucvuTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(chucvuGUI.this, "Hãy chọn dòng muốn sửa");
                else{
                    new editChucVuFrame(chucvuGUI.this);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadChucVuList();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chucvuDefaultTableModel = new DefaultTableModel();
                chucvuDefaultTableModel.addColumn("Mã chức vụ");
                chucvuDefaultTableModel.addColumn("Tên chức vụ");
                chucvuDefaultTableModel.addColumn("Mã phòng ban");
                chucvuDefaultTableModel.addColumn("Mã lương");
               
                Vector<chucvuDTO> arr = new Vector<chucvuDTO>();
                arr = chucvuBLL.getSearchChucVu(searchTextField.getText());
                for (int i=0; i<arr.size(); i++){
                    chucvuDTO chucvu = arr.get(i);
                    String maCV = chucvu.getMaCV();
                    String tenCVHT = chucvu.getTenCVHT();
                    String phongBan_MaPB = chucvu.getPhongBan_MaPB();
                    String luong_MaLuong = chucvu.getLuong_MaLuong();
                    Object[] row = {maCV,tenCVHT,phongBan_MaPB,luong_MaLuong};
                    chucvuDefaultTableModel.addRow(row);
                }
        
                chucvuTable.setModel(chucvuDefaultTableModel);
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

    public void loadChucVuList(){
        chucvuDefaultTableModel = new DefaultTableModel();
        chucvuDefaultTableModel.addColumn("Mã chức vụ");
        chucvuDefaultTableModel.addColumn("Tên chức vụ");
        chucvuDefaultTableModel.addColumn("Mã phòng ban");
        chucvuDefaultTableModel.addColumn("Mã lương");
       
        Vector<chucvuDTO> arr = new Vector<chucvuDTO>();
        arr = chucvuBLL.getAllChucVu();
        for (int i=0; i<arr.size(); i++){
            chucvuDTO chucvu = arr.get(i);
            String maCV = chucvu.getMaCV();
            String tenCVHT = chucvu.getTenCVHT();
            String phongBan_MaPB = chucvu.getPhongBan_MaPB();
            String luong_MaLuong = chucvu.getLuong_MaLuong();
            Object[] row = {maCV,tenCVHT,phongBan_MaPB,luong_MaLuong};
            chucvuDefaultTableModel.addRow(row);
        }

        chucvuTable.setModel(chucvuDefaultTableModel);
    }


}
