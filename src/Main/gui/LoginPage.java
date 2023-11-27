package Main.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        RoundedPanel backgroundPanel = new RoundedPanel(29, 44, 392, 700, new Color(150, 150, 150, 50));
        mainPanel.add(backgroundPanel);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(52, 130, 78, 40);
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        mainPanel.add(loginLabel);

        createStyledButton(mainPanel, "회원가입", 100, 550);
        createStyledButton(mainPanel, "로그인", 100, 470);

        createStyledTextField(mainPanel, "비밀번호를 입력하세요", 52, 285, 340, 1);
        createStyledTextField(mainPanel, "아이디를 입력하세요", 52, 200, 340, 2);

        mainPanel.setFocusable(true); // Set focus to the main panel

        add(mainPanel);
        setVisible(true);
    }

    private void createStyledButton(JPanel panel, String text, int left, int top) {
        StyledButton button = new StyledButton(text, left, top);
        button.setBounds(left, top, 250, 50); // Increased size
        panel.add(button);
    }

    private void createStyledTextField(JPanel panel, String placeholder, int left, int top, int width, int id) {
        StyledTextField textField = new StyledTextField(placeholder);
        textField.setBounds(left, top + 20, width, 40); // Increased height
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(textField);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage());
    }

    class StyledTextField extends JTextField {
        private String hintText;

        public StyledTextField(String hintText) {
            this.hintText = hintText;
            setupUI();
        }

        private void setupUI() {
            setOpaque(false);
            setBorder(new CompoundBorder(new RoundedCornerBorder(), new EmptyBorder(0, 10, 0, 10)));
            setForeground(Color.GRAY);
            setText(hintText);

            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(hintText)) {
                        setText("");
                        setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText(hintText);
                        setForeground(Color.GRAY);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int arc = 15; // Corner radius
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

            super.paintComponent(g);
        }
    }

    class RoundedCornerBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int arc = 15; // Corner radius
            g2.setColor(c.getBackground());
            g2.drawRoundRect(x, y, width - 1, height - 1, arc, arc);

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 8, 4, 8); // Adjust the padding as needed
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(4, 8, 4, 8); // Adjust the padding as needed
            return insets;
        }
    }

    class StyledButton extends JButton {
        private int left, top;

        public StyledButton(String text, int left, int top) {
            super(text);
            this.left = left;
            this.top = top;
            setupUI();
        }

        private void setupUI() {
            setBounds(left, top, 250, 50); // Increased size
            setFont(new Font("Arial", Font.PLAIN, 20)); // Increased font size
            setForeground(Color.BLACK);
            setBackground(Color.WHITE); // Dark gray background color
            setBorder(null); // Removed border
            setFocusPainted(false); // Remove focus border
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int arc = 15; // Corner radius
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

            super.paintComponent(g);
        }
    }

    class RoundedPanel extends JPanel {
        private int x, y, width, height;
        private Color color;

        public RoundedPanel(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.fillRoundRect(x, y, width, height, 15, 15);
            g2d.dispose();
        }
    }
}
