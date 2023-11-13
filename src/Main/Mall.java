package Main;

import java.util.Scanner;

public class Mall {

    private static Mall instance;

    static Scanner scanner = new Scanner(System.in);

    // 싱글톤 구현
    private static Mall getInstance(){
        if(instance == null)
            instance = new Mall();
        return instance;
    }

    // menu enum 필요
    private static int menuSelect(){
        System.out.println("### 메뉴 ###");
        System.out.println("1. 로그인");
        System.out.println("2. 종료");
        return scanner.nextInt();
    }

    private void run(int menuSelect){

    }

    public static void main(String[] args) {
        Mall.getInstance().run(menuSelect());
    }

}
