package Main.gui;

import Main.mall.Admin;
import Main.mall.Item.Product;
import Main.mgr.Manageable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemListPage extends JFrame {
    private final JPanel componentPanel;
    private JPanel productPanel;
    private final Admin admin;
    private final JPanel userPagePanel;
    private boolean isUserPagePanelShown = true;
    private int index = 0;
    private final Map<String, Integer> productIndices = new HashMap<>();

    public ItemListPage(Admin admin) {
        this.admin = admin;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800);
        setLayout(new BorderLayout());

        userPagePanel = new JPanel();
        userPagePanel.setBackground(new Color(240, 240, 240));
        userPagePanel.setLayout(new BorderLayout());
        userPagePanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel pcAndTitleLabel = new JLabel("<html><span style='font-size:15px; color:#008000'>모두나와PC</span><br/>"
            + "<span style='font-size:35px; color:#282828;'>제품리스트</span></html>");

        // 여러 경계선 및 여백 조정
        pcAndTitleLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(200, 200, 200)), // 밑에 subtile 경계선
            BorderFactory.createEmptyBorder(10, 20, 20, 20))); // 여백 조정

        userPagePanel.add(pcAndTitleLabel, BorderLayout.NORTH);

        componentPanel = new JPanel(new GridLayout(0, 1, 10, 10)); // Adjust as needed
        componentPanel.setBackground(new Color(255, 255, 255));

        // 각 부품 버튼과 화살표를 추가
        addButton("CPU");
        addButton("Case");
        addButton("Mainboard");
        addButton("RAM");
        addButton("Power");
        addButton("Graphics Card");
        addButton("Storage");

        userPagePanel.add(componentPanel, BorderLayout.CENTER);

        add(userPagePanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButton(String componentName) {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        buttonPanel.setPreferredSize(new Dimension(200, 90));

        JButton componentButton = new JButton("<html><center>" + componentName + "</center></html>");
        componentButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        componentButton.setBackground(new Color(255, 255, 255));
        componentButton.setForeground(new Color(40, 40, 40));
        componentButton.setBorderPainted(false);
        componentButton.setFocusPainted(false);
        componentButton.addActionListener(e -> showProductList(componentName));

        // 버튼에 그림자 효과 추가
        componentButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(240, 240, 240), 5),
            BorderFactory.createLineBorder(new Color(240, 240, 240), 5)));

        // 레이아웃 매니저를 사용하여 가운데 정렬
        buttonPanel.add(componentButton, BorderLayout.CENTER);

        JButton arrowButton = new JButton("▶");
        arrowButton.addActionListener(e -> showArrowButtonAction(componentName));
        arrowButton.setBorderPainted(false);
        arrowButton.setBackground(new Color(255, 255, 255));

        arrowButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(240, 240, 240), 5),
            BorderFactory.createLineBorder(new Color(240, 240, 240), 5)));

        // 마우스 호버 효과 추가
        componentButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                componentButton.setBackground(new Color(230, 230, 230));
                arrowButton.setBackground(new Color(230,230,230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                componentButton.setBackground(new Color(255, 255, 255));
                arrowButton.setBackground(new Color(255,255,255));
            }
        });
        arrowButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                componentButton.setBackground(new Color(230, 230, 230));
                arrowButton.setBackground(new Color(230,230,230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                componentButton.setBackground(new Color(255, 255, 255));
                arrowButton.setBackground(new Color(255,255,255));
            }
        });

        // 레이아웃 매니저를 사용하여 오른쪽 정렬
        buttonPanel.add(arrowButton, BorderLayout.EAST);

        componentPanel.add(buttonPanel);
        if (componentPanel.getComponentCount() > 0) {
            int thickness = 2; // 줄의 두께
            Color color = Color.BLACK; // 줄의 색상
            MatteBorder matteBorder = new MatteBorder(0, 0, thickness, 0, color);
            buttonPanel.setBorder(BorderFactory.createCompoundBorder(buttonPanel.getBorder(), matteBorder));
        }
    }

    private void showArrowButtonAction(String componentName) {
        showProductList(componentName);
    }


    private void showProductList(String componentName) {
        int localIndex = index; // Store the current index locally
        ArrayList<Manageable> productList;

        // Get the product list based on the selected component category
        switch (componentName) {
            case "CPU":
                productList = admin.getCpu();
                break;
            case "Case":
                productList = admin.getCase();
                break;
            case "Mainboard":
                productList = admin.getMainboard();
                break;
            case "RAM":
                productList = admin.getRam();
                break;
            case "Power":
                productList = admin.getPower();
                break;
            case "Graphics Card":
                productList = admin.getGraphicscard();
                break;
            case "Storage":
                productList = admin.getStorage();
                break;
            default:
                productList = new ArrayList<>();
        }

        productIndices.clear();
        for (int i = 0; i < productList.size(); i++) {
            Manageable product = productList.get(i);
            productIndices.put(((Product) product).getName().toLowerCase(), i);
        }
        productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        JPanel currentComponentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel componentNameLabel = new JLabel("현재 리스트된 부품 : " + componentName);
        componentNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        currentComponentPanel.add(componentNameLabel);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(25);
        searchField.setPreferredSize(new Dimension(searchField.getPreferredSize().width, 30));
        JButton searchButton = new JButton("검색");

        searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        searchButton.setBackground(new Color(190, 190, 190));
        searchButton.setForeground(Color.BLACK);

        searchButton.addActionListener(e -> performComponentSearch(componentName, searchField.getText().trim().toLowerCase()));

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JButton backButton = new JButton("←");
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        backButton.setBackground(new Color(190, 190, 190));
        backButton.setForeground(Color.BLACK);

        backButton.addActionListener(e -> showComponentPanel());
        searchPanel.add(backButton);

        productPanel.add(currentComponentPanel);
        productPanel.add(searchPanel);

        for (Manageable product : productList) {
            createProductPanel(productPanel, (Product) product, componentName);
        }

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(productPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        customizeScrollBar(scrollPane);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        getContentPane().removeAll();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
        index = localIndex;
    }

    private void performSearch(ArrayList<Manageable> itemList, String searchTerm, String componentName) {
        if (searchTerm.isEmpty()) {
            refreshItemList(admin, componentName);
        } else {
            // Create a new JFrame for search results
            JFrame searchResultsFrame = new JFrame("검색된 결과");
            searchResultsFrame.setSize(450, 800);
            searchResultsFrame.setLayout(new BorderLayout());

            productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

            for (Manageable product : itemList) {
                String itemName = ((Product) product).getName().toLowerCase();

                if (itemName.contains(searchTerm.toLowerCase())) {
                    createProductPanel(productPanel, (Product) product, componentName);
                    productPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                }
            }
            productPanel.add(Box.createVerticalGlue());

            JScrollPane scrollPane = new JScrollPane(productPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(30); // 스크롤 속도 증가
            customizeScrollBar(scrollPane);

            searchResultsFrame.add(scrollPane);
            searchResultsFrame.setLocationRelativeTo(this);
            searchResultsFrame.setVisible(true);
        }
    }

    private void showComponentPanel() {
        getContentPane().removeAll();
        getContentPane().add(userPagePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        isUserPagePanelShown = true;
    }

    private void createProductPanel(JPanel parentPanel, Product product, String componentName) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.X_AXIS));
        productPanel.setPreferredSize(new Dimension(370, 100));
        productPanel.setBackground(new Color(230, 230, 230));

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(80, 80));

        ImageIcon imageIcon = loadImage(componentName, product.getName().toLowerCase());
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

        detailsButton.addActionListener(e -> displayProductDetails(product));

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

    private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    private void displayProductDetails(Product product) {
        //주석처리한거 바꿔주시면 돼요
        // new ProductDetailsDialog(this, product);
    }

    private void refreshItemList(Admin admin, String componentName) {
        componentPanel.removeAll();
        componentPanel.revalidate();
        componentPanel.repaint();

        for (Manageable a : admin.getAllItem()) {
            createProductPanel(componentPanel, (Product) a, componentName);
            componentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        componentPanel.add(Box.createVerticalGlue());
    }

    private ImageIcon loadImage(String componentName, String productName) {
        ArrayList<String> imagePathList = getImagePathList(componentName);
        int productIndex = productIndices.getOrDefault(productName, 0);

        if (!imagePathList.isEmpty() && productIndex < imagePathList.size()) {
            String imagePath = imagePathList.get(productIndex);
            return resizeImage(new ImageIcon(imagePath), 80, 80);
        }

        return null;
    }

    private void performComponentSearch(String componentName, String searchTerm) {
        ArrayList<Manageable> productList;

        switch (componentName) {
            case "CPU":
                productList = admin.getCpu();
                break;
            case "Case":
                productList = admin.getCase();
                break;
            case "Mainboard":
                productList = admin.getMainboard();
                break;
            case "RAM":
                productList = admin.getRam();
                break;
            case "Power":
                productList = admin.getPower();
                break;
            case "Graphics Card":
                productList = admin.getGraphicscard();
                break;
            case "Storage":
                productList = admin.getStorage();
                break;
            default:
                productList = new ArrayList<>();
        }

        performSearch(productList, searchTerm, componentName);
    }

    private void customizeScrollBar(JScrollPane scrollPane) {
        customizeVerticalScrollBar(scrollPane.getVerticalScrollBar());
    }

    private void customizeVerticalScrollBar(JScrollBar verticalScrollBar) {
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(130, 130, 130);
                this.trackColor = new Color(200, 200, 200);
            }
        });
    }

    private ArrayList<String> getImagePathList(String componentName) {
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