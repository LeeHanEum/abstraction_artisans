package Main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Main.mall.*;
import Main.mgr.*;


public class MyPage extends JFrame{
    public MyPage(Admin admin){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800);
        setLayout(null);  // Absolute positioning 사용
        setLocationRelativeTo(null);

        // 전체 패널
        JPanel userPagePanel = new JPanel();
        userPagePanel.setBackground(Color.WHITE);
        userPagePanel.setBounds(0, 0, 450, 800);
        userPagePanel.setLayout(null);

        //main Y AXIS BOX
        JPanel groupPanel = new JPanel();
        groupPanel.setBounds(0,0,450,800);
        groupPanel.setPreferredSize(new Dimension(450, 800)); // 너비 조절
        groupPanel.setBackground(Color.WHITE);
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));

        //padding
        addYPadding(groupPanel,50);

        //name Panel X_AXIS
        JPanel namePanel = new JPanel();
        namePanel.setPreferredSize(new Dimension(450,50));
        namePanel.setBackground(Color.WHITE);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));

        //initPadding
        addXPadding(namePanel,50);

        //name square y axis
        JPanel subNamePanel = new JPanel();
        subNamePanel.setPreferredSize(new Dimension(100,50));
        subNamePanel.setBackground(Color.WHITE);
        subNamePanel.setLayout(new BoxLayout(subNamePanel, BoxLayout.Y_AXIS));
        //column
        JLabel columnLabel = new JLabel("이름 :");
        columnLabel.setFont(new Font("Inter", Font.PLAIN, 17));
        columnLabel.setForeground(Color.BLACK);
        subNamePanel.add(columnLabel);

        //TODO : 로그인이랑 연결 되면 밑에 주석 없애주세요 지금 임의로 김연우로 해놨음 이름
        //name
        String name = Login.currentUser.getName();
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Inter", Font.PLAIN, 24));
        nameLabel.setForeground(Color.BLACK);
        subNamePanel.add(nameLabel);



        namePanel.add(subNamePanel);
        addXPadding(namePanel,200);

        //이미지가 들어갈 것
        ImageIcon logoIcon = new ImageIcon("src/Main/resource/user.png"); // Replace with the actual path to your logo image
        Image resizedLogoImage = logoIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedLogoIcon = new ImageIcon(resizedLogoImage);

        JLabel logoLabel = new JLabel(resizedLogoIcon);
        logoLabel.setBounds(150, 0, 50, 50); // Adjust the bounds as needed
        namePanel.add(logoLabel);
        addXPadding(namePanel,70);


        groupPanel.add(namePanel);
        addYPadding(groupPanel,50);

        //유저 선택 버튼들
        //1. 내 정보 관리
        JPanel settingInfo = new JPanel();
        settingInfo.setPreferredSize(new Dimension(350,100));
        settingInfo.setBackground(Color.WHITE);
        settingInfo.setLayout(new BoxLayout(settingInfo, BoxLayout.X_AXIS));
        settingInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //str
        JLabel textLabel1 = new JLabel("내 정보 관리");
        textLabel1.setSize(new Dimension(100,80));
        textLabel1.setFont(new Font("Inter", Font.PLAIN, 21));
        textLabel1.setForeground(Color.BLACK);
        settingInfo.add(textLabel1);

        addXPadding(settingInfo,200);

        // >
        JLabel arrow = new JLabel(">");
        arrow.setFont(new Font("Inter", Font.BOLD, 21));
        arrow.setForeground(Color.BLACK);
        settingInfo.add(arrow);

        settingInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new SetMyInfoPage(admin); // Open the setting page
            }
        });

        groupPanel.add(settingInfo);

        addYPadding(groupPanel,30);


        //2. 찜목록
        JPanel zimInfo = new JPanel();
        zimInfo.setPreferredSize(new Dimension(350,100));
        zimInfo.setBackground(Color.WHITE);
        zimInfo.setLayout(new BoxLayout(zimInfo, BoxLayout.X_AXIS));
        zimInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //str
        JLabel textLabel2 = new JLabel("찜 목록");
        textLabel2.setSize(new Dimension(100,80));
        textLabel2.setFont(new Font("Inter", Font.PLAIN, 21));
        textLabel2.setForeground(Color.BLACK);
        zimInfo.add(textLabel2);

        addXPadding(zimInfo,250);

        // >
        JLabel arrow1 = new JLabel(">");
        arrow1.setFont(new Font("Inter", Font.BOLD, 21));
        arrow1.setForeground(Color.BLACK);
        zimInfo.add(arrow1);

        //찜목록으로 바꿔주는 액션 추가
        zimInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new MyInterestPage(admin); // Open the setting page
            }
        });
        groupPanel.add(zimInfo);

        addYPadding(groupPanel,30);

        //3. 추천받은 조합 보기
        JPanel chuInfo = new JPanel();
        chuInfo.setPreferredSize(new Dimension(350,100));
        chuInfo.setBackground(Color.WHITE);
        chuInfo.setLayout(new BoxLayout(chuInfo, BoxLayout.X_AXIS));
        chuInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //str
        JLabel textLabel3 = new JLabel("추천받은 조합 보기");
        textLabel3.setSize(new Dimension(100,80));
        textLabel3.setFont(new Font("Inter", Font.PLAIN, 21));
        textLabel3.setForeground(Color.BLACK);
        chuInfo.add(textLabel3);

        addXPadding(chuInfo,152);

        // >
        JLabel arrow2 = new JLabel(">");
        arrow2.setFont(new Font("Inter", Font.BOLD, 21));
        arrow2.setForeground(Color.BLACK);
        chuInfo.add(arrow2);

        chuInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Login.currentUser.getRecommend() == null) {
                    JOptionPane.showMessageDialog(null, "추천받은 조합이 없습니다.", "추천받은 조합 보기", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                dispose();
                new RecommendList(Login.currentUser.getRecommend(), admin); // Open the recommend list page
            }
        });

        groupPanel.add(chuInfo);

        addYPadding(groupPanel,30);

        //4. Cart
        JPanel myCart = new JPanel();
        myCart.setPreferredSize(new Dimension(350,100));
        myCart.setBackground(Color.WHITE);
        myCart.setLayout(new BoxLayout(myCart, BoxLayout.X_AXIS));
        myCart.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //str
        JLabel textLabel4 = new JLabel("장바구니 보기");
        textLabel4.setSize(new Dimension(100,80));
        textLabel4.setFont(new Font("Inter", Font.PLAIN, 21));
        textLabel4.setForeground(Color.BLACK);
        myCart.add(textLabel4);

        addXPadding(myCart,200);

        // >
        JLabel arrow3 = new JLabel(">");
        arrow3.setFont(new Font("Inter", Font.BOLD, 21));
        arrow3.setForeground(Color.BLACK);
        myCart.add(arrow3);

        myCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new MyCartPage(admin); // Open the cart page
            }
        });

        groupPanel.add(myCart);
        addYPadding(groupPanel,30);

        //5. 앱 문의
        JPanel munInfo = new JPanel();
        munInfo.setPreferredSize(new Dimension(350,100));
        munInfo.setBackground(Color.WHITE);
        munInfo.setLayout(new BoxLayout(munInfo, BoxLayout.X_AXIS));
        munInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //str
        JLabel textLabel5 = new JLabel("앱 문의");
        textLabel5.setSize(new Dimension(100,80));
        textLabel5.setFont(new Font("Inter", Font.PLAIN, 21));
        textLabel5.setForeground(Color.BLACK);
        munInfo.add(textLabel5);

        addXPadding(munInfo,250);

        // >
        JLabel arrow4 = new JLabel(">");
        arrow4.setFont(new Font("Inter", Font.BOLD, 21));
        arrow4.setForeground(Color.BLACK);
        munInfo.add(arrow4);

