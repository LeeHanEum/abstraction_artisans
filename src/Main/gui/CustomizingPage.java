package Main.gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomizingPage extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private JPanel usageSelectionPanel; // 사용 용도 선택 패널
    public CustomizingPage() {
        // 프레임 설정
        setTitle("부품 커스터마이징");
        setSize(450, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); // 프레임 배경색 설정

        // 라벨 설정
        label = new JLabel("부품 커스터마이징");
        label.setHorizontalAlignment(JLabel.LEFT); // 왼쪽 정렬
        label.setFont(new Font("SansSerif", Font.BOLD, 20)); // 폰트 크기 조절
        add(label, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE); // 중앙 패널 배경색 설정
        add(centerPanel, BorderLayout.CENTER);

        // "가격한도설정" 라벨을 대각선 왼쪽 위에 배치
        JLabel limitLabel = new JLabel("가격 한도 설정");
        limitLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        GridBagConstraints gbcLimitLabel = new GridBagConstraints();
        gbcLimitLabel.gridx = 0;
        gbcLimitLabel.gridy = 0;
        gbcLimitLabel.insets = new Insets(0, 0, 0, 250); // 여백 설정
        gbcLimitLabel.anchor = GridBagConstraints.WEST;
        centerPanel.add(limitLabel, gbcLimitLabel);

        // "최대" 라벨과 텍스트 필드를 포함한 패널을 생성
        JPanel textFieldPanel = new JPanel(new GridBagLayout());
        textFieldPanel.setBackground(Color.WHITE);

        JLabel maxLabel = new JLabel("최대");
        GridBagConstraints gbcMaxLabel = new GridBagConstraints();
        maxLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbcMaxLabel.gridx = 0;
        gbcMaxLabel.gridy = 1;
        gbcMaxLabel.insets = new Insets(10, 10, 10, 10); // 여백 설정
        gbcMaxLabel.anchor = GridBagConstraints.EAST;
        textFieldPanel.add(maxLabel, gbcMaxLabel);

        // 텍스트 필드를 JPanel에 추가
        textField = new JTextField(20); // 20은 텍스트 필드의 초기 크기
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 40));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        textField.setBorder(border);

        GridBagConstraints gbcTextField = new GridBagConstraints();
        gbcTextField.gridx = 1;
        gbcTextField.gridy = 1;
        gbcTextField.insets = new Insets(10, 10, 10, 10); // 여백 설정
        textFieldPanel.add(textField, gbcTextField);

        // textFieldPanel을 centerPanel에 추가
        GridBagConstraints gbcTextFieldPanel = new GridBagConstraints();
        gbcTextFieldPanel.gridx = 0;
        gbcTextFieldPanel.gridy = 1;
        gbcTextFieldPanel.insets = new Insets(10, 10, 10, 10); // 여백 설정
        centerPanel.add(textFieldPanel, gbcTextFieldPanel);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE); // 하단 버튼 패널 배경색 설정
        add(buttonPanel, BorderLayout.SOUTH);
        // 버튼을 JPanel에 추가
        button = new JButton("다음");
        button.setPreferredSize(new Dimension(88, 55));
        button.addActionListener(this); // 액션 리스너 등록
        button.setBackground(Color.decode("#525252")); // 배경색 설정
        button.setForeground(Color.WHITE); // 글씨색 설정
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.insets = new Insets(10, 310, 100, 10); // 여백 설정
        gbcButton.anchor = GridBagConstraints.EAST;
        buttonPanel.add(button, gbcButton);

        // 프레임 표시
        setLocationRelativeTo(null); // 화면 정중앙에 표시
        setVisible(true);
    }

    // 사용 용도 선택 패널 초기화
    private void initUsageSelectionPanel() {
        // 패널 초기화 및 구성
        usageSelectionPanel = new JPanel(new GridLayout(5, 1));
        usageSelectionPanel.setBackground(Color.WHITE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            //최대가격 입력받은 값을 userChoiceHandler 에게 보내야함

            // 사용 용도 선택 패널 초기화
            initUsageSelectionPanel();
            // 다음 버튼이 클릭되었을 때 "사용 용도 선택" 패널로 전환
            switchToUsageSelectionPanel();
        }
    }

    // 사용 용도 선택 패널로 전환
    private void switchToUsageSelectionPanel() {
        // 중앙 패널 비우고 사용 용도 선택 패널 추가
        getContentPane().removeAll();
        // 라벨 설정
        label = new JLabel("부품 커스터마이징");
        label.setHorizontalAlignment(JLabel.LEFT); // 왼쪽 정렬
        label.setFont(new Font("SansSerif", Font.BOLD, 20)); // 폰트 크기 조절
        add(label, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE); // 중앙 패널 배경색 설정
        add(centerPanel, BorderLayout.CENTER);

        JLabel userSelctionLabel = new JLabel("사용 용도 선택");
        userSelctionLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        GridBagConstraints gbcLimitLabel = new GridBagConstraints();
        gbcLimitLabel.gridx = 0;
        gbcLimitLabel.gridy = 0;
        gbcLimitLabel.insets = new Insets(0, 0, 0, 250); // 여백 설정
        gbcLimitLabel.anchor = GridBagConstraints.WEST;
        centerPanel.add(userSelctionLabel, gbcLimitLabel);



        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE); // 하단 버튼 패널 배경색 설정
        add(buttonPanel, BorderLayout.SOUTH);

        // 버튼을 JPanel에 추가
        button = new JButton("다음");
        button.setPreferredSize(new Dimension(88, 55));
        button.addActionListener(this); // 액션 리스너 등록
        button.setBackground(Color.decode("#525252")); // 배경색 설정
        button.setForeground(Color.WHITE); // 글씨색 설정
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.insets = new Insets(10, 310, 100, 10); // 여백 설정
        gbcButton.anchor = GridBagConstraints.EAST;
        buttonPanel.add(button, gbcButton);


        // 다시 그리기
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        // GUI 실행
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomizingPage();
            }
        });
    }
}
