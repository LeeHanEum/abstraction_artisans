package Main.gui;

import Main.mall.Admin;
import Main.mall.Login;
import Main.mall.dto.UserDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class SetMyInfoPage extends JFrame {

    private JTextField nameField;
    private JTextField telField;
    private JTextField addressField;

    private final Admin admin;

    public SetMyInfoPage(Admin admin) {
        this.admin = admin;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 800);
        setLayout(null);

        // 전체 패널
        JPanel userPagePanel = new JPanel();
        userPagePanel.setBackground(Color.WHITE);
        userPagePanel.setBounds(0, 0, 450, 800);
        userPagePanel.setLayout(null);

        UserDto userDto = Login.currentUser.getUserInfo();
        String tel = userDto.getTel();
        String address = userDto.getAddress();
        String name = userDto.getName();

        // 텍스트 필드 생성 및 위치 지정
        nameField = new JTextField(name);
        nameField.setBounds(50, 50, 300, 30);
        userPagePanel.add(nameField);

        telField = new JTextField(tel);
        telField.setBounds(50, 100, 300, 30);
        userPagePanel.add(telField);

        addressField = new JTextField(address);
        addressField.setBounds(50, 150, 300, 30);
        userPagePanel.add(addressField);

        // 저장 버튼
        JButton saveButton = new JButton("변경 사항 저장");
        saveButton.setBounds(50, 200, 150, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
        userPagePanel.add(saveButton);

        add(userPagePanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveChanges() {
        // 텍스트 필드에서 업데이트된 정보 검색
        String newName = nameField.getText();
        String newTel = telField.getText();
        String newAddress = addressField.getText();

        // 사용자 정보 업데이트
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setName(newName);
        updatedUserDto.setTel(newTel);
        updatedUserDto.setAddress(newAddress);
        Login.currentUser.setUserInfo(updatedUserDto);

        // 여기에 데이터베이스에 저장하거나 성공 메시지를 표시하는 등의 추가 로직을 추가할 수 있습니다.
        JOptionPane.showMessageDialog(this, "변경 사항이 성공적으로 저장되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);

        // 마이페이지로 돌아갑니다.
        dispose();
        new MyPage(admin);
    }
}
