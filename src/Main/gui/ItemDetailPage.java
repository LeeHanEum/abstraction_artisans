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

    public ItemDetailPage(Admin admin, Product product) {
        this.admin = admin;
        this.product = product;

        // 프레임 설정
        setTitle("제품 상세페이지");
        setSize(450, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);

        // 메뉴바 450*50
        JToolBar menuBar = new JToolBar();
        menuBar.setBounds(0, 0, 450, 50);

        // 뒤로가기 이미지 버튼
        JButton backButton = createStyledButton("src/Main/resource/back.png");
        menuBar.add(backButton);

        // 검색창
        JTextField searchField = new JTextField();
        searchField.setBounds(80, 10, 300, 40);
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
        JLabel productNameLabel = new JLabel("제품명: " + product.getName());
        productNameLabel.setBounds(15, 480, 420, 20);
        productNameLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(productNameLabel);

        // 가격 출력
        JLabel priceLabel = new JLabel("가격: " + product.getPrice() + "원");
        priceLabel.setBounds(15, 500, 420, 20);
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(priceLabel);

        // 뒤로가기 버튼 액션
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 뒤로가기 액션 처리
                System.out.println("뒤로가기 버튼이 클릭되었습니다.");
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
        Image resizedButtonImage = buttonIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
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
