package GUI.bangchamcong;

import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.bangChamCongBUS;
import DTO.bangChamCongDTO;

public class TkFrameBCC extends JFrame{
    private bangchamcongGUI parentGUI;
    bangChamCongBUS bangChamCongBUS = new bangChamCongBUS();
    JTable nhanvienTable = new JTable();

    DefaultTableModel nhanvienDefaultTableModel;

    public TkFrameBCC(bangchamcongGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Thống kê số ngày nghỉ nhân viên");
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(nhanvienTable);

        JButton refreshButton = new JButton("Show");
        
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadThongKeList();
            }
        });



        JPanel northPanel = new JPanel();
        northPanel.add(refreshButton);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        
        this.setSize(500,700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        setLocationRelativeTo(null);
        
    }

    public void loadThongKeList(){
        nhanvienDefaultTableModel = new DefaultTableModel();
                nhanvienDefaultTableModel.addColumn("Mã Nhân Viên");
                nhanvienDefaultTableModel.addColumn("Tổng số ngày nghỉ");
       
        Vector<bangChamCongDTO> arr = new Vector<bangChamCongDTO>();
        arr = bangChamCongBUS.ThongKeBCC();
        for (int i=0; i<arr.size(); i++){
            bangChamCongDTO nv = arr.get(i);
                    String manv = nv.getNHANVIEN_MaNV();
                    int snn = nv.getSoNgayNghi();
                    Object[] row = { manv , snn };
                    nhanvienDefaultTableModel.addRow(row);
        }

        nhanvienTable.setModel(nhanvienDefaultTableModel);
    }


}


