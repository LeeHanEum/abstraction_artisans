package Main.gui;

import Main.mall.*;
import Main.mall.Item.Product;
import Main.mgr.*;

import javax.swing.*;
import java.awt.*;

public class AdminItemPage extends JFrame{
    public AdminItemPage(Admin admin){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800);
        setLayout(null);  // Absolute positioning 사용

        // 전체 패널
        JPanel userPagePanel = new JPanel(null);
        userPagePanel.setBackground(Color.WHITE);
        userPagePanel.setBounds(0, 0, 450, 800);
        userPagePanel.setLayout(null);

        String back = "";
        JLabel backLabel = new JLabel(back);
        backLabel.setBounds(25,5,300,30);
        backLabel.setFont(new Font("Inter", Font.BOLD, 20));
        backLabel.setForeground(Color.BLACK);
        userPagePanel.add(backLabel);


        //title
        JLabel titleLabel = new JLabel("제품리스트");
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

        String name = "    Type         |        name        ";
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
        scrollPane.setBounds(30, 110, 390, 560);

        //sandbox 2
        sandbox2.setLayout(new BoxLayout(sandbox2,BoxLayout.Y_AXIS));
        sandbox2.setBackground(Color.LIGHT_GRAY);
        sandbox2.setBounds(30,110,390,560);


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

        setVisible(true);
    }
    private void createGroupPanel(JPanel parentPanel, Product usr) {
        JPanel adminPanel = new JPanel();
        adminPanel.setPreferredSize(new Dimension(360,30));
        adminPanel.setBackground(Color.WHITE);
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.X_AXIS));

        JPanel groupPanel = new JPanel();
        groupPanel.setPreferredSize(new Dimension(320, 30)); // 너비 조절 원래 360
        groupPanel.setBackground(Color.WHITE);
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));


        groupPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        // 하얀색 바탕의 레이블
        JLabel whiteLabel = new JLabel();
        whiteLabel.setOpaque(true);
        whiteLabel.setBackground(Color.WHITE);
        groupPanel.add(whiteLabel);

        // product type
        String format = "%-10s |";
        String type = usr.getType();
        String formType = String.format(format,type);
        JLabel textLabel = new JLabel(formType);
        textLabel.setFont(new Font("Andale Mono", Font.BOLD, 12));
        textLabel.setForeground(Color.BLACK);
        groupPanel.add(textLabel);

        // product name
        String nameFormat = "%-25s ";
        String name = usr.getName();
        if (name.length() > 20){
            name = name.substring(0,17);
            name += "...";
        }
        String formattedName = String.format(nameFormat,name);
        JLabel telLabel = new JLabel(formattedName);
        telLabel.setFont(new Font("Andale Mono",Font.PLAIN,12));
        telLabel.setForeground(Color.BLACK);
        groupPanel.add(telLabel);

        // 우측 여백 추가
        groupPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        adminPanel.add(groupPanel);

        //수정버튼
        JPanel outerContainer = new JPanel();
        outerContainer.setPreferredSize(new Dimension(40,30));
        outerContainer.setLayout(null);
        outerContainer.setBounds(0, 0, 40, 30);
        outerContainer.setBackground(new Color(82, 82, 82));
        adminPanel.add(outerContainer);

        // Text container
        JPanel textContainer = new JPanel();
        textContainer.setLayout(null);
        textContainer.setBounds(13, 0, 40, 30);
        textContainer.setBackground(new Color(82, 82, 82));

        JLabel labelText = new JLabel(" 수정");
        labelText.setForeground(Color.WHITE);
        labelText.setFont(new Font("Inter", Font.BOLD, 12));
        labelText.setHorizontalAlignment(JLabel.LEFT);
        labelText.setBounds(0, 0, 40, 30);
        textContainer.add(labelText);
        outerContainer.add(textContainer);

        // 우측 여백 추가
        groupPanel.add(Box.createRigidArea(new Dimension(8, 0)));

        // 부모 패널에 그룹 패널 추가
        parentPanel.add(adminPanel);
    }
}

