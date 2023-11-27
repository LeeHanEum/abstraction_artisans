package Main.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("MainPage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);

        JLabel welcomeLabel = new JLabel("<html>환영합니다!<br>원하는대로 컴퓨터 부품을 커스터마이징 해보세요:)</html>");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        welcomeLabel.setBounds(60, 130, 300, 60); // 위치와 크기 설정
        panel.add(welcomeLabel);

        String[] buttonLabels = {"부품 커스터마이징", "부품 리스트", "마이 페이지", "로그아웃"};
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            // 각 버튼의 위치와 크기를 직접 지정
            button.setBounds(110, 250 + i * 110, 230, 60);

            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(true);
            button.setBorderPainted(false);

            if (buttonLabels[i].equals("로그아웃")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 프로그램 종료
                        System.exit(0);
                    }
                });
            }

//            button.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    handleButtonClick(buttonLabels[i]); // 버튼이 클릭되었을 때 처리
//                }
//            });

            panel.add(button);
        }
        frame.getContentPane().add(panel);

        frame.setSize(450, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

//   private static void handleButtonClick(String buttonLabel) {
//        // 각 버튼에 따라 적절한 페이지로 이동
//        switch (buttonLabel) {
//            case "부품 커스터마이징":
//                CustomizingPage customizingPage = new CustomizingPage();
//                customizingPage.setVisible(true);
//                break;
//            case "마이 페이지":
//                UserListPage userListPage = new UserListPage();
//                userListPage.setVisible(true);
//                break;
//            case "부품 리스트":
//                ItemListPage itemListPage = new ItemListPage();
//                itemListPage.setVisible(true);
//                break;
//            case "로그아웃":
//                System.exit(0);
//                break;
//        }
//    }
}
