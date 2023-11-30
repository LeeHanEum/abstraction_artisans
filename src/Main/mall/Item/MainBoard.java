package Main.mall.Item;


import java.util.Scanner;

public class MainBoard extends Product{
    String socket;
    String ramType;
    String boardType;
    public MainBoard(){
        this.type = "MainBoard";
    }
    @Override
    public void read(Scanner scan) {
        socket = scan.next();
        ramType = scan.next();
        boardType = scan.next();
        super.read(scan);
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("(소켓%s) 메모리규격: %s , 지원보드규격: %s\n", socket, ramType, boardType);
    }
    @Override
    public String toString() {
        return super.toString() + String.format("(소켓%s) 메모리규격: %s , 지원보드규격: %s\n", socket, ramType, boardType);
    }
    @Override
    public boolean matches(String kwd) {
        if(kwd.equals(socket))
            return true;
        if(kwd.equals(ramType))
            return true;
        if(kwd.equals(boardType))
            return true;
        return super.matches(kwd);
    }
    
    //램타입 반환
    public String getRamType(){
        return ramType;
    }
    
    //보드타입 반환
    public String getBoardType(){
        return boardType;
    }
}
