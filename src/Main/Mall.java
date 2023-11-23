package Main;

import Main.mall.Admin;

import java.awt.GridLayout;
import javax.swing.*;

public class Mall extends JFrame {

    private static Mall instance;

    private Mall() {
        initUI();
    }

    private void initUI() {
        setTitle("Mall GUI");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton loginButton = new JButton("로그인");
        JButton exitButton = new JButton("종료");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(loginButton);
        panel.add(exitButton);

        loginButton.addActionListener(e -> {
            new Admin().setVisible(true);
            dispose();
        });

        exitButton.addActionListener(e -> System.exit(0));

        add(panel);
        setLocationRelativeTo(null);
    }

    public static Mall getInstance() {
        if (instance == null)
            instance = new Mall();
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Mall.getInstance().setVisible(true);
        });
    }
}
