package Main.gui;

import javax.swing.*;
import java.awt.*;

import Main.mall.*;
import Main.mgr.*;


public class MyPage extends JFrame{
    public MyPage(Admin admin){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800);
        setLayout(null);  // Absolute positioning 사용

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
        addXPadding(namePanel,60);

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
        addXPadding(namePanel,150);

        //이미지가 들어갈 것
        JPanel grayRectanglePanel = new JPanel();
        grayRectanglePanel.setPreferredSize(new Dimension(50, 50));
        grayRectanglePanel.setBackground(Color.GRAY);
        namePanel.add(grayRectanglePanel);
        addXPadding(namePanel,90);


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

        groupPanel.add(chuInfo);

        addYPadding(groupPanel,30);

        //4. QnA
        JPanel qnaInfo = new JPanel();
        qnaInfo.setPreferredSize(new Dimension(350,100));
        qnaInfo.setBackground(Color.WHITE);
        qnaInfo.setLayout(new BoxLayout(qnaInfo, BoxLayout.X_AXIS));
        qnaInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //str
        JLabel textLabel4 = new JLabel("Q & A");
        textLabel4.setSize(new Dimension(100,80));
        textLabel4.setFont(new Font("Inter", Font.PLAIN, 21));
        textLabel4.setForeground(Color.BLACK);
        qnaInfo.add(textLabel4);

        addXPadding(qnaInfo,250);

        // >
        JLabel arrow3 = new JLabel(">");
        arrow3.setFont(new Font("Inter", Font.BOLD, 21));
        arrow3.setForeground(Color.BLACK);
        qnaInfo.add(arrow3);

        groupPanel.add(qnaInfo);
        addYPadding(groupPanel,30);

        //5. 앱 문의
        JPanel munInfo = new JPanel();
        munInfo.setPreferredSize(new Dimension(350,100));
        munInfo.setBackground(Color.WHITE);
        munInfo.setLayout(new BoxLayout(munInfo, BoxLayout.X_AXIS));
        munInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //str
        JLabel textLabel5 = new JLabel("엡 문의");
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
        outerContainer.setBackground(new Color(82, 82, 82));
        btnPanel.add(outerContainer);

        // Text container
        JPanel textContainer = new JPanel();
        textContainer.setLayout(null);
        textContainer.setBounds(11, 14, 53, 12);
        textContainer.setBackground(new Color(82, 82, 82));

        JLabel labelText = new JLabel(" 메인으로");
        labelText.setForeground(Color.WHITE);
        labelText.setFont(new Font("Inter", Font.BOLD, 12));
        labelText.setHorizontalAlignment(JLabel.CENTER);
        labelText.setBounds(0, 0, 53, 12);

        textContainer.add(labelText);
        outerContainer.add(textContainer);
        /************메인으로 버튼 끝 ***********/
        addXPadding(btnPanel,30);
        /************종료 버튼***********/
        JPanel outerContainer1 = new JPanel();
        outerContainer1.setLayout(null);
        outerContainer1.setBounds(0, 0, 76, 44);
        outerContainer1.setBackground(Color.RED);
        btnPanel.add(outerContainer1);

        // Text container
        JPanel textContainer1 = new JPanel();
        textContainer1.setLayout(null);
        textContainer1.setBounds(11, 14, 53, 12);
        textContainer1.setBackground(Color.RED);

        JLabel labelText1 = new JLabel(" 로그아웃");
        labelText1.setForeground(Color.WHITE);
        labelText1.setFont(new Font("Inter", Font.BOLD, 12));
        labelText1.setHorizontalAlignment(JLabel.CENTER);
        labelText1.setBounds(0, 0, 53, 12);
        textContainer1.add(labelText1);
        outerContainer1.add(textContainer1);
        /************종료 버튼 끝 ***********/
        addXPadding(btnPanel,70);


        groupPanel.add(btnPanel);
        addYPadding(groupPanel,100);

        add(groupPanel);
        setLocationRelativeTo(null);
    }

    public void addYPadding(JPanel mainPane,int Height){
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), Height)));
    }

    public void addXPadding(JPanel mainPane,int width){
        mainPane.add(Box.createRigidArea(new Dimension(width, mainPane.getHeight())));
    }
}
