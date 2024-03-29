package Main.mall.Item;

import java.util.Scanner;

public class Power extends Product{
    int power;
    public Power(){
        this.type = "Power";
    }

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
    @Override
    public String toString() {
        return super.toString() + String.format("정격파워:%d\n", power);
    }

    //정격파워하고 비교해서 더 크면 반환
    //return 에 super.matches로 이름도 비교하게 만듦
    @Override
    public boolean matches(String kwd){
        if (power >= Integer.parseInt(kwd))
            return true;
        return super.matches(kwd);
    }
    
    //파워 반환
    public int getPower(){
        return power;
    }
}
