package mall.Item;

import java.util.Scanner;

public class Ram extends Product{

    String type;
    int capacity;
    int speed;
    @Override
    public void read(Scanner scan){
        type = scan.next();
        capacity = scan.nextInt();
        speed = scan.nextInt();
    }

    @Override
    public void print(){
        System.out.printf("DDR 종류: %s, 메모리용량: %d, 속도:%d", type, capacity, speed);
    }

    @Override
    public boolean matches(String kwd){
        return false;
    }
}
