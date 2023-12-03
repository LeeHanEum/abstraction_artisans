package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.GraphicsCard;
import Main.mgr.Manageable;
import Main.mall.User;
import Main.mgr.Manager;

import javax.security.sasl.SaslClient;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class UserListPage extends JFrame{
    public UserListPage(Admin admin){
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
        scrollPane.setBounds(30, 110, 390, 560);

        //sandbox 2
        sandbox2.setLayout(new BoxLayout(sandbox2,BoxLayout.Y_AXIS));
        sandbox2.setBackground(Color.LIGHT_GRAY);
        sandbox2.setBounds(30,110,390,560);


        for(Manageable a : admin.getUserList()){
            createGroupPanel(sandbox2,((User) a));
            sandbox2.add(Box.createRigidArea(new Dimension(0, 3)));
        }
        sandbox2.add(Box.createRigidArea(new Dimension(0, 3)));

        sandbox2.add(Box.createVerticalGlue());

        // JScrollPane을 userPagePanel에 추가
        userPagePanel.add(scrollPane,BorderLayout.WEST);

        JButton mainButton = new JButton("메인으로");
        mainButton.setForeground(Color.BLACK); // Change the text color to black
        mainButton.setFont(new Font("Inter", Font.BOLD, 12));
        mainButton.setBounds(350, 700, 76, 44);
        mainButton.setBackground(Color.WHITE); // Change the background color to white

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                new AdminMainPage(admin); // Open the main page
            }
        });

        userPagePanel.add(mainButton);

        // JFrame에 전체 패널 추가
        add(userPagePanel);

        setVisible(true);
    }

    private void createGroupPanel(JPanel parentPanel, User usr) {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(360,30));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));


        JPanel groupPanel = new JPanel();
        groupPanel.setPreferredSize(new Dimension(280, 30)); // 너비 조절
        groupPanel.setBackground(Color.WHITE);
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));

        //초기 패딩
        groupPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        // 하얀색 바탕의 레이블
        JLabel whiteLabel = new JLabel();
        whiteLabel.setOpaque(true);
        whiteLabel.setBackground(Color.WHITE);
        groupPanel.add(whiteLabel);

        // usr name 텍스트 레이블
        String name = usr.getName();
        if(name.length() == 2){
            name = name.substring(0,1) + "  " + name.substring(1,2);
        }
        name += " | ";
        JLabel textLabel = new JLabel(name);
        textLabel.setFont(new Font("Inter", Font.BOLD, 12));
        textLabel.setForeground(Color.BLACK);
        groupPanel.add(textLabel);

        //usr tel
        JLabel telLabel = new JLabel(usr.getAddress() + " | ");
        telLabel.setFont(new Font("Inter",Font.PLAIN,12));
        telLabel.setForeground(Color.BLACK);
        groupPanel.add(telLabel);

        //usr address
        JLabel addLabel = new JLabel(usr.getTel());
        addLabel.setFont(new Font("Inter",Font.PLAIN,12));
        addLabel.setForeground(Color.BLACK);
        groupPanel.add(addLabel);


        // 우측 여백 추가
        groupPanel.add(Box.createRigidArea(new Dimension(30, 0)));

        mainPanel.add(groupPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(20, 30)));

        mainPanel.add(Box.createRigidArea(new Dimension(5,30)));
        //수정버튼
        JPanel outerContainer = new JPanel();
        outerContainer.setPreferredSize(new Dimension(40,30));
        outerContainer.setLayout(null);
        outerContainer.setBounds(0, 0, 40, 30);
        outerContainer.setBackground(new Color(82, 82, 82));
        mainPanel.add(outerContainer);

        // Text container
        JPanel textContainer = new JPanel();
        textContainer.setLayout(null);
        textContainer.setBounds(10, 0, 60, 30);
        textContainer.setBackground(new Color(82, 82, 82));

        JLabel labelText = new JLabel(" 주문내역");
        labelText.setForeground(Color.WHITE);
        labelText.setFont(new Font("Inter", Font.BOLD, 12));
        labelText.setHorizontalAlignment(JLabel.LEFT);
        labelText.setBounds(0, 0, 130, 30);
        textContainer.add(labelText);
        outerContainer.add(textContainer);



        // 부모 패널에 그룹 패널 추가
        parentPanel.add(mainPanel);
    }
}

