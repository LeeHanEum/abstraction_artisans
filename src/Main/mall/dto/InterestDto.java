package Main.mall.dto;

import Main.mall.Interest;
import Main.mall.Item.Product;
import Main.mall.User;

import java.time.LocalDateTime;
import java.util.List;
//이 부분도 cartdto와 비슷하게 수정
public class InterestDto {

    private User user;

    private List<Product> product;

    private LocalDateTime createdAt;

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static InterestDto builder(Interest interest){
        InterestDto interestDto = new InterestDto();
        interestDto.user = interest.getUser();
        interestDto.product = interest.getInterestList();
        interestDto.createdAt = interest.getCreatedAt();
        return interestDto;
    }
}
