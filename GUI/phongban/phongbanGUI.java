package GUI.phongban;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.phongbanBLL;
import DTO.phongbanDTO;

public class phongbanGUI extends JPanel{
    phongbanBLL phongbanBLL = new phongbanBLL();
    JTable phongbanTable = new JTable();
    DefaultTableModel phongbanDefaultTableModel;
    public phongbanGUI(){
        loadPhongBanList();
        initComponents();
    }

    public void initComponents(){
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(phongbanTable);

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
                new addPhongBanFrame(phongbanGUI.this);
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (phongbanTable.getSelectedRow() == -1)
                    JOptionPane.showMessageDialog(phongbanGUI.this, "Hãy chọn dòng muốn xóa");
                else{
                    String deletingMaPB = phongbanTable.getValueAt(phongbanTable.getSelectedRow(), 0).toString();
                    String message = phongbanBLL.delPhongBan(deletingMaPB);
                    JOptionPane.showMessageDialog(phongbanGUI.this, message);
                    if (message.equals("Xóa thành công"))
                        phongbanDefaultTableModel.removeRow(phongbanTable.getSelectedRow());
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if (phongbanTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(phongbanGUI.this, "Hãy chọn dòng muốn sửa");
            else 
                new editPhongBanFrame(phongbanGUI.this);
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPhongBanList();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phongbanDefaultTableModel = new DefaultTableModel();
                phongbanDefaultTableModel.addColumn("Mã phòng ban");
                phongbanDefaultTableModel.addColumn("Tên phòng ban");
               
                Vector<phongbanDTO> arr = new Vector<phongbanDTO>();
                arr = phongbanBLL.getSearchPhongBan(searchTextField.getText());
                for (int i=0; i<arr.size(); i++){
                    phongbanDTO phongban = arr.get(i);
                    String maPB = phongban.getMaPB();
                    String tenPB = phongban.getTenPB();
                    Object[] row = {maPB,tenPB};
                    phongbanDefaultTableModel.addRow(row);
                }
        
                phongbanTable.setModel(phongbanDefaultTableModel);
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

    public void loadPhongBanList(){
        phongbanDefaultTableModel = new DefaultTableModel();
        phongbanDefaultTableModel.addColumn("Mã phòng ban");
        phongbanDefaultTableModel.addColumn("Tên phòng ban");
       
        Vector<phongbanDTO> arr = new Vector<phongbanDTO>();
        arr = phongbanBLL.getAllPhongBan();
        for (int i=0; i<arr.size(); i++){
            phongbanDTO phongban = arr.get(i);
            String maPB = phongban.getMaPB();
            String tenPB = phongban.getTenPB();
            Object[] row = {maPB,tenPB};
            phongbanDefaultTableModel.addRow(row);
        }

        phongbanTable.setModel(phongbanDefaultTableModel);
    }

}
