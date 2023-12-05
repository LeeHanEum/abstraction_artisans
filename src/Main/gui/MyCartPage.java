package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.Product;
import Main.mall.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MyCartPage extends JFrame {

    private final Admin admin;

    public MyCartPage(Admin admin) {
        this.admin = admin;

        setTitle("내 장바구니");
        setSize(450, 800); // Adjusted size for better visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for product list
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        // Get the product list from the user's cart
        List<Product> productList = Login.currentUser.getCart().getProductList();

        // Header
        JLabel headerLabel = new JLabel("장바구니");
        headerLabel.setBounds(20, 20, 200, 30);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(headerLabel);

        // Vertical position for each product
        int yPosition = 70;

        // Display each product in the cart
        for (Product product : productList) {
            // Display product image on the left
            JLabel imageLabel = new JLabel();
            imageLabel.setBounds(50, yPosition, 100, 100);
            ImageIcon imageIcon = new ImageIcon("src/Main/resource/productImage/" + product.getProductId() + "번.png");
            Image resizedImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon resizedMainImageIcon = new ImageIcon(resizedImage);
            imageLabel.setIcon(resizedMainImageIcon);
            panel.add(imageLabel);

            // Display product details
            JLabel nameLabel = new JLabel(product.getName());
            nameLabel.setBounds(200, yPosition, 200, 20);
            panel.add(nameLabel);

            JLabel priceLabel = new JLabel("가격: $" + product.getPrice());
            priceLabel.setBounds(200, yPosition + 20, 200, 20);
            panel.add(priceLabel);

            JLabel quantityLabel = new JLabel("수량: ");
            quantityLabel.setBounds(200, yPosition + 40, 50, 20);
            panel.add(quantityLabel);

            JTextField quantityField = new JTextField(String.valueOf(1));
            quantityField.setBounds(250, yPosition + 40, 50, 20);
            panel.add(quantityField);

            JButton removeButton = new JButton("삭제");
            removeButton.setBounds(350, yPosition + 40, 80, 20);
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Remove button clicked
                    Login.currentUser.getCart().deleteItemFromCart(product.getProductId());
                    refreshUI();
                }
            });
            panel.add(removeButton);

            yPosition += 150; // Increased the gap to accommodate the image
        }

        // Checkout button
        JButton checkoutButton = new JButton("결제하기");
        checkoutButton.setBounds(50, yPosition + 20, 150, 30);
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Additional checkout logic can be added here
                JOptionPane.showMessageDialog(panel, "결제가 완료되었습니다!", "결제 완료", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(checkoutButton);

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
        new MyCartPage(admin);
    }

    // Navigate to the main page
    private void navigateToMainPage() {
        dispose();
        new MainPage(admin);
    }

}
