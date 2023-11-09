package mall.Item;

import java.util.Scanner;
import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class Product implements Manageable {
    Scanner scan = new Scanner(System.in);
    static Manager graphicsmgr = new Manager();
    static Manager rammgr = new Manager();
    static Manager powermgr = new Manager();
    static Manager storagemgr = new Manager();
    static Manager cpumgr = new Manager();
    static Manager mainboardmgr = new Manager();
    static Manager casemgr = new Manager();
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
        graphicsmgr.readAll("graphicscard.txt", new Factory() {
            @Override
            public Manageable create() {
                return new GraphicsCard();
            }
        });
        graphicsmgr.printAll();
        rammgr.readAll("ram.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Ram();
            }
        });
        rammgr.printAll();
        powermgr.readAll("power.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Power();
            }
        });
        powermgr.printAll();
        storagemgr.readAll("ssd.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Storage();
            }
        });
        storagemgr.printAll();
        cpumgr.readAll("cpu.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Cpu();
            }
        });
        cpumgr.printAll();
        mainboardmgr.readAll("mainboard.txt", new Factory() {
            @Override
            public Manageable create() {
                return new MainBoard();
            }
        });
        mainboardmgr.printAll();
        casemgr.readAll("case.txt", new Factory() {
            @Override
            public Manageable create() {
                return new Case();
            }
        });
        casemgr.printAll();
        searchMenu();
    }
    void searchMenu() {
        int num;
        while (true) {
            System.out.print("(1)그래픽 (2)램 (3)파워 (4)SSD (5)cpu (6)case (7)mainboard (기타) 종료 ");
            num = scan.nextInt();
            switch (num){
                case 1: graphicsmgr.search(scan); break;
                case 2: rammgr.search(scan); break;
                case 3: powermgr.search(scan); break;
                case 4: storagemgr.search(scan); break;
                case 5: cpumgr.search(scan);
                case 6: casemgr.search(scan);
                case 7: mainboardmgr.search(scan);
                default: break;
            }
        }
    }

    public static void main(String args[]) {
        Product my = new Product();
        my.run();
    }
}
