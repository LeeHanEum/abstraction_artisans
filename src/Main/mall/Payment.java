package Main.mall;

import Main.mall.Item.Product;

import java.util.List;

public class Payment {

    private Long paymentId;

    private User user;

    private List<Product> productList;

    private String paymentMethod;


    // 결제 이메일 전송
    public void sendEmail() {
        System.out.println("결제 이메일 전송");
    }

    // 결제 정보 저장
    public void savePaymentInfo() {
        System.out.println("결제 정보 저장");
    }

    // 결제 정보 조회
    public void getPaymentInfo() {
        System.out.println("결제 정보 조회");
    }

    // 결제 정보 입력
    public void inputPaymentInfo() {
        System.out.println("결제 정보 입력");
    }

}
