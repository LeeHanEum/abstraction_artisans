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

    //출력을 시키지않게 바꿨습니다
    private void loadProductData(){
        cpuMgr.readAll("src/Main/mall/input/cpu.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Cpu();
            }
        });
        mainboardMgr.readAll("src/Main/mall/input/mainboard.txt", new Factory() {
            @Override
            public Manageable create() {
                return new MainBoard();
            }
        });
        ramMgr.readAll("src/Main/mall/input/ram.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Ram();
            }
        });
        storageMgr.readAll("src/Main/mall/input/ssd.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Storage();
            }
        });
        graphicsMgr.readAll("src/Main/mall/input/graphicscard.txt", new Factory() {
            @Override
            public Manageable create() {
                return new GraphicsCard();
            }
        });
        powerMgr.readAll("src/Main/mall/input/power.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Power();
            }
        });
        caseMgr.readAll("src/Main/mall/input/case.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Case();
            }
        });
    }

    //여기서 출력을 한 품목씩 나눌 수 있게 해줬습니다(카테고리필터링)
    //검색도 입력한 부품에 따라 검색할 수 있게 만들었습니다(상품검색)
    //상품 상세보기는 이미 모든 정보가 출력돼서 따로 안 만들었습니다
    private void searchMenu() {
        int num;
        String kwd;
        while (true) {
            System.out.print("(1)그래픽 (2)램 (3)파워 (4)SSD (5)cpu (6)case (7)mainboard (8) ALL (9) 검색 (기타) 종료 ");
            num = scan.nextInt();
            switch (num){
                case 1: graphicsMgr.printAll(); break;
                case 2: ramMgr.printAll(); break;
                case 3:powerMgr.printAll(); break;
                case 4: storageMgr.printAll(); break;
                case 5: cpuMgr.printAll(); break;
                case 6: caseMgr.printAll(); break;
                case 7: mainboardMgr.printAll(); break;
                case 8: getAllItem(); break; //getAlllItem 으로 수정했습니다.
                case 9:
                    System.out.print("검색할 품목을 입력해주세요: ");
                    kwd = scan.next();
                    if (kwd.equals("그래픽카드")) graphicsMgr.search(scan);
                    else if (kwd.equals("램")) ramMgr.search(scan);
                    else if (kwd.equals("파워")) powerMgr.search(scan);
                    else if (kwd.equals("SSD")) storageMgr.search(scan);
                    else if (kwd.equals("cpu")) cpuMgr.search(scan);
                    else if (kwd.equals("case")) caseMgr.search(scan);
                    else if (kwd.equals("메인보드")) mainboardMgr.search(scan); break;
                default: return;
            }
        }
    }

    // 전체 상품 조회
    // 전체 상품을 print 해주게 하였습니다. 어제 만든게 이거 아닌가싶네요
    public ArrayList<Manageable> getAllItem(){
        ArrayList<Manageable> allList = new ArrayList<>();
        allList.addAll(cpuMgr.mList);
        allList.addAll(mainboardMgr.mList);
        allList.addAll(ramMgr.mList);
        allList.addAll(storageMgr.mList);
        allList.addAll(graphicsMgr.mList);
        allList.addAll(powerMgr.mList);
        allList.addAll(caseMgr.mList);
        return allList;

//        System.out.println("전체 상품을 조회합니다.");
//        cpuMgr.printAll();
//        mainboardMgr.printAll();
//        ramMgr.printAll();
//        storageMgr.printAll();
//        graphicsMgr.printAll();
//        powerMgr.printAll();
//        caseMgr.printAll();
    }

    // 상품 추가 등록
    public void addItem(){
        System.out.print("추가할 품목을 입력해주세요: ");
        String kwd = scan.next();

        Manager mgr = null;

        if (kwd.equals("그래픽카드")) mgr = graphicsMgr;
        else if (kwd.equals("램")) mgr = ramMgr;
        else if (kwd.equals("파워")) mgr = powerMgr;
        else if (kwd.equals("SSD")) mgr = storageMgr;
        else if (kwd.equals("cpu")) mgr = cpuMgr;
        else if (kwd.equals("case")) mgr = caseMgr;
        else if (kwd.equals("메인보드")) mgr = mainboardMgr;
        else {System.out.println("옳지 않은 품목입니다."); return;}

        Product m = new Product();
        System.out.println("추가할 제품의 정보를 입력해주세요.");
        m.read(scan);
        mgr.add(m);
    }

    // 상품 수정
    public void updateItem(Scanner scan){
        System.out.print("수정할 품목을 입력해주세요: ");
        String kwd = scan.next();

        Manager mgr = null;

        if (kwd.equals("그래픽카드")) mgr = graphicsMgr;
        else if (kwd.equals("램")) mgr = ramMgr;
        else if (kwd.equals("파워")) mgr = powerMgr;
        else if (kwd.equals("SSD")) mgr = storageMgr;
        else if (kwd.equals("cpu")) mgr = cpuMgr;
        else if (kwd.equals("case")) mgr = caseMgr;
        else if (kwd.equals("메인보드")) mgr = mainboardMgr;
        else {System.out.println("옳지 않은 품목입니다."); return;}

        System.out.print("수정할 제품 이름을 선택해주세요");
        String kwd1 = scan.next();
        Product m;

        m = (Product) mgr.find(kwd1); //nullable

        if (m == null) {System.out.println("해당 제품을 찾을 수 없습니다."); return;}

        System.out.print("무엇을 바꾸시겠습니까? (1) 이름 (2) 가격 : ");
        String chs = scan.next();
        String newKwd;

        if(chs.contains("1")){
            System.out.print("이름을 정해주세요 : ");
            newKwd = scan.next();
        }
        else{
            System.out.print("가격을 정해주세요 : ");
            newKwd = scan.next();
        }

        m.modify(newKwd);
        mgr.replace(kwd1,m);

    }

    // 상품 삭제
    public void deleteItem(){
        System.out.print("삭제할 품목을 입력해주세요: ");
        String kwd = scan.next();

        Manager mgr = null;

        if (kwd.equals("그래픽카드")) mgr = graphicsMgr;
        else if (kwd.equals("램")) mgr = ramMgr;
        else if (kwd.equals("파워")) mgr = powerMgr;
        else if (kwd.equals("SSD")) mgr = storageMgr;
        else if (kwd.equals("cpu")) mgr = cpuMgr;
        else if (kwd.equals("case")) mgr = caseMgr;
        else if (kwd.equals("메인보드")) mgr = mainboardMgr;
        else {System.out.println("옳지 않은 품목입니다."); return;}

        System.out.print("삭제할 제품 이름을 선택해주세요");
        String kwd1 = scan.next();
        Product m;

        m = (Product) mgr.find(kwd1); //nullable

        if (m == null) {System.out.println("해당 제품을 찾을 수 없습니다."); return;}

        mgr.delete(m);
    }

    // 상품 페이지네이션
    public void ItemPagenation(){

    }

    // 회원 리스팅
    public ArrayList<Manageable> getUserList(){
        return userMgr.mList;
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