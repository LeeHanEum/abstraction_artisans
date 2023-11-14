package Main.mall;

import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Cart {

    private Long cartId;

    private User user;

    private LocalDateTime createdAt;


    // 장바구니에 상품 담기
    public void addItemToCart() {
        System.out.println("장바구니에 상품을 담았습니다.");
    }

    // 장바구니에서 상품 삭제
    public void deleteItemFromCart() {
        System.out.println("장바구니에서 상품을 삭제했습니다.");
    }

    // 장바구니에서 상품 수량 조절
    public void EditQuantityFromCart() {
        System.out.println("장바구니에서 상품 수량을 조절했습니다.");
    }

    // 장바구니에서 상품 리스팅 조회
    public void getItemListFromCart() {
        System.out.println("장바구니에서 상품을 조회했습니다.");
    }

    // 장바구니에서 상품 구매
    public void payItemFromCart() {
        System.out.println("장바구니에서 상품을 구매했습니다.");
    }

}
