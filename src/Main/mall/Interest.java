package Main.mall;

import Main.mall.Item.Product;
import Main.mall.dto.InterestDto;

import java.time.LocalDateTime;

public class Interest {

    private Long interestId;

    private User user;

    private Product product;

    private LocalDateTime createdAt;

    // 상품 찜하기
    public void markInterest() {
        System.out.println("상품 찜하기");
    }

    // 찜한 상품 조회
    public void getInterestList() {
        System.out.println("찜한 상품 조회");
    }

    // 찜한 상품 삭제
    public void deleteInterest() {
        System.out.println("찜한 상품 삭제");
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static Interest builder(InterestDto interestDto){
        Interest interest = new Interest();
        interest.user = interestDto.getUser();
        interest.product = interestDto.getProduct();
        interest.createdAt = interestDto.getCreatedAt();
        return interest;
    }
}
