package mall.Item;

import java.util.Scanner;

public class Storage extends Product{

    int capacity;
    int speedAvg;
    @Override
    public void read(Scanner scan){
        capacity = scan.nextInt();
        speedAvg = scan.nextInt();
    }

    @Override
    public void print(){
        System.out.printf("용량: %d, 읽기쓰기 속도 평균:%d", capacity, speedAvg);
    }

    @Override
    public boolean matches(String kwd){
        return false;
    }
}
