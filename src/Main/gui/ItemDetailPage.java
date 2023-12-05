package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.Case;
import Main.mall.Item.Cpu;
import Main.mall.Item.GraphicsCard;
import Main.mall.Item.MainBoard;
import Main.mall.Item.Power;
import Main.mall.Item.Product;
import Main.mall.Item.Ram;
import Main.mall.Item.Storage;
import Main.mall.Login;
import Main.mgr.Manageable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemDetailPage extends JFrame {

    private final Admin admin;
    private final Product product;

    public ItemDetailPage(Admin admin, Product product, ItemListPage itemListPage, String type) {
        this.admin = admin;
        this.product = product;

        // 프레임 설정
        setTitle("제품 상세페이지");
        setSize(450, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        // 메뉴바 450*50
        JToolBar menuBar = new JToolBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.setBounds(0, 0, 450, 50);

        // 뒤로가기 이미지 버튼
        JButton backButton = createStyledButton("src/Main/resource/back.png");
        menuBar.add(backButton);

        // 검색창
        JTextField searchField = new JTextField();
        searchField.setBounds(80, 10, 300, 40);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                searchField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        menuBar.add(searchField);

        // 검색창 이미지 버튼
        JButton searchButton = createStyledButton("src/Main/resource/search.png");
        menuBar.add(searchButton);

        // 장바구니 이미지 버튼
        JButton cartButton = createStyledButton("src/Main/resource/cart.png");
        menuBar.add(cartButton);

        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 장바구니 액션 처리
                System.out.println("장바구니 버튼이 클릭되었습니다.");
                dispose();
                new MyCartPage(admin);
            }
        });

        // 마이페이지 이미지 버튼
        JButton userButton = createStyledButton("src/Main/resource/user.png");
        menuBar.add(userButton);

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 마이페이지 액션 처리
                System.out.println("마이페이지 버튼이 클릭되었습니다.");
                dispose();
                new MyPage(admin);
            }
        });

        // 비활성화된 투명한 경계선 없애기
        menuBar.setFloatable(false);

        panel.add(menuBar);

        // 제품 메인 사진 420*420
        ImageIcon mainImageIcon = new ImageIcon("src/Main/resource/productImage/" + product.getProductId() + "번.png");
        System.out.println(product.getProductId() + "번.png");
        Image resizedImage = mainImageIcon.getImage().getScaledInstance(420, 420, Image.SCALE_SMOOTH);
        ImageIcon resizedMainImageIcon = new ImageIcon(resizedImage);

        JLabel mainPicture = new JLabel(resizedMainImageIcon);
        mainPicture.setBounds(15, 50, 420, 420); // 여백 추가
        mainPicture.setHorizontalAlignment(JLabel.CENTER);
        panel.add(mainPicture);

        // 제품명 출력
        JLabel productNameLabel = new JLabel("<html>" + product.getName() + "</html>");
        productNameLabel.setBounds(15, 480, 420, 40); // 높이를 더 늘려줌
        productNameLabel.setHorizontalAlignment(JLabel.LEFT);
        // 폰트 속성 조정
        Font productNameFont = productNameLabel.getFont();
        productNameLabel.setFont(new Font(productNameFont.getName(), Font.PLAIN, 18)); // 크게 만들기
        productNameLabel.setPreferredSize(new Dimension(420, 40)); // 높이에 맞게 조절
        panel.add(productNameLabel);

// 가격 출력
        JLabel priceLabel = new JLabel(product.getPrice() + "원");
        int productNameHeight = productNameLabel.getPreferredSize().height;
        priceLabel.setBounds(15, 500 + productNameHeight, 430, 20);
        priceLabel.setHorizontalAlignment(JLabel.LEFT); // 오른쪽 정렬로 변경
        Font priceFont = priceLabel.getFont();
        priceLabel.setFont(new Font(priceFont.getName(), Font.BOLD, 16));
        panel.add(priceLabel);

// 오른쪽 정렬된 버튼 패널 생성
        FlowLayout buttonPanelLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel buttonPanel = new JPanel(buttonPanelLayout);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(15, 480 + productNameHeight + 20, 430, 35);

