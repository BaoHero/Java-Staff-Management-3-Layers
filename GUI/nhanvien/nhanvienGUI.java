package GUI.nhanvien;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import java.time.LocalDate;

public class nhanvienGUI extends JPanel{
    NhanVienBLL NhanVienBLL = new NhanVienBLL();
    JTable nhanvienTable = new JTable();

    DefaultTableModel nhanvienDefaultTableModel;

    public nhanvienGUI(){
        loadNhanVienList();
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
        JButton tkButton = new JButton("Thống kê");
        

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addNhanVienFrame(nhanvienGUI.this);
            } 
        });
        
        tkButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new tkNhanVienFrame(nhanvienGUI.this);
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(nhanvienGUI.this, "Hãy chọn dòng muốn xóa");
                else{
                    String deletingMaNV = nhanvienTable.getValueAt(nhanvienTable.getSelectedRow(), 0).toString();
                    String message = NhanVienBLL.delNhanVien(deletingMaNV);
                    JOptionPane.showMessageDialog(nhanvienGUI.this, message);
                    if (message.equals("Xóa thành công"))
                        nhanvienDefaultTableModel.removeRow(nhanvienTable.getSelectedRow());
                }
            }            
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nhanvienTable.getSelectedRow() == -1)
                JOptionPane.showMessageDialog(nhanvienGUI.this, "Hãy chọn dòng muốn sửa");
                else{
                    new editNhanVienFrame(nhanvienGUI.this);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadNhanVienList();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã nhân viên");
                nhanvienDefaultTableModel.addColumn("Họ Tên");
                nhanvienDefaultTableModel.addColumn("Giới tính");
                nhanvienDefaultTableModel.addColumn("Ngày Sinh");
                nhanvienDefaultTableModel.addColumn("Số điện thoại");
                nhanvienDefaultTableModel.addColumn("Trạng thái");
                nhanvienDefaultTableModel.addColumn("Mã chức vụ");
                nhanvienDefaultTableModel.addColumn("Ngày nhận công việc");
                nhanvienDefaultTableModel.addColumn("Mã trình độ");
               
                Vector<NhanVienDTO> arr = new Vector<NhanVienDTO>();
                arr = NhanVienBLL.getSearchNhanVien(searchTextField.getText());
                for (int i=0; i<arr.size(); i++){
                    NhanVienDTO nv = arr.get(i);
                    String manv = nv.getMaNV();
                    String hoten = nv.getHoTen();
                    String gt = nv.getGioiTinh();
                    LocalDate ns = nv.getNgaySinh();
                    String sdt = nv.getSoDienThoai();
                    String trangthai = nv.getTrangThai();
                    String cv = nv.getChucVu_MaCV();
                    LocalDate nn = nv.getNgayNhanCV();
                    String tdhv = nv.getTrinhDoHocVan_MaTDHV();
                    Object[] row = {manv , hoten , gt , ns , sdt , trangthai , cv , nn , tdhv};
                    nhanvienDefaultTableModel.addRow(row);
                }
        
                nhanvienTable.setModel(nhanvienDefaultTableModel);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton); 
        buttonPanel.add(delButton); 
        buttonPanel.add(editButton);
        buttonPanel.add(tkButton);

        JPanel northPanel = new JPanel();
        northPanel.add(refreshButton);
        northPanel.add(searchButton);
        northPanel.add(searchTextField);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void loadNhanVienList(){
        nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã nhân viên");
                nhanvienDefaultTableModel.addColumn("Họ Tên");
                nhanvienDefaultTableModel.addColumn("Giới tính");
                nhanvienDefaultTableModel.addColumn("Ngày Sinh");
                nhanvienDefaultTableModel.addColumn("Số điện thoại");
                nhanvienDefaultTableModel.addColumn("Trạng thái");
                nhanvienDefaultTableModel.addColumn("Mã chức vụ");
                nhanvienDefaultTableModel.addColumn("Ngày nhận công việc");
                nhanvienDefaultTableModel.addColumn("Mã trình độ");
       
        Vector<NhanVienDTO> arr = new Vector<NhanVienDTO>();
        arr = NhanVienBLL.getAllNhanVien();
        for (int i=0; i<arr.size(); i++){
            NhanVienDTO nv = arr.get(i);
                    String manv = nv.getMaNV();
                    String hoten = nv.getHoTen();
                    String gt = nv.getGioiTinh();
                    LocalDate ns = nv.getNgaySinh();
                    String sdt = nv.getSoDienThoai();
                    String trangthai = nv.getTrangThai();
                    String cv = nv.getChucVu_MaCV();
                    LocalDate nn = nv.getNgayNhanCV();
                    String tdhv = nv.getTrinhDoHocVan_MaTDHV();
                    Object[] row = {manv , hoten , gt , ns , sdt , trangthai , cv , nn , tdhv};
                    nhanvienDefaultTableModel.addRow(row);
        }

        nhanvienTable.setModel(nhanvienDefaultTableModel);
    }


}

