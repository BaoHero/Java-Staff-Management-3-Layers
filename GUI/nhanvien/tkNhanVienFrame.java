package GUI.nhanvien;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;

public class tkNhanVienFrame extends JFrame{
    private nhanvienGUI parentGUI;
    NhanVienBLL NhanVienBLL = new NhanVienBLL();
    JTable nhanvienTable = new JTable();

    DefaultTableModel nhanvienDefaultTableModel;

    public tkNhanVienFrame(nhanvienGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thống kê nhân viên đang làm hoặc đã nghỉ");
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(nhanvienTable);

        JButton refreshButton = new JButton("Toàn bộ");
        JButton searchButton = new JButton("Lọc");
        
        
        JComboBox<String> searchtk = new JComboBox<>();
        searchtk.addItem("Đang làm");
        searchtk.addItem("Đã nghỉ việc");

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
                nhanvienDefaultTableModel.addColumn("Họ Tên");
                nhanvienDefaultTableModel.addColumn("Trạng thái");
                
                Vector<NhanVienDTO> arr = new Vector<NhanVienDTO>();
                arr = NhanVienBLL.getSearchTk(searchtk.getSelectedItem().toString());
                for (int i=0; i<arr.size(); i++){
                    NhanVienDTO nv = arr.get(i);
                    String hoten = nv.getHoTen();
                    String trangthai = nv.getTrangThai();
                    Object[] row = {hoten , trangthai};
                    nhanvienDefaultTableModel.addRow(row);
                }
        
                nhanvienTable.setModel(nhanvienDefaultTableModel);
            }
        });


        JPanel northPanel = new JPanel();
        northPanel.add(refreshButton);
        northPanel.add(searchButton);
        northPanel.add(searchtk);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        
        this.setSize(500,700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        setLocationRelativeTo(null);
        
    }

    public void loadNhanVienList(){
        nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Họ Tên");
                nhanvienDefaultTableModel.addColumn("Trạng thái");
       
        Vector<NhanVienDTO> arr = new Vector<NhanVienDTO>();
        arr = NhanVienBLL.ThongKeNhanVien();
        for (int i=0; i<arr.size(); i++){
            NhanVienDTO nv = arr.get(i);
                    String hoten = nv.getHoTen();
                    String trangthai = nv.getTrangThai();
                    Object[] row = { hoten , trangthai };
                    nhanvienDefaultTableModel.addRow(row);
        }

        nhanvienTable.setModel(nhanvienDefaultTableModel);
    }


}

