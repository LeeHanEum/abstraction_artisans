package Main.mall.Item;

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
        super.read(scan);
    }

    @Override
    public void print(){
        super.print();
        System.out.printf("DDR 종류: %s, 메모리용량: %d, 속도:%d\n", type, capacity, speed);
    }
    @Override
    public String toString() {
        return String.format("DDR 종류: %s, 메모리용량: %d, 속도:%d\n", type, capacity, speed);
    }
    //return 에 super.matches로 이름도 비교하게 만듦
    @Override
    public boolean matches(String kwd){
        try {
            int num = Integer.parseInt(kwd);
            // 사용자 입력이 숫자인 경우, 용량과 비교
            //용량 비교에서 스피드 비교로 바꿈
            if (speed >= num) {
                return true;
            }
        } catch (NumberFormatException e) {
            // 사용자 입력이 문자열인 경우, type과 비교
            if (type.equals(kwd)) {
                return true;
            }
        }
        return super.matches(kwd);
    }
}
