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
        userPagePanel.setBackground(Color.WHITE);
        userPagePanel.setBounds(0, 0, 450, 800);
        userPagePanel.setLayout(null);

        JLabel titleLabel = new JLabel("제품리스트");
        titleLabel.setBounds(30, 40, 300, 30);
        titleLabel.setFont(new Font("Inter", Font.PLAIN, 20));
        userPagePanel.add(titleLabel);

        sandbox2 = new JPanel();
        JScrollPane scrollPane = new JScrollPane(sandbox2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBounds(30, 110, 390, 610);

        sandbox2.setLayout(new BoxLayout(sandbox2, BoxLayout.Y_AXIS));
        sandbox2.setBackground(Color.LIGHT_GRAY);
        sandbox2.setBounds(30, 110, 390, 610);

        searchField = new JTextField();
        searchField.setBounds(30, 80, 150, 20);

        JButton searchButton = new JButton("검색");
        searchButton.setBounds(190, 80, 80, 20);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch(admin);
            }
        });

        userPagePanel.add(searchField);
        userPagePanel.add(searchButton);

        sandbox2.add(Box.createRigidArea(new Dimension(0, 3)));
        for (Manageable a : admin.getAllItem()) {
            createGroupPanel(sandbox2, ((Product) a).getName());
            sandbox2.add(Box.createRigidArea(new Dimension(0, 3)));
        }

        sandbox2.add(Box.createVerticalGlue());

        userPagePanel.add(scrollPane, BorderLayout.WEST);
        add(userPagePanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createGroupPanel(JPanel parentPanel, String labelText) {
        JPanel groupPanel = new JPanel();
        groupPanel.setPreferredSize(new Dimension(360, 100));
        groupPanel.setBackground(Color.WHITE);
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));

        groupPanel.add(Box.createRigidArea(new Dimension(30, 0)));

        JLabel whiteLabel = new JLabel();
        whiteLabel.setOpaque(true);
        whiteLabel.setBackground(Color.WHITE);
        groupPanel.add(whiteLabel);

        JLabel textLabel = new JLabel(labelText);
        textLabel.setFont(new Font("Inter", Font.PLAIN, 12));
        textLabel.setForeground(Color.BLACK);
        groupPanel.add(textLabel);

        groupPanel.add(Box.createRigidArea(new Dimension(298, 0)));

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
                    createGroupPanel(sandbox2, ((Product) a).getName());
                    sandbox2.add(Box.createRigidArea(new Dimension(0, 3)));
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
            createGroupPanel(sandbox2, ((Product) a).getName());
            sandbox2.add(Box.createRigidArea(new Dimension(0, 3)));
        }

        sandbox2.add(Box.createVerticalGlue());
    }
}
