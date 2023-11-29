package Main.gui;

import Main.mall.*;
import Main.mall.Item.Product;
import Main.mgr.*;

import javax.swing.*;
import java.awt.*;

public class adminItemPage extends JFrame{
    public adminItemPage(Admin admin){
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

        JPanel groupPanel = new JPanel();
        groupPanel.setBounds(27,78,360,30);
        groupPanel.setPreferredSize(new Dimension(360, 30)); // 너비 조절
        groupPanel.setBackground(Color.WHITE);
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));


        groupPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        // 하얀색 바탕의 레이블
        JLabel whiteLabel = new JLabel();
        whiteLabel.setOpaque(true);
        whiteLabel.setBackground(Color.WHITE);
        groupPanel.add(whiteLabel);

        String name = "  이름  |        전화번호        |      주소    ";
        JLabel textLabel = new JLabel(name);
        textLabel.setFont(new Font("Inter", Font.PLAIN, 12));
        textLabel.setForeground(Color.BLACK);
        groupPanel.add(textLabel);

        userPagePanel.add(groupPanel);


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
            createGroupPanel(sandbox2,((Product) a));
            sandbox2.add(Box.createRigidArea(new Dimension(0, 3)));
        }
        sandbox2.add(Box.createRigidArea(new Dimension(0, 3)));

        sandbox2.add(Box.createVerticalGlue());

        // JScrollPane을 userPagePanel에 추가
        userPagePanel.add(scrollPane,BorderLayout.WEST);

        // JFrame에 전체 패널 추가
        add(userPagePanel);

        // 화면 가운데 정렬
        setLocationRelativeTo(null);
    }
    private void createGroupPanel(JPanel parentPanel, Product usr) {
        JPanel groupPanel = new JPanel();
        groupPanel.setPreferredSize(new Dimension(360, 30)); // 너비 조절
        groupPanel.setBackground(Color.WHITE);
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));


        groupPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        // 하얀색 바탕의 레이블
        JLabel whiteLabel = new JLabel();
        whiteLabel.setOpaque(true);
        whiteLabel.setBackground(Color.WHITE);
        groupPanel.add(whiteLabel);

        // usr name 텍스트 레이블
        String format = "%-8s";
        String name = usr.getName();
        name += " | ";
        JLabel textLabel = new JLabel(name);
        textLabel.setFont(new Font("Inter", Font.BOLD, 12));
        textLabel.setForeground(Color.BLACK);
        groupPanel.add(textLabel);

        //usr tel
        JLabel telLabel = new JLabel(" | ");
        telLabel.setFont(new Font("Inter",Font.PLAIN,12));
        telLabel.setForeground(Color.BLACK);
        groupPanel.add(telLabel);

        //usr address

        // 우측 여백 추가
        groupPanel.add(Box.createRigidArea(new Dimension(298, 0)));

        // 부모 패널에 그룹 패널 추가
        parentPanel.add(groupPanel);
    }
}

