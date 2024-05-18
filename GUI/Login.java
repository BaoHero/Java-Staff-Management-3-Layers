package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BLL.LoginBLL;

import DTO.LoginDTO;


public class Login extends JFrame {
    LoginBLL userBLL = new LoginBLL();
    JTextField usernameField;
    JPasswordField passwordField;

    public Login() {
        setTitle("Đăng nhập");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Tài khoản :");
        usernameLabel.setBounds(10, 10, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 10, 160, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Mật khẩu :");
        passwordLabel.setBounds(10, 40, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 40, 160, 25);
        add(passwordField);

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(100, 80, 100, 25);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                LoginDTO user = new LoginDTO(username, password);

                if (userBLL.login(user)) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công !");
                    new main().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu , mời nhập lại !", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        
        new Login().setVisible(true);
    }
        
}



