package mall.Item;

import java.util.Scanner;
import mgr.Manageable;

public class Product implements Manageable {
    String name;
    int price;
    @Override
    public void read(Scanner scan){
        name = scan.nextLine();
        price = scan.nextInt();
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
