package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.Product;
import Main.mall.Cart;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ShoppingListPage extends JFrame {

    private final Cart cart;
    private final Admin admin;
    private final JPanel contentPanel;
    private int totalPrice = 0; // 총 가격을 저장할 변수
    private final Map<Product, Integer> productQuantityMap = new HashMap<>(); // 변경된 부분
    private final Map<Product, JTextField> productQuantityTextFieldMap = new HashMap<>();

    public ShoppingListPage(Admin admin) {
        this.cart = admin.getCart();
        this.admin = admin;

        setTitle("모두나와 PC - 장바구니");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800); // 필요에 따라 초기 크기 조정

        JPanel cartPanel = new JPanel();
        cartPanel.setBackground(new Color(240, 240, 240));
        cartPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("<html><span style='font-size:15px; color:#282828;'>장바구니</span></html>");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // 레이아웃 변경 후 위치 변경
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.anchor = GridBagConstraints.NORTHWEST;

        cartPanel.add(titleLabel, titleConstraints);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        customizeScrollBar(scrollPane);

        // 레이아웃 변경 후 위치 변경
        GridBagConstraints scrollConstraints = new GridBagConstraints();
        scrollConstraints.gridx = 0;
        scrollConstraints.gridy = 1;
        scrollConstraints.fill = GridBagConstraints.BOTH;
        scrollConstraints.weightx = 1.0;
        scrollConstraints.weighty = 1.0;

        cartPanel.add(scrollPane, scrollConstraints);

        JButton backButton = new JButton("마이페이지");
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        backButton.setBackground(new Color(190, 190, 190));
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(e -> navigateToMainPage());
        backButton.setBorderPainted(false);

        // 레이아웃 변경 후 백버튼 위치변경
        GridBagConstraints backConstraints = new GridBagConstraints();
        backConstraints.gridx = 0;
        backConstraints.gridy = 0;
        backConstraints.anchor = GridBagConstraints.NORTHEAST;

        if (productQuantityMap.isEmpty() && productQuantityTextFieldMap.isEmpty()) {
            initializeProductQuantityMaps();
        }

        cartPanel.add(backButton, backConstraints);
        backButton.setPreferredSize(new Dimension(120, 30));

        add(cartPanel);
        setLocationRelativeTo(null);
        setVisible(true);

        createProductPanels();
    }
    private void initializeProductQuantityMaps() {
        List<Product> productList = cart.getProductList();

        for (Product product : productList) {
            if (!productQuantityMap.containsKey(product)) {
                productQuantityMap.put(product, 1);  // Set default quantity to 1
                createProductPanel(product);
            }
        }
    }
    private void createProductPanels() {
        List<Product> productList = cart.getProductList();

        // 업데이트된 목록을 기반으로 제품 패널 생성
        for (Product product : productList) {
            // 이미 담겨있으면 ltemlistpage에 있는 장바구니 추가에서 이미 담겨 있는 상품이라고 알려주고 추가 안해줌
            if (!productQuantityMap.containsKey(product)) {
                createProductPanel(product);
            }
        }

        updateTotalPriceLabel(); // 각 제품에 대한 총 가격 라벨 업데이트

        // 변경 사항을 반영하기 위해 다시 유효성 검사 및 다시 그림
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

        // 이미지 불러오기
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
        textPanel.setPreferredSize(new Dimension(345, 80)); // 필요에 따라 폭 조정

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        nameLabel.setForeground(Color.BLACK);

        JLabel priceLabel = new JLabel("가격: " + product.getPrice() + "원");
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        priceLabel.setForeground(new Color(40, 40, 40));

        // 버튼들
        JPanel quantityPanel = new JPanel(); // 수량과 버튼들을 포함하는 패널
        quantityPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // 왼쪽 정렬

        quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.X_AXIS));

        JLabel quantityLabel = new JLabel("수량");

        JTextField quantityTextField = new JTextField(String.valueOf(productQuantityMap.getOrDefault(product, 1)), 1); // 변경된 부분
        productQuantityMap.put(product, Integer.parseInt(quantityTextField.getText()));
        productQuantityTextFieldMap.put(product, quantityTextField);

        quantityTextField.setPreferredSize(new Dimension(30, 20)); // 필요에 따라 너비와 높이 조정

        JButton increaseButton = new JButton("+");
        JButton decreaseButton = new JButton("-");
        JButton deleteButton = new JButton("삭제");
        JCheckBox checkBox = new JCheckBox(); // 체크박스 생성
        //체크박스 상태보고 바꿔주기
        checkBox.setSelected(productQuantityMap.getOrDefault(product, 1) != 0); // 변경된 부분
        checkBox.addActionListener(e -> {
            // 체크박스가 어떤 상태인지 보고 값 설정
            boolean isChecked = checkBox.isSelected();
            System.out.println("Checkbox state: " + isChecked);
            //체크박스 눌러서 상태가 false가 되면 quantity 값 0으로 설정
            int quantity = isChecked ? Integer.parseInt(quantityTextField.getText()) : 0;
            productQuantityMap.put(product, quantity);

            // 총 가격 다시 설정
            updateQuantityAndTotalPrice(product, quantity, isChecked);
        });

        int initialQuantity = Integer.parseInt(quantityTextField.getText());
        int initialTotalPrice = product.getPrice() * initialQuantity;
        totalPrice += initialTotalPrice;

        // quantityPanel에 컴포넌트들을 추가
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantityTextField);
        quantityPanel.add(increaseButton);
        quantityPanel.add(decreaseButton);
        quantityPanel.add(deleteButton);
        quantityPanel.add(Box.createHorizontalGlue()); // 간격 조정을 위해 수평 glue 추가
        quantityPanel.add(checkBox); // 체크박스 추가

        // quantityPanel의 크기를 조정
        quantityPanel.setPreferredSize(new Dimension(250, 30));
        // 컴포넌트들의 여백을 조정
        quantityPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // 수량 조절
        increaseButton.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityTextField.getText());
            quantity++;
            System.out.println("Increased quantity for product " + product.getName() + " to: " + quantity);

            // Update quantity for the current product
            productQuantityMap.put(product, quantity);

            // Update total price and GUI
            updateQuantityAndTotalPrice(product, quantity, checkBox.isSelected());
            quantityTextField.setText(String.valueOf(quantity));
            productQuantityTextFieldMap.put(product, quantityTextField);
        });

        // decreaseButton의 action listener 내부
        decreaseButton.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityTextField.getText());
            if (quantity > 1) {
                quantity--;
                System.out.println("Decreased quantity for product " + product.getName() + " to: " + quantity);

                // Update quantity for the current product
                productQuantityMap.put(product, quantity);

                // Update total price and GUI
                updateQuantityAndTotalPrice(product, quantity, checkBox.isSelected());
                quantityTextField.setText(String.valueOf(quantity));
                productQuantityTextFieldMap.put(product, quantityTextField);
            }
        });

        // 삭제 버튼
        deleteButton.setBackground(new Color(255, 0, 0));
        deleteButton.setForeground(new Color(255, 255, 255));
        deleteButton.addActionListener(e -> deleteItemFromCart(product));
        deleteButton.setFocusPainted(false); // 버튼 포커스 테두리 제거

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

        // 변경 사항을 반영하기 위해 다시 유효성 검사 및 다시 그림
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // +, - 버튼 누르고 총 가격 업데이트
    private void updateQuantityAndTotalPrice(Product product, int quantity, boolean isChecked) {
        // 장바구니에서 수량 업데이트
        cart.editQuantityFromCart(product.getProductId(), quantity);

        // 모든 상품에 대한 가격의 합 다시 계산
        recalculateTotalPrice();

        // 쇼핑 목록 페이지 업데이트
        updateShoppingListPage();
        // 총 가격 라벨 업데이트
        updateTotalPriceLabel();
        JTextField quantityTextField = productQuantityTextFieldMap.get(product);
        quantityTextField.setText(String.valueOf(quantity));
    }

    // 총 가격 다시 계산
    private void recalculateTotalPrice() {
        totalPrice = 0;
        for (Product product : cart.getProductList()) {
            int retotal = 0;
            retotal -= totalPrice;
            retotal += product.getPrice();
        }
       // System.out.println("Recalculated total price: " + );
    }

    // 총 가격 라벨
    private void updateTotalPriceLabel() {
        JLabel totalPriceLabel = new JLabel("총 가격: " + totalPrice + "원");
        totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        totalPriceLabel.setForeground(Color.BLACK);

        // 기존 총 가격 라벨을 업데이트된 라벨로 교체
        contentPanel.add(totalPriceLabel, contentPanel.getComponentCount() - 1);
    }

    // 장바구니에서 항목 삭제
    private void deleteItemFromCart(Product product) {
        Iterator<Product> iterator = cart.getProductList().iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getProductId() == product.getProductId()) {
                iterator.remove();
                totalPrice = 0;
                updateShoppingListPage();
                updateTotalPriceLabel();

                return;  // 각 제품 ID가 고유하다고 가정; 그렇지 않으면 return 문 제거
            }
        }
    }

    // 페이지 업데이트
    private void updateShoppingListPage() {
        List<Product> productList = cart.getProductList();
        // content panel 비우기
        contentPanel.removeAll();

        // 업데이트된 목록을 기반으로 제품 패널 생성
        if (productList.isEmpty()) {
            // 장바구니가 비어 있으면 메시지 표시
            JLabel emptyCartLabel = new JLabel("장바구니가 비었습니다.");
            emptyCartLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
            emptyCartLabel.setForeground(Color.BLACK);
            contentPanel.add(emptyCartLabel);
        } else {
            // 업데이트된 목록을 기반으로 제품 패널 생성
            for (Product product : productList) {
                createProductPanel(product);
            }
        }

        // 변경 사항을 반영하기 위해 다시 유효성 검사 및 다시 그림
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // 스크롤바 커스터마이즈
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

    // 마이페이지로 이동
    private void navigateToMainPage() {
        // 현재 창 닫기
        dispose();
        // 메인 페이지 열기
        new MyPage(admin);
    }
}