package GUI;

import javax.swing.*;

import GUI.chucvu.chucvuGUI;
import GUI.luong.luongGUI;
import GUI.phongban.phongbanGUI;

import java.awt.*;
import java.awt.event.*;

public class MainFrame_Cv_Pb_L extends JFrame {
    
    public MainFrame_Cv_Pb_L(){
        this.setTitle("Quản lý nhân sự");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(900,600);
        setLocationRelativeTo(null);

        JLabel headingLabel = new JLabel("Quản lý hệ thống");
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(headingLabel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Phòng ban", new phongbanGUI());
        tabbedPane.addTab("Lương", new luongGUI());
        tabbedPane.addTab("Chức vụ", new chucvuGUI());

        this.add(tabbedPane, BorderLayout.CENTER);
        
        // Tạo nút và thêm vào giao diện
        JButton backButton = new JButton("Quay lại");
        backButton.setBackground(Color.red);
        backButton.setForeground(Color.white);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện hành động khi nút được nhấp
                // Ví dụ: Đóng cửa sổ hiện tại và hiển thị giao diện chính
                dispose();
                new main();
            }
        });
        // Thêm nút vào phía dưới cùng của giao diện
        this.add(backButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }
    
    
}
