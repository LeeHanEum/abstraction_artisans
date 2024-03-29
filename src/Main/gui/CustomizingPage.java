package Main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Main.mall.Admin;
import Main.mall.UserChoiceHandler;

public class CustomizingPage extends JFrame implements ActionListener {
    private JPanel contentPane = new JPanel();
    private JLabel limitLabel;
    private JLabel maxLabel;
    private JLabel choiceLabel;
    private JTextField textField;
    private JButton nextButton;
    private JButton backButton;
    private JRadioButton choiceRadioButton[] =new JRadioButton[6];
    private ButtonGroup bg;
    int current = 0,c1 = 0,c2 = 0;

    private int userChoices[] = new int[3];
    private final Admin admin;

    public CustomizingPage(Admin admin) {
        this.admin = admin;
        // JPanel 생성
        setTitle("CustomizingPage");
        setSize(450, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // contentPane 레이아웃 설정
        contentPane.setLayout(null); // 배치 관리자를 null로 설정
        setContentPane(contentPane);
        contentPane.setBackground(Color.white);

        // 부품 커스터마이징 라벨
        JLabel customLabel = new JLabel("부품 커스터마이징");
        customLabel.setBounds(12, 10, 174, 41);
        customLabel.setFont(new Font("Inter", Font.BOLD, 20));
        contentPane.add(customLabel);

        // "가격한도설정" 라벨
        limitLabel = new JLabel("가격 한도 설정");
        limitLabel.setFont(new Font("Inter", Font.BOLD, 24));
        limitLabel.setBounds(12, 196, 188, 55);
        contentPane.add(limitLabel);

        // "최대" 라벨
        maxLabel = new JLabel("<html><h3>최대</h3></html>");
        maxLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        maxLabel.setBounds(45, 297, 52, 15);
        contentPane.add(maxLabel);

        // 텍스트 필드
        textField = new JTextField(10);
        textField.setBounds(93, 281, 258, 48);
        contentPane.add(textField);

        // "다음" 버튼 크기랑 위치조정
        nextButton = new JButton("다음");
        nextButton.addActionListener(this);
        nextButton.setOpaque(true);
        nextButton.setBackground(new Color(64, 64, 64));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(true);
        nextButton.setBorderPainted(false);
        nextButton.setBounds(280, 594, 120, 50);
        contentPane.add(nextButton);

        // "이전" 버튼 크기랑 위치조정
        backButton = new JButton("이전");
        backButton.addActionListener(this);
        backButton.setOpaque(true);
        backButton.setBackground(new Color(64, 64, 64));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(true);
        backButton.setBorderPainted(false);
        backButton.setBounds(40, 594, 120, 50);
        contentPane.add(backButton);


        //"사용 용도 선택 라벨"
        choiceLabel = new JLabel("사용 용도 선택");
        choiceLabel.setFont(new Font("Inter", Font.BOLD, 24));
        choiceLabel.setBounds(12, 118, 430, 55);
        contentPane.add(choiceLabel);

        bg = new ButtonGroup();
        for (int i = 0; i < 6; i++){
            choiceRadioButton[i] = new JRadioButton();
            add(choiceRadioButton[i]);
            bg.add(choiceRadioButton[i]);
        }
        int s = 0;
        for (int i = 0; i < 6; i++){
            choiceRadioButton[i].setBounds(12, 200+s, 240, 23);
            s = s +75;
            contentPane.add(choiceRadioButton[i]);
            choiceRadioButton[i].setVisible(false);
            choiceRadioButton[i].setBackground(Color.white);
            choiceRadioButton[i].setFont(new Font("Inter", Font.PLAIN, 18));
        }
        choiceRadioButton[5].setSelected(true);

        // 프레임 표시
        setVisible(true);
        choiceLabel.setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            if (current == 0) {
                navigateToMain();
            } else if (current == 1) {
                for (int i = 0; i < 6; i++) {
                    choiceRadioButton[i].setVisible(false);
                }
                textField.setVisible(true);
                maxLabel.setVisible(true);
                limitLabel.setVisible(true);
                choiceLabel.setVisible(false);
                current--;
            } else if (current == 2) {
                for (int i = 0; i < 6; i++) {
                    choiceRadioButton[i].setVisible(false);
                }
                current--;
                choiceLabel.setText("사용 용도 선택"); // 초기화 추가
                choiceRadioButton[0].setText("1. 게임용");
                choiceRadioButton[1].setText("2. 작업용");
                choiceRadioButton[2].setText("3. 사무용");
                choiceRadioButton[3].setText("4. 취미용");
                for (int i = 0; i < 4; i++) {
                    choiceRadioButton[i].setVisible(true);
                }
                // "다음"으로 버튼 텍스트 변경
                nextButton.setText("다음");
            }
        }

        if (e.getSource() == nextButton) {
            if (current == 0) {
                textField.setVisible(false);
                maxLabel.setVisible(false);
                limitLabel.setVisible(false);
                choiceLabel.setVisible(true);
                setChoiceRadioButton(0);
                try {
                    userChoices[current] = Integer.parseInt(textField.getText());
                } catch (NumberFormatException e1) {
                    System.out.println("숫자가 아님!");
                }
                current++;
                // 입력받은 값을 userChoiceHandler 에게 전달 구현
            } else if (current == 1) {
                c1 = choiceCheck();
                userChoices[current] = c1;
                if (!choiceRadioButton[5].isSelected()) {
                    for (int i = 0; i < 6; i++) {
                        choiceRadioButton[i].setVisible(false);
                    }
                    setChoiceRadioButton(c1);
                    current++;
                }
                // 입력받은 값을 userChoiceHandler 에게 전달 구현
                // "결과"로 버튼 텍스트 변경
                nextButton.setText("결과 확인");
            } else if (current == 2) {
                userChoices[current] = choiceCheck();
                System.out.println("" + userChoices[0] + userChoices[1] + userChoices[2]);
                UserChoiceHandler usr = new UserChoiceHandler();
                ArrayList rec[] = usr.recommends(userChoices);
                dispose();
                new RecommendList(rec, admin);
            }
        }
    }
    void setChoiceRadioButton(int c){
        if (current == 0){
            choiceRadioButton[0].setText("1. 게임용");
            choiceRadioButton[1].setText("2. 작업용");
            choiceRadioButton[2].setText("3. 사무용");
            choiceRadioButton[3].setText("4. 취미용");
            for (int i = 0; i < 4; i++){
                choiceRadioButton[i].setVisible(true);
            }
        }
        else if (current == 1){
            if(c == 1) {
                choiceLabel.setText("주로 하는 게임을 골라주세요");
                choiceRadioButton[0].setText("롤");
                choiceRadioButton[1].setText("오버워치");
                choiceRadioButton[2].setText("배틀그라운드");
                choiceRadioButton[3].setText("메이플스토리");
                choiceRadioButton[4].setText("서든어텍");
                for (int i = 0; i < 5; i++) {
                    choiceRadioButton[i].setVisible(true);
                }
            }
            else if(c == 2) {
                choiceLabel.setText("주로 하는 작업을 골라주세요");
                choiceRadioButton[0].setText("소프트웨어 개발");
                choiceRadioButton[1].setText("게임 개발");
                choiceRadioButton[2].setText("그래픽 디자인");
                choiceRadioButton[3].setText("도면 설계");
                choiceRadioButton[4].setText("웹개발 및 디자인");

                for (int i = 0; i < 5; i++) {
                    choiceRadioButton[i].setVisible(true);
                }
            }
            else if(c == 3) {
                choiceLabel.setText("주로 하는 사무 용도를 골라주세요");
                choiceRadioButton[0].setText("워드 및 한글");
                choiceRadioButton[1].setText("피피티");
                choiceRadioButton[2].setText("웹서치");

                for (int i = 0; i < 3; i++) {
                    choiceRadioButton[i].setVisible(true);
                }
            }
            else if(c == 4) {
                choiceLabel.setText("주로 취미로 하는 활동을 골라주세요");
                choiceRadioButton[0].setText("유튜브 및 OTT");
                choiceRadioButton[1].setText("웹서핑");

                for (int i = 0; i < 2; i++) {
                    choiceRadioButton[i].setVisible(true);
                }
            }
        }
    }
    int choiceCheck() {
        int check = 0;
        if (choiceRadioButton[0].isSelected()) {
            check = 1;
            return check;
        }
        if (choiceRadioButton[1].isSelected()) {
            check = 2;
            return check;
        }
        if (choiceRadioButton[2].isSelected()) {
            check = 3;
            return check;
        }
        if (choiceRadioButton[3].isSelected()) {
            check = 4;
            return check;
        }
        if (choiceRadioButton[4].isSelected()) {
            check = 5;
            return check;
        }
        return check;
    }
    private void navigateToMain() {
        dispose();
        new MainPage(admin);
    }
}
