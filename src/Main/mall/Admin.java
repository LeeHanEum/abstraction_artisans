package Main.mall;

import Main.mall.Item.*;
import Main.mgr.Factory;
import Main.mgr.Manageable;
import Main.mgr.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Admin extends JFrame {

    private Scanner scan = new Scanner(System.in);
    private static Login loginMgr = new Login();

    public static Manager userMgr = new Manager();
    public static Manager cpuMgr = new Manager();
    public static Manager mainboardMgr = new Manager();
    public static Manager ramMgr = new Manager();
    public static Manager storageMgr = new Manager();
    public static Manager graphicsMgr = new Manager();
    public static Manager powerMgr = new Manager();
    public static Manager caseMgr = new Manager();

    public UserChoiceHandler userChoiceHandler = new UserChoiceHandler();

    public Admin() {
        run();
        initUI();
    }

    private void initUI() {
        setTitle("Admin GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs for each category
        tabbedPane.addTab("Users", createUserPanel());
        tabbedPane.addTab("CPU", createProductPanel(cpuMgr));
        tabbedPane.addTab("Mainboard", createProductPanel(mainboardMgr));
        tabbedPane.addTab("RAM", createProductPanel(ramMgr));
        tabbedPane.addTab("Storage", createProductPanel(storageMgr));
        tabbedPane.addTab("Graphics Card", createProductPanel(graphicsMgr));
        tabbedPane.addTab("Power", createProductPanel(powerMgr));
        tabbedPane.addTab("Case", createProductPanel(caseMgr));

        add(tabbedPane);
        setLocationRelativeTo(null);
    }

    private JPanel createUserPanel() {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement search functionality for users if needed
            }
        });

        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        userPanel.add(searchPanel, BorderLayout.NORTH);

        JPanel userListPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(userListPanel);
        userPanel.add(scrollPane, BorderLayout.CENTER);

        updateUserList(userPanel);

        return userPanel;
    }

    private void updateUserList(JPanel userPanel) {
        // Assuming that you have a list of users in your user manager
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> userList = new JList<>(listModel);

        // Populate the listModel with user names
        for (Manageable user : userMgr.mList) {
            listModel.addElement(user.toString()); // Assuming User class has a toString method
        }

        JScrollPane scrollPane = new JScrollPane(userList);
        userPanel.add(scrollPane, BorderLayout.CENTER);

        userPanel.revalidate();
        userPanel.repaint();
    }


    private JPanel createProductPanel(Manager manager) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                manager.search(new Scanner(keyword));
                updateProductList(productPanel, manager);
            }
        });

        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        productPanel.add(searchPanel, BorderLayout.NORTH);

        JPanel productListPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(productListPanel);
        productPanel.add(scrollPane, BorderLayout.CENTER);

        updateProductList(productPanel, manager);

        return productPanel;
    }

    private void updateProductList(JPanel productPanel, Manager manager) {
        productPanel.remove(1);

        JPanel productListPanel = new JPanel(new GridLayout(manager.mList.size(), 1));

        // Display each product in the list
        for (Manageable product : manager.mList) {
            JPanel productItemPanel = new JPanel();
            JLabel productLabel = new JLabel();
            product.print();
            productLabel.setText(productLabel.getText() + " " + product); // Add product details

            productItemPanel.add(productLabel);
            productListPanel.add(productItemPanel);
        }

        JScrollPane scrollPane = new JScrollPane(productListPanel);
        productPanel.add(scrollPane, BorderLayout.CENTER);

        productPanel.revalidate();
        productPanel.repaint();
    }



    public void run() {
        loadUserData();
        loadProductData();
        userChoiceHandler.handleUserChoice();
    }

    private void loadUserData() {
        userMgr.readAll("src/Main/mall/input/user.txt", new Factory() {
            @Override
            public Manageable create() {
                return new User();
            }
        });
    }

    private void loadProductData() {
        cpuMgr.readAll("src/Main/mall/input/cpu.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Cpu();
            }
        });
        mainboardMgr.readAll("src/Main/mall/input/mainboard.txt", new Factory() {
            @Override
            public Manageable create() {
                return new MainBoard();
            }
        });
        ramMgr.readAll("src/Main/mall/input/ram.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Ram();
            }
        });
        storageMgr.readAll("src/Main/mall/input/ssd.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Storage();
            }
        });
        graphicsMgr.readAll("src/Main/mall/input/graphicscard.txt", new Factory() {
            @Override
            public Manageable create() {
                return new GraphicsCard();
            }
        });
        powerMgr.readAll("src/Main/mall/input/power.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Power();
            }
        });
        caseMgr.readAll("src/Main/mall/input/case.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Case();
            }
        });
    }


}
