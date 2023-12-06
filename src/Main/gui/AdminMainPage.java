package Main.gui;

import Main.mall.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainPage extends JFrame{

    private final Admin admin;

    public AdminMainPage(Admin admin) {
        this.admin = admin;

        setTitle("쇼핑몰 메인 페이지");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(null);

        ImageIcon logoIcon = new ImageIcon("src/Main/resource/computer.png"); // Replace with the actual path to your logo image
        Image resizedLogoImage = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedLogoIcon = new ImageIcon(resizedLogoImage);

        JLabel logoLabel = new JLabel(resizedLogoIcon);
        logoLabel.setBounds(150, 20, 150, 150); // Adjust the bounds as needed
        panel.add(logoLabel);

        JLabel welcomeLabel = new JLabel("관리자 페이지");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        welcomeLabel.setBounds(60, 150, 300, 60);
        panel.add(welcomeLabel);

        String[] buttonLabels = {"회원 리스트", "부품 리스트", "로그아웃"};
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(110, 250 + i * 130, 230, 60);

            button.setOpaque(true);
            button.setBackground(new Color(64, 64, 64));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);

            if (buttonLabels[i].equals("로그아웃")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new LoginPage(admin);
                    }
                });
            } else {
                final String pageName = buttonLabels[i]; // Create a final variable to use inside ActionListener
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose(); // Close current window
                        navigateToPage(pageName);
                    }
                });
            }

            panel.add(button);
        }

        setContentPane(panel);
        setVisible(true);
    }

    private void navigateToPage(String pageName) {
        switch (pageName) {
            case "회원 리스트":
                new UserListPage(admin);
                break;
            case "부품 리스트":
                new AdminItemPage(admin);
                break;
            default:
                break;
        }
    }
}
