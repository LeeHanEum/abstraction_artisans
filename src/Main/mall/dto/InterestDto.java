package Main.mall.dto;

import Main.mall.Interest;
import Main.mall.Item.Product;
import Main.mall.User;

import java.time.LocalDateTime;

public class InterestDto {

    private User user;

    private Product product;

    private LocalDateTime createdAt;

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static InterestDto builder(Interest interest){
        InterestDto interestDto = new InterestDto();
        interestDto.user = interest.getUser();
        interestDto.product = interest.getProduct();
        interestDto.createdAt = interest.getCreatedAt();
        return interestDto;
    }
}
