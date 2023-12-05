package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.Product;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
final class TYPE{
    public static final int CPU = 0;
    public static final int RAM = 1;
    public static final int STORAGE = 2;
    public static final int GPU = 3;
    public static final int POW = 4;
    public static final int CASE = 5;
    public static final int MainBoard = 6;
}

public class PartsRecommendList extends JFrame {
    public PartsRecommendList(ArrayList recommend[], String Type,Admin admin){
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
        JLabel title = new JLabel(Type + " 추천 목록");
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
        groupPanel.add(scrollPane);

        /** 물풍 출력 **/
        ArrayList<Product> a;
        switch (Type){
            case "Case":
                a = recommend[TYPE.CASE];
                break;
            case "Cpu":
                a = recommend[TYPE.CPU];
                break;
            case "GPU":
                a = recommend[TYPE.GPU];
                break;
            case "MainBoard":
                a = recommend[TYPE.MainBoard];
                break;
            case "Power":
                a = recommend[TYPE.POW];
                break;
            case "Ram":
                a = recommend[TYPE.RAM];
                break;
            case "Storage":
                a = recommend[TYPE.STORAGE];
                break;
            default:
                a = recommend[TYPE.CASE];
        }
        try {
            for(int i=0;i<10;i++){
                createProductPanel(sandbox2,a.get(i),i,recommend,a,admin);
                addYPadding(sandbox2,10);
            }
            addYPadding(groupPanel,10);
        }
        catch (IndexOutOfBoundsException e){

        }

        /************메인으로 버튼***********/
        JPanel outerContainer = new JPanel();
        outerContainer.setLayout(null);
        outerContainer.setPreferredSize(new Dimension(76,44));
        outerContainer.setAlignmentX(400);
        outerContainer.setBackground(Color.WHITE); // Change the background color to white

        JButton mainButton = new JButton("이전으로");
        mainButton.setForeground(Color.BLACK); // Change the text color to black
        mainButton.setFont(new Font("Inter", Font.BOLD, 12));
        mainButton.setBounds(0, 0, 76, 34);
        mainButton.setBackground(Color.WHITE); // Change the background color to white

        outerContainer.add(mainButton);
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                new RecommendList(recommend,admin);
            }
        });
        groupPanel.add(outerContainer);

        userPagePanel.add(groupPanel);

        addYPadding(groupPanel,30);

        add(userPagePanel);
        setVisible(true);
    }

    public void addYPadding(JPanel mainPane,int Height){
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), Height)));
    }

    public void addXPadding(JPanel mainPane,int width){
        mainPane.add(Box.createRigidArea(new Dimension(width, mainPane.getHeight())));
    }

    private void createProductPanel(JPanel parentPanel, Product product, int isFirst, ArrayList arr[],ArrayList<Product> pd,Admin admin) {
        JPanel productPanel = new JPanel();

        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.X_AXIS));
        productPanel.setPreferredSize(new Dimension(370, 80));

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
        if(isFirst == 0){
            textPanel.setBackground(new Color(255, 231, 111, 255));
        }
        else {
            textPanel.setBackground(Color.WHITE);
        }

        String name = product.getName();
        if(name.length() > 30){
            String sub = name.substring(0,29) + "...";
            name = sub;
        }
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
        nameLabel.setForeground(Color.BLACK);
        if(isFirst == 0){
            nameLabel.setBackground(new Color(255, 220, 65, 205));
        }
        else {
            nameLabel.setBackground(Color.WHITE);
        }

        JLabel priceLabel = new JLabel("가격: " + product.getPrice() + "원");
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        priceLabel.setForeground(new Color(40, 40, 40));
        if(isFirst == 0){
            priceLabel.setBackground(new Color(255, 220, 65, 205));
        }
        else {
            priceLabel.setBackground(Color.WHITE);
        }

        //TODO : Button에 입력받은 Type 받기
        JButton detailsButton = new JButton("제품 선택");
        detailsButton.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        detailsButton.setForeground(new Color(40, 40, 40));

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pd.remove(product);
                pd.add(0,product);
                dispose(); // Close the current window
                new RecommendList(arr,admin);
            }
        });

        JPanel namePricePanel = new JPanel();
        namePricePanel.setPreferredSize(new Dimension(300,70));
        namePricePanel.setLayout(new BoxLayout(namePricePanel, BoxLayout.Y_AXIS));
        if(isFirst == 0){
            namePricePanel.setBackground(new Color(255, 231, 111, 255));
        }
        else {
            namePricePanel.setBackground(Color.WHITE);
        }

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
