package Main.gui;

import Main.mall.*;
import Main.mall.Item.Product;
import Main.mgr.Manageable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Main.mall.UserChoiceHandler.*;

public class RecommendList extends JFrame {
    private final Map<String, Long> productIndices = new HashMap<>();
    public RecommendList(ArrayList recommend[],Admin admin){
        Login.currentUser.setRecommend(recommend);

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
        addYPadding(groupPanel,30);

        /***title Panel***/
        JPanel titlePane = new JPanel();
        titlePane.setPreferredSize(new Dimension(450,50));
        titlePane.setBackground(Color.WHITE);
        titlePane.setLayout(new BoxLayout(titlePane,BoxLayout.X_AXIS));

        addXPadding(titlePane,5);

        JPanel subPanel = new JPanel();
        subPanel.setPreferredSize(new Dimension(300,50));
        subPanel.setBackground(Color.WHITE);
        subPanel.setLayout(new BoxLayout(subPanel,BoxLayout.Y_AXIS));

        /***title text***/
        JLabel title = new JLabel("모두나와PC가 추천하는 콤퓨타!!");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Inter", Font.PLAIN, 20));
        subPanel.add(title);
        groupPanel.add(titlePane);

        JLabel subtitle = new JLabel("다양한 부품을 고려하여 최적의 성능을 제공하는 조합을 추천합니다.");
        subtitle.setForeground(Color.GRAY);
        subtitle.setFont(new Font("Inter", Font.PLAIN, 15));
        subPanel.add(subtitle);
        titlePane.add(subPanel);
        addXPadding(titlePane,300);
        groupPanel.add(titlePane);

        addYPadding(groupPanel,10);
        for(ArrayList<Product> a : recommend){
            try {
                //타입도 받아감
                createProductPanel(groupPanel,a.get(0),a.get(0).getType(),recommend,admin, a.get(0).getType());
                addYPadding(groupPanel,10);
            }
            catch (IndexOutOfBoundsException e){

            }

        }

        userPagePanel.add(groupPanel);

        /*****Button 2개 Panel******/
        JPanel btnPanel = new JPanel();
        btnPanel.setPreferredSize(new Dimension(350,50));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));

        addXPadding(btnPanel,230);

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
        addXPadding(btnPanel,15);
        /************종료 버튼***********/
        JPanel outerContainer1 = new JPanel();
        outerContainer1.setLayout(null);
        outerContainer1.setBounds(0, 0, 76, 44);
        outerContainer1.setBackground(Color.WHITE); // Change the background color to white

        JButton logoutButton = new JButton("장바구니 담기");
        logoutButton.setForeground(Color.BLACK); // Change the text color to black
        logoutButton.setFont(new Font("Inter", Font.BOLD, 12));
        logoutButton.setBounds(0, 0, 76, 44);
        logoutButton.setBackground(Color.WHITE); // Change t동e background color to white

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "이 조합이 장바구니에 담겼습니다.");
                for(ArrayList<Product> a : recommend){
                    try {
                        Login.currentUser.getCart().addItemToCart(a.get(0));
                    }
                    catch (IndexOutOfBoundsException ex){

                    }

                }
                dispose();
                new MyCartPage(admin);
            }
        });

        outerContainer1.add(logoutButton);
        btnPanel.add(outerContainer1);
        /************종료 버튼 끝 ***********/
        addXPadding(btnPanel,30);


        groupPanel.add(btnPanel);
        addYPadding(groupPanel,20);

        add(userPagePanel);
        setVisible(true);
    }

    public void addYPadding(JPanel mainPane,int Height){
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), Height)));
    }

    public void addXPadding(JPanel mainPane,int width){
        mainPane.add(Box.createRigidArea(new Dimension(width, mainPane.getHeight())));
    }

    private void createProductPanel(JPanel parentPanel, Product product, String componentName, ArrayList arr[],Admin admin,String componentType) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.X_AXIS));
        productPanel.setPreferredSize(new Dimension(370, 60));
        productPanel.setBackground(new Color(230, 230, 230));
        addXPadding(productPanel,10);

        JPanel imagePane = new JPanel();
        imagePane.setLayout(new BoxLayout(imagePane,BoxLayout.Y_AXIS));
        imagePane.setPreferredSize(new Dimension(60,60));

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(60, 60));
        imageLabel.setBounds(10,10,60,60);
        imagePane.add(imageLabel);

        ImageIcon imageIcon = new ImageIcon("src/Main/resource/productImage/" + product.getProductId() + "번.png");
        Image resizedImage = imageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon resizedMainImageIcon = new ImageIcon(resizedImage);
        imageLabel.setIcon(resizedMainImageIcon);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        textPanel.setBackground(Color.WHITE);

        String name = product.getName();
        if(name.length() > 30){
            String sub = name.substring(0,29) + "...";
            name = sub;
        }
        //타입: 제품명 나오게 수정
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBackground(Color.WHITE);

        JLabel priceLabel = new JLabel("가격: " + product.getPrice() + "원");
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        priceLabel.setForeground(new Color(40, 40, 40));
        priceLabel.setBackground(Color.WHITE);

        //TODO : Button에 입력받은 Type 받기
        //추천 목록 버튼에도 타입 추가
        JButton detailsButton = new JButton( componentType + " 추천목록");
        detailsButton.setBackground(Color.WHITE);
        detailsButton.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        detailsButton.setForeground(new Color(40, 40, 40));
        detailsButton.setPreferredSize(new Dimension(450, 30));
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                new PartsRecommendList(arr, componentName, admin); // Open the main page
            }
        });

        //버튼 크기 통일 위해 다 필요해요 하나씩 다 지워봤는데 다 달라져요 자꾸
        detailsButton.setMaximumSize(new Dimension(145, 25));
        detailsButton.setPreferredSize(new Dimension(145, 25));
        detailsButton.setMinimumSize(new Dimension(145, 25));

        JPanel namePricePanel = new JPanel();
        namePricePanel.setPreferredSize(new Dimension(300,70));
        namePricePanel.setLayout(new BoxLayout(namePricePanel, BoxLayout.Y_AXIS));
        namePricePanel.setBackground(Color.WHITE);
        namePricePanel.add(nameLabel);
        namePricePanel.add(priceLabel);
        namePricePanel.add(detailsButton);

        textPanel.add(namePricePanel);

        productPanel.add(imagePane);
        productPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        productPanel.add(textPanel);

        addXPadding(productPanel,20);
        parentPanel.add(productPanel);
    }

}
