package Main.mall;

import Main.mall.Item.Case;
import Main.mall.Item.Cpu;
import Main.mall.Item.GraphicsCard;
import Main.mall.Item.MainBoard;
import Main.mall.Item.Power;
import Main.mall.Item.Product;
import Main.mall.Item.Ram;
import Main.mall.Item.Storage;
import Main.mgr.Factory;
import Main.mgr.Manageable;
import Main.mgr.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {

    Scanner scan = new Scanner(System.in);
    static Login loginMgr = new Login();

    static Manager userMgr = new Manager();

    static Manager cpuMgr = new Manager();

    static Manager mainboardMgr = new Manager();

    static Manager ramMgr = new Manager();

    static Manager storageMgr = new Manager();

    static Manager graphicsMgr = new Manager();

    static Manager powerMgr = new Manager();

    static Manager caseMgr = new Manager();


    public void run () {
        loadUserData();
        loadProductData();
        searchMenu();
    }

    private void loadUserData() {
        userMgr.readAll("src/Main/mall/input/user.txt", new Factory() {
            @Override
            public Manageable create() {
                return new User();
            }
        });
    }

    private void loadProductData(){
        cpuMgr.readAll("src/Main/mall/input/cpu.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Cpu();
            }
        });
        cpuMgr.printAll();
        mainboardMgr.readAll("src/Main/mall/input/mainboard.txt", new Factory() {
            @Override
            public Manageable create() {
                return new MainBoard();
            }
        });
        mainboardMgr.printAll();
        ramMgr.readAll("src/Main/mall/input/ram.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Ram();
            }
        });
        ramMgr.printAll();
        storageMgr.readAll("src/Main/mall/input/ssd.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Storage();
            }
        });
        storageMgr.printAll();
        graphicsMgr.readAll("src/Main/mall/input/graphicscard.txt", new Factory() {
            @Override
            public Manageable create() {
                return new GraphicsCard();
            }
        });
        graphicsMgr.printAll();
        powerMgr.readAll("src/Main/mall/input/power.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Power();
            }
        });
        powerMgr.printAll();
        caseMgr.readAll("src/Main/mall/input/case.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Case();
            }
        });
        caseMgr.printAll();
    }

    private void searchMenu() {
        int num;
        while (true) {
            System.out.print("(1)그래픽 (2)램 (3)파워 (4)SSD (5)cpu (6)case (7)mainboard (기타) 종료 ");
            num = scan.nextInt();
            switch (num){
                case 1: graphicsMgr.search(scan); break;
                case 2: ramMgr.search(scan); break;
                case 3: powerMgr.search(scan); break;
                case 4: storageMgr.search(scan); break;
                case 5: cpuMgr.search(scan);
                case 6: caseMgr.search(scan);
                case 7: mainboardMgr.search(scan);
                default: return;
            }
        }
    }

    // 전체 상품 조회
    public void getAllItem(){
        System.out.println("전체 상품을 조회합니다.");
    }

    // 상품 추가 등록
    public void addItem(){

    }

    // 상품 수정
    public void updateItem(){

    }

    // 상품 삭제
    public void deleteItem(){

    }

    // 상품 페이지네이션
    public void ItemPagenation(){

    }

    // 회원 리스팅
    public void getUserList(){

    }

    // 회원 결제 내역 조회
    public void getUserPaymentInfo(){

    }

    public void adminPage(){
        if(!Login.currentUser.matchID("admin")){
            System.out.println("Permission denied");
            return;
        }
        //TODO : make manage page for admin
        System.out.println("Hello admin");
    }

}
