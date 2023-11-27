package Main.mall;

import Main.mall.Item.Product;
import Main.mall.dto.PaymentDto;
import Main.mgr.Manageable;

import java.util.List;
import java.util.Scanner;

public class Payment implements Manageable {

    private Long paymentId;

    private User user;

    private List<Product> productList;

    private String paymentMethod;

    private static Long sequence = 1L;


    // 결제 이메일 전송
    public void sendEmail() {
        System.out.println("결제 이메일 전송");
    }

    // 결제 정보 저장
    public void savePaymentInfo(Payment payment) {
        System.out.println("결제 정보 저장");
        User.paymentMgr.add(payment);
    }

    // 결제 정보 조회
    public PaymentDto getPaymentInfo(Long paymentId) {
        System.out.println("결제 정보 조회");
        for (Manageable payment : User.paymentMgr.mList) {
            if (payment.matches(String.valueOf(paymentId))) {
                return PaymentDto.builder((Payment) payment);
            }
        }
        return null;
    }

    // 장바구니 결제
    public void payItemFormCart(String paymentMethod) {
        System.out.println("장바구니 결제");
        Cart cart = user.getCart();
        this.paymentId = sequence++;
        this.user = Login.currentUser;
        this.productList = cart.getProductList();
        this.paymentMethod = paymentMethod;
        savePaymentInfo(this);
    }

    // 선택 상품 결제
    public void paySelectedItem(Long[] productIds, String paymentMethod) {
        System.out.println("선택 상품 결제");
        Cart cart = user.getCart();
        this.paymentId = sequence++;
        this.user = Login.currentUser;
        this.productList = user.getCart().selectItemFromCart(productIds);
        this.paymentMethod = paymentMethod;
        savePaymentInfo(this);
    }

    // 상품 바로 결제
    public void payItemDirectly(Product product, String paymentMethod) {
        System.out.println("상품 바로 결제");
        this.paymentId = sequence++;
        this.user = Login.currentUser;
        this.productList.add(product);
        this.paymentMethod = paymentMethod;
        savePaymentInfo(this);
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public static Payment builder(PaymentDto paymentDto){
        Payment payment = new Payment();
        payment.user = paymentDto.getUser();
        payment.productList = paymentDto.getProductList();
        payment.paymentMethod = paymentDto.getPaymentMethod();
        return payment;
    }

    @Override
    public void read(Scanner scan) {

    }

    @Override
    public void print() {

    }

    @Override
    public boolean matches(String kwd) {
        return false;
    }
}
