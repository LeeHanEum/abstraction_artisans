package mall.Item;

import java.util.Scanner;

public class Power extends Product{
    int power;

    @Override
    public void read(Scanner scan){
        power = scan.nextInt();
    }

    @Override
    public void print(){
        System.out.printf("정격파워:%d", power);
    }

    @Override
    public boolean matches(String kwd){
        return false;
    }
}
