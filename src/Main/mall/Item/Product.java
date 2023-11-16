package Main.mall.Item;

import java.time.LocalDateTime;
import java.util.Scanner;
import Main.mgr.Factory;
import Main.mgr.Manageable;
import Main.mgr.Manager;

public class Product implements Manageable {

    Long productId; // 상품 아이디

    String name;

    int price;

    LocalDateTime createdAt;

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
