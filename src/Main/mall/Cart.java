package Main.mall;

import Main.mall.Item.Product;
import Main.mall.dto.CartDto;
import javafx.util.Builder;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class Cart {

    private Long cartId;

    private User user;

    private List<Product> productList;

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

    public User getUser() {
        return user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static Cart builder(CartDto cartDto){
        Cart cart = new Cart();
        cart.user = cartDto.getUser();
        cart.productList = cartDto.getProductList();
        cart.createdAt = cartDto.getCreatedAt();
        return cart;
    }

}
