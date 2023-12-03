package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemDetailPage extends JFrame {

    private final Admin admin;
    private final Product product;

    public ItemDetailPage(Admin admin, Product product, ItemListPage itemListPage) {
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

        // 마이페이지 이미지 버튼
        JButton userButton = createStyledButton("src/Main/resource/user.png");
        menuBar.add(userButton);

        // 비활성화된 투명한 경계선 없애기
        menuBar.setFloatable(false);

        panel.add(menuBar);

        // 제품 메인 사진 420*420
        ImageIcon mainImageIcon = new ImageIcon("src/Main/resource/" + product.getType() + "/" + product.getProductId() + "번.png");
        System.out.println(product.getType() + " " + product.getProductId());
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
