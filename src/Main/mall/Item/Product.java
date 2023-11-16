package Main.mall.Item;

import java.time.LocalDateTime;
import java.util.Scanner;

import Main.mgr.Manageable;

public class Product implements Manageable {

    private Long productId; // 상품 아이디

    private String name;

    private int price;

    private LocalDateTime createdAt;

    @Override
    public void read(Scanner scan){
        price = scan.nextInt();
        name = scan.nextLine();
    }

    @Override
    public void print() {
        System.out.printf("%s %d", name, price);
    }

    @Override
    public boolean matches(String kwd) {
        if (name.equals(kwd))
            return true;
        return false;
    }

}
