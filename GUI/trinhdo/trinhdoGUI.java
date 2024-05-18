package GUI.trinhdo;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.TrinhDoBLL;
import DTO.TrinhDoDTO;
import java.time.LocalDateTime;

public class trinhdoGUI extends JPanel{
    TrinhDoBLL NhanVienBLL = new TrinhDoBLL();
    JTable nhanvienTable = new JTable();

    DefaultTableModel nhanvienDefaultTableModel;

    public trinhdoGUI(){
        loadTrinhDoList();
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
                new addFrameTrinhDo(trinhdoGUI.this);
            } 
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(trinhdoGUI.this, "Hãy chọn dòng muốn xóa");
                else{
                    String deletingMaTD = nhanvienTable.getValueAt(nhanvienTable.getSelectedRow(), 0).toString();
                    String message = NhanVienBLL.delTrinhDo(deletingMaTD);
                    JOptionPane.showMessageDialog(trinhdoGUI.this, message);
                    if (message.equals("Xóa thành công"))
                        nhanvienDefaultTableModel.removeRow(nhanvienTable.getSelectedRow());
                }
            }            
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(trinhdoGUI.this, "Hãy chọn dòng muốn sửa");
                else{
                    new editFrameTrinhDo(trinhdoGUI.this);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTrinhDoList();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã trình độ học vấn");
                nhanvienDefaultTableModel.addColumn("Tên trình độ");
                nhanvienDefaultTableModel.addColumn("Chuyên ngành");
               
                Vector<TrinhDoDTO> arr = new Vector<TrinhDoDTO>();
                arr = NhanVienBLL.getSearchTrinhDo(searchTextField.getText());
                for (int i=0; i<arr.size(); i++){
                    TrinhDoDTO nv = arr.get(i);
                    String matd = nv.getMaTDHV();
                    String tentd = nv.getTenTDHV();
                    String cn = nv.getChuyenNganh();                   
                    Object[] row = {matd , tentd , cn };
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

    public void loadTrinhDoList(){
        nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã trình độ học vấn");
                nhanvienDefaultTableModel.addColumn("Tên trình độ");
                nhanvienDefaultTableModel.addColumn("Chuyên ngành");
                
        Vector<TrinhDoDTO> arr = new Vector<TrinhDoDTO>();
        arr = NhanVienBLL.getAllTrinhDo();
        for (int i=0; i<arr.size(); i++){
            TrinhDoDTO nv = arr.get(i);
                String matd = nv.getMaTDHV();
                String tentd = nv.getTenTDHV();
                String cn = nv.getChuyenNganh();                   
                Object[] row = {matd , tentd , cn };
                nhanvienDefaultTableModel.addRow(row);
        }

        nhanvienTable.setModel(nhanvienDefaultTableModel);
    }


}

