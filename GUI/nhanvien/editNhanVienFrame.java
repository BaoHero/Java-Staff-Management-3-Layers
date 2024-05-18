package GUI.nhanvien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import BLL.*;
import DTO.NhanVienDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class editNhanVienFrame extends JFrame {
    private nhanvienGUI parentGUI;
    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    JTextField maNVTextField, HoTenTextField , NgaySinhTextField ,SoDienThoaiTextField , NgayNhanCVTextField ;
    JComboBox gtComboBox, ttComboBox , MaCVCombobox , MaTDComboBox ;
    JButton saveButton, cancelButton;

    NhanVienBLL NhanVienBLL = new NhanVienBLL();
    chucvuBLL chucvuBLL = new chucvuBLL();
    TrinhDoBLL TrinhDoBLL = new TrinhDoBLL();

    public editNhanVienFrame(nhanvienGUI parentGUI){
        this.parentGUI = parentGUI;
        initComponents();
    }

    public void initComponents(){
        this.setTitle("Chỉnh sửa chức vụ"); 

        int selectedRow = parentGUI.nhanvienTable.getSelectedRow();

        maNVTextField = new JTextField(20);
        HoTenTextField = new JTextField(20);
        NgaySinhTextField = new JTextField(20);
        SoDienThoaiTextField = new JTextField(20);
        NgayNhanCVTextField = new JTextField(20); 

        maNVTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString());
        HoTenTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 1).toString());
        NgaySinhTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 3).toString());
        SoDienThoaiTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 4).toString());
        NgayNhanCVTextField.setText(parentGUI.nhanvienTable.getValueAt(selectedRow, 7).toString());
       
        JComboBox<String> gtComboBox = new JComboBox<>();
        gtComboBox.addItem("Nam");
        gtComboBox.addItem("Nữ");
        JComboBox<String> ttComboBox = new JComboBox<>();
        ttComboBox.addItem("Đang làm");
        ttComboBox.addItem("Đã nghỉ việc");
        
        Vector <String> MaCVList = new Vector<String>();
        Vector <String> TDHVList = new Vector<String>();
        MaCVList = chucvuBLL.getMaCVList();
        TDHVList = TrinhDoBLL.getTrinhDoList();

        MaCVCombobox = new JComboBox <>(MaCVList);
        MaTDComboBox = new JComboBox <>(TDHVList);

        String selectedgt = parentGUI.nhanvienTable.getValueAt(selectedRow, 2).toString();
        String selectedtt = parentGUI.nhanvienTable.getValueAt(selectedRow, 5).toString();
        String selectedcv = parentGUI.nhanvienTable.getValueAt(selectedRow, 6).toString();
        String selectedtd = parentGUI.nhanvienTable.getValueAt(selectedRow, 8).toString();

        gtComboBox.setSelectedItem(selectedgt);
        ttComboBox.setSelectedItem(selectedtt);
        MaCVCombobox.setSelectedItem(selectedcv);
        MaTDComboBox.setSelectedItem(selectedtd);

        saveButton = new JButton("Lưu");
        cancelButton = new JButton("Hủy");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maNVTextField.getText().trim().equals("") || HoTenTextField.getText().trim().equals("") || NgaySinhTextField.getText().trim().equals("") || SoDienThoaiTextField.getText().trim().equals("") || NgayNhanCVTextField.getText().trim().equals(""))  
                    JOptionPane.showMessageDialog(editNhanVienFrame.this,"Vui lòng nhập đủ thông tin");
                else{
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setMaNV(maNVTextField.getText());
                    nv.setHoTen(HoTenTextField.getText());
                    nv.setGioiTinh(gtComboBox.getSelectedItem().toString());
                    nv.setNgaySinh(LocalDate.parse(NgaySinhTextField.getText().trim(),Formatter));
                    nv.setSoDienThoai(SoDienThoaiTextField.getText());
                    nv.setTrangThai(ttComboBox.getSelectedItem().toString());
                    nv.setChucVu_MaCV(MaCVCombobox.getSelectedItem().toString());
                    nv.setNgayNhanCV(LocalDate.parse(NgayNhanCVTextField.getText().trim(),Formatter));
                    nv.setTrinhDoHocVan_MaTDHV(MaTDComboBox.getSelectedItem().toString());
                    //condition la ID của dữ liệu dùng để khi truy vấn câu lệnh sql tại WHERE = ...
                    String condition = parentGUI.nhanvienTable.getValueAt(selectedRow, 0).toString();
                    String result = NhanVienBLL.editNhanVien(nv, condition);
                    JOptionPane.showMessageDialog(parentGUI, result);
                    parentGUI.loadNhanVienList();
                    editNhanVienFrame.this.dispose();
                }
            }            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editNhanVienFrame.this.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);  
        buttonPanel.add(cancelButton);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Mã nhân viên : "));
        inputPanel.add(maNVTextField);
        inputPanel.add(new JLabel("Họ tên : "));
        inputPanel.add(HoTenTextField);
        inputPanel.add(new JLabel("Giới tính : "));
        inputPanel.add(gtComboBox);
        inputPanel.add(new JLabel("Ngày sinh : "));
        inputPanel.add(NgaySinhTextField);
        inputPanel.add(new JLabel("Số điện thoại: "));
        inputPanel.add(SoDienThoaiTextField);
        inputPanel.add(new JLabel("Trạng thái : "));
        inputPanel.add(ttComboBox);
        inputPanel.add(new JLabel("Chức vụ : "));
        inputPanel.add(MaCVCombobox);
        inputPanel.add(new JLabel("Ngày nhận chức: "));
        inputPanel.add(NgayNhanCVTextField);
        inputPanel.add(new JLabel("Trình độ : "));
        inputPanel.add(MaTDComboBox);

        this.setSize(765,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
