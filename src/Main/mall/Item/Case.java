package Main.mall.Item;

import java.util.Scanner;

public class Case extends Product{
    String color;
    String boardType;
    @Override
    public void read(Scanner scan) {
        color = scan.next();
        boardType = scan.next();
        super.read(scan);
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("색상: %s , 지원보드규격: %s\n", color, boardType);
    }

    @Override
    public boolean matches(String kwd) {
        if(kwd.equals(color))
            return true;
        if(boardType.contains(kwd))
            return true;
        return super.matches(kwd);
    }

}
