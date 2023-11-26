package Main.mall.Item;

import java.time.LocalDateTime;
import java.util.Scanner;

import Main.mgr.Manageable;

public class Product implements Manageable {

    private Long productId; // 상품 아이디

    private String name;

    private int price;

    private LocalDateTime createdAt;

    private static Long sequence = 0L;

    //trim 추가 앞 뒤 공백 삭제
    @Override
    public void read(Scanner scan){
        productId = ++sequence;
        price = scan.nextInt();
        name = scan.nextLine().trim();
    }

    //원 하고 띄어쓰기 추가
    @Override
    public void print() {
        System.out.printf("%s %d원 ", name, price);
    }

    @Override
    public boolean matches(String kwd) {
        if (name.contains(kwd))
            return true;
        return false;
    }

    public Long getProductId() {
        return productId;
    }

    public void modify(String kwd){
        try {
            this.price = Integer.parseInt(kwd);
        }
        catch (NumberFormatException e){
            this.name = kwd;
        }
    }
}