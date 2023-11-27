package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.Product;
import Main.mgr.Manageable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemListPage extends JFrame {
    private JPanel sandbox2;
    private JTextField searchField;

    public ItemListPage(Admin admin) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800);
        setLayout(null);

        JPanel userPagePanel = new JPanel();
        userPagePanel.setBackground(new Color(240, 240, 240));
        userPagePanel.setBounds(0, 0, 450, 800);
        userPagePanel.setLayout(null);

        JLabel titleLabel = new JLabel("제품리스트");
        titleLabel.setBounds(30, 40, 300, 30);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 30));
        titleLabel.setForeground(new Color(52, 152, 219));
        userPagePanel.add(titleLabel);

        sandbox2 = new JPanel();
        JScrollPane scrollPane = new JScrollPane(sandbox2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBounds(30, 110, 390, 610);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); //스크롤 속도 조정

        sandbox2.setLayout(new BoxLayout(sandbox2, BoxLayout.Y_AXIS));
        sandbox2.setBackground(new Color(100, 100, 100));
        sandbox2.setBounds(0, 0, 390, 610);

        searchField = new JTextField();
        searchField.setBounds(30, 80, 300, 20);
        searchField.setBackground(new Color(230, 230, 230));

        JButton searchButton = new JButton("<html><center>🔍 검색</center></html>");
        searchButton.setBounds(30 + 300 + 5, 80, 75, 20);
        searchButton.setHorizontalAlignment(SwingConstants.CENTER);
        searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        searchButton.setBackground(new Color(52, 152, 219));
        searchButton.setForeground(Color.WHITE);

        userPagePanel.add(searchField);
        userPagePanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch(admin);
            }
        });

        sandbox2.add(Box.createRigidArea(new Dimension(0, 5))); // Adjust spacing

        for (Manageable a : admin.getAllItem()) {
            createGroupPanel(sandbox2, (Product) a);
            sandbox2.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        userPagePanel.add(scrollPane);
        add(userPagePanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createGroupPanel(JPanel parentPanel, Product product) {
        JPanel groupPanel = new JPanel();
        groupPanel.setLayout(new BorderLayout());
        groupPanel.setPreferredSize(new Dimension(360, 100));
        groupPanel.setBackground(new Color(230, 230, 230));

        Box verticalBox = Box.createVerticalBox();

        JLabel nameLabel = new JLabel("제품명: " + product.getName());
        nameLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        nameLabel.setForeground(Color.BLACK);

        JLabel priceLabel = new JLabel("가격: " + product.getPrice() + "원");
        priceLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        priceLabel.setForeground(Color.BLACK);

        JButton detailsButton = new JButton("상세정보");
        detailsButton.setBounds(10, 60, 120, 30);
        detailsButton.setBackground(new Color(52, 152, 219));
        detailsButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        detailsButton.setForeground(Color.WHITE);

        verticalBox.add(nameLabel);
        verticalBox.add(priceLabel);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 22)));
        verticalBox.add(detailsButton);

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayProductDetails(product);
            }
        });
        groupPanel.add(verticalBox, BorderLayout.CENTER);

        parentPanel.add(groupPanel);
    }

    private void performSearch(Admin admin) {
        String searchTerm = searchField.getText().trim().toLowerCase();

        if (searchTerm.isEmpty()) {
            refreshItemList(admin);
        } else {
            sandbox2.removeAll();
            sandbox2.revalidate();
            sandbox2.repaint();

            for (Manageable a : admin.getAllItem()) {
                String itemName = ((Product) a).getName().toLowerCase();

                if (itemName.contains(searchTerm)) {
                    createGroupPanel(sandbox2, (Product) a);
                    sandbox2.add(Box.createRigidArea(new Dimension(0, 5)));
                }
            }

            sandbox2.add(Box.createVerticalGlue());
        }
    }

    private void refreshItemList(Admin admin) {
        sandbox2.removeAll();
        sandbox2.revalidate();
        sandbox2.repaint();

        for (Manageable a : admin.getAllItem()) {
            createGroupPanel(sandbox2, (Product) a);
            sandbox2.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        sandbox2.add(Box.createVerticalGlue());
    }

    private void displayProductDetails(Product product) {
        // 새로운 다이얼로그 생성
        JDialog dialog = new JDialog(this, "상세정보", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(450, 200);
        dialog.setLocationRelativeTo(this);

        // 상세정보 텍스트 생성
        JTextArea detailsTextArea = new JTextArea("상세정보\n" + product);
        detailsTextArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        detailsTextArea.setBackground(new Color(230, 230, 230));

        // 텍스트 영역을 스크롤 가능하도록 스크롤 패널에 추가
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // 닫기 버튼 추가
        JButton closeButton = new JButton("닫기");
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // 다이얼로그를 표시
        dialog.setVisible(true);
    }

}