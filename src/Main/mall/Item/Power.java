package Main.mall.Item;

import java.util.Scanner;

public class Power extends Product{
    int power;

    @Override
    public void read(Scanner scan){
        power = scan.nextInt();
        super.read(scan);
    }

    @Override
    public void print(){
        super.print();
        System.out.printf("정격파워:%d\n", power);
    }

    //정격파워하고 비교해서 더 크면 반환
    @Override
    public boolean matches(String kwd){
        if (power >= Integer.parseInt(kwd))
            return true;
        return false;
    }
}