// 앱 문의 버튼에 대한 액션 리스너 추가
        munInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "추상화 장인들(주)\n문의 : chusanghwaJanindle@gmail.com", "앱 문의", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        groupPanel.add(munInfo);

        //padding
        addYPadding(groupPanel,100);

        /*****Button 2개 Panel******/
        JPanel btnPanel = new JPanel();
        btnPanel.setPreferredSize(new Dimension(350,100));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));

        addXPadding(btnPanel,190);

        /************메인으로 버튼***********/
        JPanel outerContainer = new JPanel();
        outerContainer.setLayout(null);
        outerContainer.setBounds(0, 0, 76, 44);
        outerContainer.setBackground(Color.WHITE); // Change the background color to white

        JButton mainButton = new JButton("메인으로");
        mainButton.setForeground(Color.BLACK); // Change the text color to black
        mainButton.setFont(new Font("Inter", Font.BOLD, 12));
        mainButton.setBounds(0, 0, 76, 44);
        mainButton.setBackground(Color.WHITE); // Change the background color to white

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                new MainPage(admin); // Open the main page
            }
        });

        outerContainer.add(mainButton);
        btnPanel.add(outerContainer);
        /************메인으로 버튼 끝 ***********/
        addXPadding(btnPanel,30);
        /************종료 버튼***********/
        JPanel outerContainer1 = new JPanel();
        outerContainer1.setLayout(null);
        outerContainer1.setBounds(0, 0, 76, 44);
        outerContainer1.setBackground(Color.WHITE); // Change the background color to white

        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setForeground(Color.BLACK); // Change the text color to black
        logoutButton.setFont(new Font("Inter", Font.BOLD, 12));
        logoutButton.setBounds(0, 0, 76, 44);
        logoutButton.setBackground(Color.WHITE); // Change the background color to white

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                new LoginPage(admin); // Open the login page
            }
        });

        outerContainer1.add(logoutButton);
        btnPanel.add(outerContainer1);
        /************종료 버튼 끝 ***********/
        addXPadding(btnPanel,70);


        groupPanel.add(btnPanel);
        addYPadding(groupPanel,100);

        add(groupPanel);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public void addYPadding(JPanel mainPane,int Height){
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), Height)));
    }

    public void addXPadding(JPanel mainPane,int width){
        mainPane.add(Box.createRigidArea(new Dimension(width, mainPane.getHeight())));
    }
}
