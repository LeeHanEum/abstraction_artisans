package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.Product;
import Main.mall.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MyInterestPage extends JFrame {

    private final Admin admin;

    public MyInterestPage(Admin admin) {
        this.admin = admin;

        setTitle("찜 목록");
        setSize(450, 800); // Adjusted size for better visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for product list
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        // Get the product list from the user's interest
        List<Product> interestList = Login.currentUser.getInterest().getInterestList();

        // Header
        JLabel headerLabel = new JLabel("찜 목록");
        headerLabel.setBounds(20, 20, 200, 30);
        headerLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        panel.add(headerLabel);

        // Vertical position for each product
        int yPosition = 70;

        // Display each product in the interest list
        for (Product product : interestList) {
            // Display product image on the left
            JLabel imageLabel = new JLabel();

            //전체적으로 x축을 왼쪽으로 더 붙여줬음
            imageLabel.setBounds(30, yPosition, 100, 100);
            ImageIcon imageIcon = new ImageIcon("src/Main/resource/productImage/" + product.getProductId() + "번.png");
            Image resizedImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon resizedMainImageIcon = new ImageIcon(resizedImage);
            imageLabel.setIcon(resizedMainImageIcon);
            panel.add(imageLabel);

            // Display product details
            JLabel nameLabel = new JLabel(product.getName());
            nameLabel.setBounds(180, yPosition, 200, 20);
            panel.add(nameLabel);

            JLabel priceLabel = new JLabel("가격: $" + product.getPrice());
            priceLabel.setBounds(180, yPosition + 20, 200, 20);
            panel.add(priceLabel);

            // 장바구니 담기 버튼
            JButton addToCartButton = new JButton("장바구니 담기");
            addToCartButton.setBounds(180, yPosition + 50, 115, 20);
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add to Cart button clicked
                    Login.currentUser.getCart().addItemToCart(product);
                    JOptionPane.showMessageDialog(null, "장바구니에 상품을 담았습니다.");
                }
            });
            panel.add(addToCartButton);

            // 찜 해제 버튼
            JButton removeButton = new JButton("찜 해제");
            removeButton.setBounds(310, yPosition + 50, 80, 20);
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Remove button clicked
                    Login.currentUser.getInterest().deleteInterest(product.getProductId());
                    refreshUI();
                }
            });
            panel.add(removeButton);

            yPosition += 150; // Increased the gap to accommodate the image
        }

        // Main page button
        JButton mainButton = new JButton("메인으로");
        mainButton.setBounds(250, yPosition + 20, 150, 30);
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToMainPage();
            }
        });
        panel.add(mainButton);

        // Calculate the preferred height based on the last component's position
        int preferredHeight = yPosition + 100; // You may need to adjust this value based on your layout

        // Set the preferred size for the panel
        panel.setPreferredSize(new Dimension(450, preferredHeight));

        // JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Increase scroll speed
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Set the content pane of the frame to the scroll pane
        setContentPane(scrollPane);

        // Display the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Refresh the UI
    private void refreshUI() {
        getContentPane().removeAll();
        revalidate();
        repaint();
        new MyInterestPage(admin);
    }

    // Navigate to the main page
    private void navigateToMainPage() {
        dispose();
        new MainPage(admin);
    }
}