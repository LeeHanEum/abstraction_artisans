package mall.Item;

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
    public boolean matches(String kwd) {
        if(kwd.equals(socket))
            return true;
        if(ramType.contains(kwd))
            return true;
        return super.matches(kwd);
    }
}
