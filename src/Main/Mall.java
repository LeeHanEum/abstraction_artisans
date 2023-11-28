package Main;

import Main.gui.ItemListPage;
import Main.gui.UserListPage;
import Main.mall.Admin;

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

    private void run(){
        Admin admin = new Admin();
        admin.run();
        new UserListPage(admin).setVisible(true);
    }

    public static void main(String[] args) {
//        Mall.getInstance().run(menuSelect());
        Mall.getInstance().run();
    }

}
