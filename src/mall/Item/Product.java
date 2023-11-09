package mall.Item;

import java.util.Scanner;
import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class Product implements Manageable {
    Scanner scan = new Scanner(System.in);
    static Manager graphicsMgr = new Manager();
    static Manager ramMgr = new Manager();
    static Manager powerMgr = new Manager();
    static Manager storageMgr = new Manager();
    static Manager cpuMgr = new Manager();
    static Manager mainboardMgr = new Manager();
    static Manager caseMgr = new Manager();
    String name;
    int price;
    @Override
    public void read(Scanner scan){
        price = scan.nextInt();
        name = scan.nextLine();
    }

    @Override
    public void print() {
        System.out.printf("%s %d", name, price);
    }

    @Override
    public boolean matches(String kwd) {
        if (name.equals(kwd))
            return true;
        return false;
    }
    void run() {
        graphicsMgr.readAll("graphicscard.txt", new Factory() {
            @Override
            public Manageable create() {
                return new GraphicsCard();
            }
        });
        graphicsMgr.printAll();
        ramMgr.readAll("ram.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Ram();
            }
        });
        ramMgr.printAll();
        powerMgr.readAll("power.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Power();
            }
        });
        powerMgr.printAll();
        storageMgr.readAll("ssd.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Storage();
            }
        });
        storageMgr.printAll();
        cpuMgr.readAll("cpu.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Cpu();
            }
        });
        cpuMgr.printAll();
        mainboardMgr.readAll("mainboard.txt", new Factory() {
            @Override
            public Manageable create() {
                return new MainBoard();
            }
        });
        mainboardMgr.printAll();
        caseMgr.readAll("case.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Case();
            }
        });
        caseMgr.printAll();
        searchMenu();
    }
    void searchMenu() {
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
                default: break;
            }
        }
    }

}
