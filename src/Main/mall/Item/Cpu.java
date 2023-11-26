package Main.mall.Item;

import java.util.Scanner;

public class Cpu extends Product{
    float clock;
    int core;
    float cache;
    String socket;
    String ramType;
    @Override
    public void read(Scanner scan) {
        clock = scan.nextFloat();
        core = scan.nextInt();
        cache = scan.nextFloat();
        socket = scan.next();
        ramType = scan.next();
        super.read(scan);
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("기본클럭:%.1f GHz 코어수:%d 캐시:%.1f MB (소캣%s) 메모리규격:%s\n"
                , clock, core, cache, socket, ramType);
    }
    @Override
    public String toString() {
        return String.format("기본클럭:%.1f GHz 코어수:%d 캐시:%.1f MB (소캣%s) 메모리규격:%s"
            , clock, core, cache, socket, ramType);
    }
    
    //캐시비교로 바꿨음
    //catch문에서 super.matches 실행되게 변경
    @Override
    public boolean matches(String kwd) {
        try {
            int num = Integer.parseInt(kwd);
            // 사용자 입력이 숫자인 경우, 용량과 비교
            if (cache >= num) {
                return true;
            }
        } catch (NumberFormatException e) {
            if(super.matches(kwd))
                return true;
        }
        return false;
    }
    
    //램타입 반환
    public String getRamType(){
        return ramType;
    }
}
