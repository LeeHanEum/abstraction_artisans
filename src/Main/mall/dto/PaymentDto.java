package Main.mall.dto;

import Main.mall.Item.Product;
import Main.mall.Payment;
import Main.mall.User;

import java.util.List;

public class PaymentDto {

    private User user;

    private List<Product> productList;

    private String paymentMethod;

    public User getUser() {
        return user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public static PaymentDto builder(Payment payment){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.user = payment.getUser();
        paymentDto.productList = payment.getProductList();
        paymentDto.paymentMethod = payment.getPaymentMethod();
        return paymentDto;
    }
}
