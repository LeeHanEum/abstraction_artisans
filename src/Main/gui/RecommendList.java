package Main.gui;

import Main.mall.*;
import Main.mall.Item.Product;
import Main.mgr.Manageable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecommendList extends JFrame {
    private final Map<String, Long> productIndices = new HashMap<>();
    public RecommendList(ArrayList recommend[],Admin admin){
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

        /***title Panel***/
        JPanel titlePane = new JPanel();
        titlePane.setPreferredSize(new Dimension(450,50));
        titlePane.setBackground(Color.WHITE);
        titlePane.setLayout(new BoxLayout(titlePane,BoxLayout.X_AXIS));

        addXPadding(titlePane,30);

        /***title text***/
        JLabel title = new JLabel("최적의 부품! 이 조합을 추천합니다.");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Inter", Font.PLAIN, 20));
        titlePane.add(title);
        addXPadding(titlePane,300);
        groupPanel.add(titlePane);

        for(ArrayList<Product> a : recommend){
            productIndices.clear();
            productIndices.put(a.get(0).getName().toLowerCase(),(a.get(0).getProductId()));
            createProductPanel(groupPanel,a.get(0),a.get(0).getType(),admin);
        }





        userPagePanel.add(groupPanel);
        add(userPagePanel);
        setVisible(true);
    }

    public void addYPadding(JPanel mainPane,int Height){
        mainPane.add(Box.createRigidArea(new Dimension(mainPane.getWidth(), Height)));
    }

    public void addXPadding(JPanel mainPane,int width){
        mainPane.add(Box.createRigidArea(new Dimension(width, mainPane.getHeight())));
    }

    private void createProductPanel(JPanel parentPanel, Product product, String componentName,Admin admin) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.X_AXIS));
        productPanel.setPreferredSize(new Dimension(370, 100));
        productPanel.setBackground(new Color(230, 230, 230));

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(80, 80));

        ImageIcon imageIcon = loadImage(componentName, product.getName().toLowerCase(),admin);
        if (imageIcon != null) {
            imageLabel.setIcon(imageIcon);
        }

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        nameLabel.setForeground(Color.BLACK);

        JLabel priceLabel = new JLabel("가격: " + product.getPrice() + "원");
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        priceLabel.setForeground(new Color(40, 40, 40));

        JButton detailsButton = new JButton("상세페이지");
        detailsButton.setBackground(new Color(190, 190, 190));
        detailsButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        detailsButton.setForeground(new Color(40, 40, 40));

        JPanel namePricePanel = new JPanel();
        namePricePanel.setLayout(new BoxLayout(namePricePanel, BoxLayout.Y_AXIS));
        namePricePanel.add(nameLabel);
        namePricePanel.add(priceLabel);
        namePricePanel.add(detailsButton);

        textPanel.add(namePricePanel);

        productPanel.add(imageLabel);
        productPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        productPanel.add(textPanel);

        parentPanel.add(productPanel);
    }
    private ImageIcon loadImage(String componentName, String productName,Admin admin) {
        ArrayList<String> imagePathList = getImagePathList(componentName,admin);
        long productIndex = productIndices.getOrDefault(productName, (long)0);

        if (!imagePathList.isEmpty() && productIndex < imagePathList.size()) {
            String imagePath = imagePathList.get((int)productIndex);
            return resizeImage(new ImageIcon(imagePath), 80, 80);
        }

        return null;
    }
    private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
    private ArrayList<String> getImagePathList(String componentName,Admin admin) {
        switch (componentName) {
            case "CPU":
                return admin.imagePathCpu();
            case "Case":
                return admin.imagePathCase();
            case "Mainboard":
                return admin.imagePathMainboard();
            case "RAM":
                return admin.imagePathRam();
            case "Power":
                return admin.imagePathPower();
            case "Graphics Card":
                return admin.imagePathGraphics();
            case "Storage":
                return admin.imagePathStorage();
            default:
                return new ArrayList<>();
        }
    }
}
