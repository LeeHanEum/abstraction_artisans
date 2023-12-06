package Main.mall;

import Main.mall.Item.Product;
import Main.mall.dto.InterestDto;
import Main.mgr.Manageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//전체코드 cart와 유사하게 수정
public class Interest {
    private final User user;
    private final LocalDateTime createdAt;
    private final List<Product> interestList;
    public Interest(User user) {
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.interestList = new ArrayList<>();
    }

    // 상품 찜하기
    public void markInterest(Product product) {
        interestList.add(product);  // Add the interest to the interest list
        System.out.println("상품 찜하기");
    }

    public List<Product> getInterestList() {
        return interestList;
    }

    public void deleteInterest(Long productId) {
        for (Product p : interestList){
            if (Objects.equals(p.getProductId(), productId)){
                interestList.remove(p);
            }
        }        System.out.println("찜한 상품 삭제");
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

 /*   public static Interest builder(InterestDto interestDto) {
        Interest interest = new Interest();
        interest.user = interestDto.getUser();
        interest.product = interestDto.getProduct();
        interest.createdAt = interestDto.getCreatedAt();
        return interest;
    }*/
}