// 공유 버튼
        JButton shareButton = createStyledButton("src/Main/resource/share.png");
        shareButton.setPreferredSize(new Dimension(32, 32));
        shareButton.setIcon(new ImageIcon(new ImageIcon("src/Main/resource/share.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        buttonPanel.add(shareButton);

// 찜 버튼
        JButton wishlistButton = createStyledButton("src/Main/resource/heart.png");
        wishlistButton.setPreferredSize(new Dimension(32, 32));
        wishlistButton.setIcon(new ImageIcon(new ImageIcon("src/Main/resource/heart.png").getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH)));
        buttonPanel.add(wishlistButton);

        panel.add(buttonPanel);

        JLabel productInfoLabel = new JLabel("제품 스펙 정보");
        productInfoLabel.setBounds(15, 530 + productNameHeight, 420, 20);
        productInfoLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(productInfoLabel);

        JLabel specLabel = new JLabel();
        specLabel.setBounds(15, 490 + productNameHeight, 420, 200);
        specLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(specLabel);

        switch (type) {
            case "CPU":
                specLabel.setText("<html>캐시: " + ((Cpu) product).getCache() + "<br>" +
                        "클럭: " + ((Cpu) product).getClock() + "<br>" +
                        "코어: " + ((Cpu) product).getCore() + "<br>" +
                        "RAM 타입: " + ((Cpu) product).getRamType() + "<br>" +
                        "소켓: " + ((Cpu) product).getSocket() + "</html>");
                break;
            case "Mainboard":
                specLabel.setText("<html>보드 타입: " + ((MainBoard) product).getBoardType() + "<br>" +
                        "RAM 타입: " + ((MainBoard) product).getRamType() + "<br>" +
                        "소켓: " + ((MainBoard) product).getSocket() + "</html>");
                break;
            case "RAM":
                specLabel.setText("<html>용량: " + ((Ram) product).getCapacity() + "<br>" +
                        "RAM 타입: " + ((Ram) product).getRamType() + "<br>" +
                        "속도: " + ((Ram) product).getSpeed() + "</html>");
                break;
            case "Graphics Card":
                specLabel.setText("<html>베이스 클럭: " + ((GraphicsCard) product).getBaseClock() + "<br>" +
                        "메모리 용량: " + ((GraphicsCard) product).getMemoryCapacity() + "<br>" +
                        "출력 단자: " + ((GraphicsCard) product).getOutputTerminal() + "<br>" +
                        "파워: " + ((GraphicsCard) product).getPower() + "</html>");
                break;
            case "Storage":
                specLabel.setText("<html>용량: " + ((Storage) product).getCapacity() + "<br>" +
                        "평균 속도: " + ((Storage) product).getSpeedAvg() + "</html>");
                break;
            case "Power":
                specLabel.setText("<html>파워: " + ((Power) product).getPower() + "</html>");
                break;
            case "Case":
                specLabel.setText("<html>색상: " + ((Case) product).getColor() + "<br>" +
                        "보드 타입: " + ((Case) product).getBoardType() + "</html>");
                break;
            default:
                specLabel.setText("스펙 정보 없음");
                break;
        }
        // 장바구니 담기 버튼
        JButton addToCartButton = new JButton("장바구니 담기");
        addToCartButton.setBounds(20, 690, 180, 60); // 크기 조절
        addToCartButton.setOpaque(true);
        addToCartButton.setBackground(new Color(64, 64, 64));
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setFocusPainted(true);
        addToCartButton.setBorderPainted(false);

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 장바구니 담기 액션 처리
                Login.currentUser.getCart().addItemToCart(product);
                JOptionPane.showMessageDialog(null, "상품이 장바구니에 담겼습니다.");
            }
        });

        panel.add(addToCartButton);

// 바로 구매 버튼
        JButton buyNowButton = new JButton("바로 구매");
        buyNowButton.setBounds(250, 690, 180, 60); // 크기 조절
        buyNowButton.setOpaque(true);
        buyNowButton.setBackground(new Color(64, 64, 64));
        buyNowButton.setForeground(Color.WHITE);
        buyNowButton.setFocusPainted(true);
        buyNowButton.setBorderPainted(false);

        buyNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 바로 구매 액션 처리
                System.out.println("바로 구매 버튼이 클릭되었습니다.");
            }
        });

        panel.add(buyNowButton);

        // 뒤로가기 버튼 액션
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 뒤로가기 액션 처리
                System.out.println("뒤로가기 버튼이 클릭되었습니다.");
                dispose();
                itemListPage.setVisible(true);
            }
        });



        // 검색 버튼 액션
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 검색 액션 처리
                System.out.println("검색 버튼이 클릭되었습니다. 입력된 검색어: " + searchField.getText());
            }
        });

        // 프레임에 패널 추가
        add(panel);

        // 프레임 표시
        setVisible(true);
    }


    // 이미지 버튼 스타일 적용 메소드
    private JButton createStyledButton(String imagePath) {
        ImageIcon buttonIcon = new ImageIcon(imagePath);
        Image resizedButtonImage = buttonIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(resizedButtonImage));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        // 마우스를 올렸을 때 효과 추가
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setContentAreaFilled(true);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setContentAreaFilled(false);
            }
        });

        return button;
    }

}
