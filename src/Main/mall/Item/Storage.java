package Main.mall.Item;

import java.util.Scanner;

public class Storage extends Product{

    int capacity;
    int speedAvg;
    public Storage(){
        this.type = "Storage";
    }
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

    @Override
    public String toString() {
        return String.format("용량: %d, 읽기쓰기 속도 평균:%d", capacity, speedAvg);
    }
    //용량이랑 비교해서 더 크면 반환
    //return 에 super.matches로 이름도 비교하게 하는걸 catch로 옮겼음
    @Override
    public boolean matches(String kwd){
        try {
            if (capacity >= Integer.parseInt(kwd))
                return true;
        } catch (NumberFormatException e){
            return super.matches(kwd);
        }
        return false;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpeedAvg() {
        return speedAvg;
    }
}
