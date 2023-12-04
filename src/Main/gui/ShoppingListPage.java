package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.Product;
import Main.mall.Cart;

import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ShoppingListPage extends JFrame {

    private final Cart cart;
    private final Admin admin;
    private final JPanel contentPanel;
    private int totalPrice = 0; // 총 가격을 저장할 변수
    private JTextField quantityTextField;
    public ShoppingListPage(Admin admin) {
        this.cart = admin.getCart();
        this.admin = admin;

        setTitle("모두나와 PC - 장바구니");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800); // Adjust the initial size as needed

        JPanel cartPanel = new JPanel();
        cartPanel.setBackground(new Color(240, 240, 240));
        cartPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("<html><span style='font-size:15px; color:#282828;'>장바구니</span></html>");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        cartPanel.add(titleLabel, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        customizeScrollBar(scrollPane);
        cartPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("마이페이지");
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        backButton.setBackground(new Color(190, 190, 190));
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(e -> navigateToMainPage());
        backButton.setBorderPainted(false);
        cartPanel.add(backButton, BorderLayout.SOUTH);

        add(cartPanel);
        setLocationRelativeTo(null);
        setVisible(true);

        createProductPanels();
    }

    private void createProductPanels() {
        List<Product> productList = cart.getProductList();

        // Create product panels based on the updated list
        for (Product product : productList) {
            System.out.println("Creating panel for product: " + product.getName());
            createProductPanel(product);
        }
        updateTotalPriceLabel(); // 총 가격을 표시하는 레이블 추가
        // Revalidate and repaint to reflect the changes
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void createProductPanel(Product product) {
        JPanel productPanel = new JPanel();
        // FlowLayout의 여백을 없애고 왼쪽 정렬로 설정
        productPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // productPanel의 크기를 조정
        productPanel.setPreferredSize(new Dimension(370, 80));

        // productPanel의 배경색 변경
        productPanel.setBackground(new Color(230, 230, 230));

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(80, 80));

        //이미지불러오기
        ImageIcon imageIcon = new ImageIcon("src/Main/resource/productImage/" + product.getProductId() + "번.png");
        try {
            Image resizedImage = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            ImageIcon resizedMainImageIcon = new ImageIcon(resizedImage);
            imageLabel.setIcon(resizedMainImageIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        // 텍스트패널 크기 조정
        textPanel.setPreferredSize(new Dimension(350, 80)); // Adjust the width as needed

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        nameLabel.setForeground(Color.BLACK);

        JLabel priceLabel = new JLabel("가격: " + product.getPrice() + "원");
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        priceLabel.setForeground(new Color(40, 40, 40));

        //버튼들
        JPanel quantityPanel = new JPanel(); // 수량과 버튼들을 포함하는 패널
        quantityPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // 왼쪽 정렬

        quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.X_AXIS));

        JLabel quantityLabel = new JLabel("수량");
        JTextField quantityTextField = new JTextField("1", 1);

        quantityTextField.setPreferredSize(new Dimension(30, 20)); // Adjust width and height as needed

        JButton increaseButton = new JButton("+");
        JButton decreaseButton = new JButton("-");
        JButton deleteButton = new JButton("삭제");
        JCheckBox checkBox = new JCheckBox(); // Create a checkbox

        int initialQuantity = Integer.parseInt(quantityTextField.getText());
        int initialTotalPrice = product.getPrice() * initialQuantity;
        totalPrice += initialTotalPrice;

        // quantityPanel에 컴포넌트들을 추가
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantityTextField);
        quantityPanel.add(increaseButton);
        quantityPanel.add(decreaseButton);
        quantityPanel.add(deleteButton);
        quantityPanel.add(Box.createHorizontalGlue()); // Add horizontal glue for spacing
        quantityPanel.add(checkBox); // Add the checkbox

        // quantityPanel의 크기를 조정
        quantityPanel.setPreferredSize(new Dimension(250, 30));
        // 컴포넌트들의 여백을 조정
        quantityPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //수량조절
        increaseButton.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityTextField.getText());
            quantity++;
            quantityTextField.setText(String.valueOf(quantity));
            updateQuantityAndTotalPrice(product, quantity);

        });

        decreaseButton.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityTextField.getText());
            if (quantity > 1) {
                quantity--;
                quantityTextField.setText(String.valueOf(quantity));
                updateQuantityAndTotalPrice(product, quantity);
            }
            });

        //삭제버튼
        deleteButton.setBackground(new Color(255, 0, 0));
        deleteButton.setForeground(new Color(255, 255, 255));
        deleteButton.addActionListener(e -> deleteItemFromCart(product));
        deleteButton.setFocusPainted(false);  // Remove button focus border

        textPanel.add(nameLabel);
        textPanel.add(priceLabel);
        textPanel.add(quantityPanel);

        productPanel.add(imageLabel);
        productPanel.add(textPanel);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // productPanel의 여백을 조정
        productPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // productPanel을 contentPanel에 추가
        contentPanel.add(productPanel);
        contentPanel.add(Box.createVerticalStrut(10)); // 수직 여백 추가

        // Revalidate and repaint to reflect the changes
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    //+,- 버튼 누르고 총 가격 업데이트
    private void updateQuantityAndTotalPrice(Product product, int quantity) {
        // Get the current total price of the product in the cart
        int currentTotalPrice = product.getPrice() * Integer.parseInt(quantityTextField.getText());

        // Update the quantity in the cart
        cart.editQuantityFromCart(product.getProductId(), quantity);

        // Calculate the new total price
        int newTotalPrice = product.getPrice() * quantity;

        // Update the overall total price
        totalPrice += (newTotalPrice - currentTotalPrice);

        // Update the total price label
        updateTotalPriceLabel();

        // Update the shopping list page
        updateShoppingListPage();
    }
    //총가격 라벨
    private void updateTotalPriceLabel() {
        JLabel totalPriceLabel = new JLabel("총 가격: " + totalPrice + "원");
        totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        totalPriceLabel.setForeground(Color.BLACK);

        // Replace the existing total price label with the updated one
        contentPanel.add(totalPriceLabel, contentPanel.getComponentCount() - 1);
    }
    //장바구니 삭제
    private void deleteItemFromCart(Product product) {
        Iterator<Product> iterator = cart.getProductList().iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getProductId() == product.getProductId()) {
                iterator.remove();
                totalPrice = 0;
                updateShoppingListPage();
                updateTotalPriceLabel();

                return;  // Assuming each product ID is unique; if not, remove the return statement
            }
        }
    }
    //페이지 update하기
    private void updateShoppingListPage() {
        List<Product> productList = cart.getProductList();

        // Clear the content panel
        contentPanel.removeAll();

        // Create product panels based on the updated list
        if (productList.isEmpty()) {
            // If the cart is empty, display a message
            JLabel emptyCartLabel = new JLabel("장바구니가 비었습니다.");
            emptyCartLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
            emptyCartLabel.setForeground(Color.BLACK);
            contentPanel.add(emptyCartLabel);
        } else {
            // Create product panels based on the updated list
            for (Product product : productList) {
                System.out.println("Creating panel for product: " + product.getName());
                createProductPanel(product);
            }
        }

        // Revalidate and repaint to reflect the changes
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    //스크롤바 커스터마이즈
    private void customizeScrollBar(JScrollPane scrollPane) {
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(130, 130, 130);
                this.trackColor = new Color(200, 200, 200);
            }
        });
    }
    //마이페이지로
    private void navigateToMainPage() {
        // Close the current window
        dispose();

        // Open the main page
        new MyPage(admin);
    }
}
