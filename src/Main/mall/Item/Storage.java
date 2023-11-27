package Main.mall.Item;

import java.util.Scanner;

public class Storage extends Product{

    int capacity;
    int speedAvg;
    @Override
    public void read(Scanner scan){
        capacity = scan.nextInt();
        speedAvg = scan.nextInt();
        super.read(scan);
    }

    @Override
    public void print(){
        super.print();
        System.out.printf("용량: %d, 읽기쓰기 속도 평균:%d\n", capacity, speedAvg);
    }
    public String toString() {
        return super.toString() + String.format("용량: %d, 읽기쓰기 속도 평균:%d\n", capacity, speedAvg);
    }

    //용량이랑 비교해서 더 크면 반환
    //return 에 super.matches로 이름도 비교하게 만듦
    @Override
    public boolean matches(String kwd){
        if (capacity >= Integer.parseInt(kwd))
            return true;
        return super.matches(kwd);
    }
}
