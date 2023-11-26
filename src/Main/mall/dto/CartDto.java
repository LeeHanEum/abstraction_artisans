package Main.mall.dto;

import Main.mall.Cart;
import Main.mall.Item.Product;
import Main.mall.User;

import java.time.LocalDateTime;
import java.util.List;

public class CartDto {

    private User user;

    private List<Product> productList;

    private LocalDateTime createdAt;

    public User getUser() {
        return user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static CartDto builder(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.user = cart.getUser();
        cartDto.productList = cart.getProductList();
        cartDto.createdAt = LocalDateTime.now();
        return cartDto;
    }
}
