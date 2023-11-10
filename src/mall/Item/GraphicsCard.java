package mall.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class GraphicsCard extends Product{
    int baseClock;
    int memoryCapacity;
    ArrayList<String> outputTerminal = new ArrayList<>();
    int power;

    @Override //end 나올때까지 출력단자 입력받기
    public void read(Scanner scan){
        baseClock = scan.nextInt();
        memoryCapacity = scan.nextInt();
        String isend;
        while (true){
            isend = scan.next();
            if (isend.equals("end")) break;
            outputTerminal.add(isend);
        }
        power = scan.nextInt();
        super.read(scan);
    }

    @Override
    public void print(){
        super.print();
        System.out.printf("베이스클럭: %d, 메모리용량: %d, 출력단자:%s, 정격파워:%d\n", baseClock, memoryCapacity, outputTerminal, power);
    }

    //용량이랑 비교해서 더 크면 출력
    @Override
    public boolean matches(String kwd){
        if (memoryCapacity >= Integer.parseInt(kwd))
            return true;
        return false;
    }
}
