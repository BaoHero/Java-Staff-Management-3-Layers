package GUI.hopdong;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.HopDongBLL;
import DTO.HopDongDTO;
import java.time.LocalDate;

public class hopdongGUI extends JPanel{
    HopDongBLL NhanVienBLL = new HopDongBLL();
    JTable nhanvienTable = new JTable();

    DefaultTableModel nhanvienDefaultTableModel;

    public hopdongGUI(){
        loadHopDongList();
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
                new addFrameHopDong(hopdongGUI.this);
            } 
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(hopdongGUI.this, "Hãy chọn dòng muốn xóa");
                else{
                    String deletingMaHD = nhanvienTable.getValueAt(nhanvienTable.getSelectedRow(), 0).toString();
                    String message = NhanVienBLL.delHopDong(deletingMaHD);
                    JOptionPane.showMessageDialog(hopdongGUI.this, message);
                    if (message.equals("Xóa thành công"))
                        nhanvienDefaultTableModel.removeRow(nhanvienTable.getSelectedRow());
                }
            }            
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(hopdongGUI.this, "Hãy chọn dòng muốn sửa");
                else{
                    new editFrameHopDong(hopdongGUI.this);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHopDongList();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã hợp đồng");
                nhanvienDefaultTableModel.addColumn("Nhân viên");
                nhanvienDefaultTableModel.addColumn("Loại hợp đồng");
                nhanvienDefaultTableModel.addColumn("Ngày bắt đầu");
                nhanvienDefaultTableModel.addColumn("Ngày kết thúc");
                nhanvienDefaultTableModel.addColumn("Ngày nghỉ việc");
               
                Vector<HopDongDTO> arr = new Vector<HopDongDTO>();
                arr = NhanVienBLL.getSearchHopDong(searchTextField.getText());
                for (int i=0; i<arr.size(); i++){
                    HopDongDTO nv = arr.get(i);
                    String mahd = nv.getMaHD();
                    String manv = nv.getNHANVIEN_MaNV();
                    String loai = nv.getLoaiHD();
                    LocalDate nbd = nv.getNgayBD();
                    LocalDate nkt = nv.getNgayKT();
                    LocalDate nnv = nv.getNgayNghiViec();
                    
                    Object[] row = {mahd , manv , loai , nbd , nkt , nnv };
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

    public void loadHopDongList(){
        nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã hợp đồng");
                nhanvienDefaultTableModel.addColumn("Nhân viên");
                nhanvienDefaultTableModel.addColumn("Loại hợp đồng");
                nhanvienDefaultTableModel.addColumn("Ngày bắt đầu");
                nhanvienDefaultTableModel.addColumn("Ngày kết thúc");
                nhanvienDefaultTableModel.addColumn("Ngày nghỉ việc");
       
        Vector<HopDongDTO> arr = new Vector<HopDongDTO>();
        arr = NhanVienBLL.getAllHopDong();
        for (int i=0; i<arr.size(); i++){
            HopDongDTO nv = arr.get(i);
                String mahd = nv.getMaHD();
                String manv = nv.getNHANVIEN_MaNV();
                String loai = nv.getLoaiHD();
                LocalDate nbd = nv.getNgayBD();
                LocalDate nkt = nv.getNgayKT();
                LocalDate nnv = nv.getNgayNghiViec();
                Object[] row = {mahd , manv , loai , nbd , nkt , nnv };
                nhanvienDefaultTableModel.addRow(row);
        }

        nhanvienTable.setModel(nhanvienDefaultTableModel);
    }


}

