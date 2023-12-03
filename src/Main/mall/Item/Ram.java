package Main.mall.Item;

import java.util.Scanner;

public class Ram extends Product{

    String ramType;
    int capacity;
    int speed;
    public Ram(){
        this.type = "Ram";
    }
    @Override
    public void read(Scanner scan){
        ramType = scan.next();
        capacity = scan.nextInt();
        speed = scan.nextInt();
        super.read(scan);
    }

    @Override
    public void print(){
        super.print();
        System.out.printf("DDR 종류: %s, 메모리용량: %d, 속도:%d\n", ramType, capacity, speed);
    }
    @Override
    public String toString() {
        return super.toString() + String.format("DDR 종류: %s, 메모리용량: %d, 속도:%d\n", ramType, capacity, speed);
    }

    @Override
    public boolean matches(String kwd){
        try {
            int num = Integer.parseInt(kwd);
            // 사용자 입력이 숫자인 경우, 속도와 비교
            if (speed >= num) {
                return true;
            }
        } catch (NumberFormatException e) {
            // 사용자 입력이 문자열인 경우, type과 비교를 없애고 이름비교를 넣었음
            if (super.matches(kwd)) {
                return true;
            }
        }
        return false;
    }
    
    //램타입 반환
    public String getRamType(){
        return ramType;
    }
}
