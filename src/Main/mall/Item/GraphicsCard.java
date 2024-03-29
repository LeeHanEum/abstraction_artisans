package Main.mall.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class GraphicsCard extends Product{
    int baseClock;
    int memoryCapacity;
    ArrayList<String> outputTerminal = new ArrayList<>();
    int power;

    public GraphicsCard(){
        this.type = "GPU";
    }

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
    @Override
    public String toString() {

        return super.toString() + String.format("베이스클럭: %d, 메모리용량: %d, 출력단자:%s, 정격파워:%d\n", baseClock, memoryCapacity, outputTerminal, power);
    }
    //용량이랑 비교해서 더 크면 출력
    //return 에 super.matches로 이름도 비교하는걸 catch로 옮겼음
    @Override
    public boolean matches(String kwd){
        try {
            int num = Integer.parseInt(kwd);
            // 사용자 입력이 숫자인 경우, 용량과 비교
            if (memoryCapacity >= num) {
                return true;
            }
        } catch (NumberFormatException e) {
            if (super.matches(kwd)) {
                return true;
            }
        }
        return false;
    }
    
    //정격파워 반환
    public int getPower(){
        return power;
    }

    public int getBaseClock() {
        return baseClock;
    }

    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public ArrayList<String> getOutputTerminal() {
        return outputTerminal;
    }
}
