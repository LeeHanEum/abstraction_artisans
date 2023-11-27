package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.Product;
import Main.mgr.Manageable;
import Main.mall.User;

import javax.security.sasl.SaslClient;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ItemListPage extends JFrame{
    public ItemListPage(Admin admin){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800);
        setLayout(null);  // Absolute positioning 사용

        // 전체 패널
        JPanel userPagePanel = new JPanel();
        userPagePanel.setBackground(Color.WHITE);
        userPagePanel.setBounds(0, 0, 450, 800);
        userPagePanel.setLayout(null);

        //title
        JLabel titleLabel = new JLabel("회원리스트");
        titleLabel.setBounds(30, 40, 300, 30); // 위치와 크기 조정
        titleLabel.setFont(new Font("Inter", Font.PLAIN, 20));
        userPagePanel.add(titleLabel);


        JPanel sandbox2 = new JPanel();

        //Scrollable 하게 만듬
        JScrollPane scrollPane = new JScrollPane(sandbox2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBounds(30, 110, 390, 610);

        //sandbox 2
        sandbox2.setLayout(new BoxLayout(sandbox2,BoxLayout.Y_AXIS));
        sandbox2.setBackground(Color.LIGHT_GRAY);
        sandbox2.setBounds(30,110,390,610);


        for(Manageable a : admin.getAllItem()){
            createGroupPanel(sandbox2,((Product) a).getName());
            System.out.println(((Product) a).getName());
            sandbox2.add(Box.createRigidArea(new Dimension(0, 3)));
        }

        sandbox2.add(Box.createVerticalGlue());

        // JScrollPane을 userPagePanel에 추가
        userPagePanel.add(scrollPane,BorderLayout.WEST);

        // JFrame에 전체 패널 추가
        add(userPagePanel);

        // 화면 가운데 정렬
        setLocationRelativeTo(null);
    }

    private void createGroupPanel(JPanel parentPanel, String labelText) {
        JPanel groupPanel = new JPanel();
        groupPanel.setPreferredSize(new Dimension(390, 100)); // 너비 조절
        groupPanel.setBackground(Color.WHITE);
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));


        groupPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        // 하얀색 바탕의 레이블
        JLabel whiteLabel = new JLabel();
        whiteLabel.setOpaque(true);
        whiteLabel.setBackground(Color.WHITE);
        groupPanel.add(whiteLabel);

        // 텍스트 레이블
        JLabel textLabel = new JLabel(labelText);
        textLabel.setFont(new Font("Inter", Font.PLAIN, 12));
        textLabel.setForeground(Color.BLACK);
        groupPanel.add(textLabel);

        // 우측 여백 추가
        groupPanel.add(Box.createRigidArea(new Dimension(300, 0)));

        // 부모 패널에 그룹 패널 추가
        parentPanel.add(groupPanel);

    }
    public static void main(String[] args) {
        Admin admin = new Admin(); // create an instance of Admin
        ItemListPage itemListPage = new ItemListPage(admin); // create an instance of ItemListPage

        itemListPage.setVisible(true); // set the frame to be visible
    }
    }