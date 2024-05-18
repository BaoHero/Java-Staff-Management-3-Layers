package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.FontUIResource;

public class main extends JFrame implements ActionListener {
    JButton button1, button2, button3;
    Panel_funct func_0, func_1, func_2;

    public main() {
        setTitle("Giao diện chính");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(660, 320);
        setLocationRelativeTo(null);
        
        JLabel headingLabel = new JLabel("Đồ án quản lý nhân sự");
        
        headingLabel.setFont(new FontUIResource("Times New Roman", FontUIResource.ITALIC, 16));
        
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(headingLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();

        
        JPanel panel_functs = new JPanel(new FlowLayout());
        func_0 = new Panel_funct("room.png", "Quản lí hệ thống");
        func_1 = new Panel_funct("calendar.png", "Quản lí chấm công");
        func_2 = new Panel_funct("human.png", "Quản lí nhân viên");
        panel_functs.add(func_0);
        panel_functs.add(func_1);
        panel_functs.add(func_2);
        
        button1 = func_0.get_btn();
        button2 = func_1.get_btn();
        button3 = func_2.get_btn();
        
        button1.setSize(100, 30);
        button2.setSize(100, 30);
        button3.setSize(100, 30);
        
        
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        
        button1.setBackground(Color.ORANGE);
        button2.setBackground(Color.RED);
        button3.setBackground(Color.GREEN);
        
        button1.setForeground(Color.WHITE);
        button2.setForeground(Color.WHITE);
        button3.setForeground(Color.WHITE);
       
        
        panel_functs.setSize(600, 500);
        add(panel_functs, BorderLayout.CENTER);
        add(panel , BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            new MainFrame_Cv_Pb_L();
        } else if (e.getSource() == button2) {
            new MainFrame_BCC_KT_KL();
        } else if (e.getSource() == button3) {
            new MainFrame_NV_HD_TD();
        }
        setVisible(false);
    }
}


