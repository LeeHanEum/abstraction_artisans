package mall.Item;

import java.util.Scanner;

public class GraphicsCard extends Product{
    int baseClock;
    int memoryCapacity;
    String outputTerminal;
    int power;

    @Override //end 나올때까지 출력단자 입력받기
    public void read(Scanner scan){
        baseClock = scan.nextInt();
        memoryCapacity = scan.nextInt();
        while (true){
            if (outputTerminal.equals("end")) break;
            outputTerminal = scan.next();
        }
        power = scan.nextInt();
    }

    @Override
    public void print(){
        System.out.printf("베이스클럭: %d, 메모리용량: %d, 출력단자:%s, 정격파워:%d", baseClock, memoryCapacity, outputTerminal, power);
    }

    @Override
    public boolean matches(String kwd){
        return false;
    }
}
