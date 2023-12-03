package Main.mall;

import Main.mall.Item.*;
import Main.mgr.Manageable;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.event.MonitorWaitedEvent;

import java.io.File;
import java.net.http.HttpRequest;
import java.time.chrono.MinguoChronology;
import java.util.*;

final class TYPE{
    public static final int CPU = 0;
    public static final int RAM = 1;
    public static final int STORAGE = 2;
    public static final int GPU = 3;
    public static final int POW = 4;
    public static final int CASE = 5;
    public static final int MainBoard = 6;
}
final class METHOD{
    public static final int GAME = 1;
    public static final int WORK = 2;
    public static final int OFFICE = 3;
    public static final int HOBBY = 4;
}
final class CHS{
    public static final int MONEY = 0;
    public static final int METHOD = 1;
    public static final int SPEC = 2;
}
public class UserChoiceHandler {
    //게임스펙에 관해서 검색후 모두 리스트에 추가하는 메서드
    //arraylist를 모두 지역변수로 바꿨음 계속 검색하면 같은 list에 출력되는걸 방지
    //부품별로 검색하는걸 나눔 가격 한도 입력해서 그 가격보다 작은 것만 출력하게 수정
    public ArrayList[] recommends(int choices[]){
        /** recommend 리스트 **/
        ArrayList[] recommend = null;
        int money[] = new int[7];

        /*****용도에 따른 가격 분류****/
        if(choices[CHS.METHOD] == METHOD.GAME || choices[CHS.METHOD] == METHOD.WORK){
            money[TYPE.GPU] = (int)(choices[CHS.MONEY] * 0.45);
            money[TYPE.CPU] = (int)(choices[CHS.MONEY] * 0.25);
            money[TYPE.RAM] = (int)(choices[CHS.MONEY] * 0.1);
            money[TYPE.POW] = (int)(choices[CHS.MONEY] * 0.075);
            money[TYPE.STORAGE] = (int)(choices[CHS.MONEY] * 0.05);
            money[TYPE.CASE] = (int)(choices[CHS.MONEY] * 0.05);
            money[TYPE.MainBoard] = (int)(choices[CHS.MONEY] * 0.125);
        }
        else{
            money[TYPE.GPU] = (int)(choices[CHS.MONEY] * 0.0);
            money[TYPE.CPU] = (int)(choices[CHS.MONEY] * 0.5);
            money[TYPE.RAM] = (int)(choices[CHS.MONEY] * 0.11);
            money[TYPE.POW] = (int)(choices[CHS.MONEY] * 0.08);
            money[TYPE.STORAGE] = (int)(choices[CHS.MONEY] * 0.17);
            money[TYPE.CASE] = (int)(choices[CHS.MONEY] * 0.05);
            money[TYPE.MainBoard] = (int)(choices[CHS.MONEY] * 0.13);
        }
        System.out.println(choices[CHS.METHOD]);
        if(choices[CHS.METHOD] == METHOD.GAME){
            String[] game = "롤|오버워치|배틀그라운드|메이플스토리|서든어택".split("\\|");
            recommend =  searchGameSpec(game[(choices[CHS.SPEC])-1],money);
        }
        else if(choices[CHS.METHOD] == METHOD.WORK){
            String[] work = "소프트웨어 개발|게임 개발|그래픽 디자인|도면 설계|웹개발 및 디자인".split("\\|");
            recommend = searchWorkSpec(work[(choices[CHS.SPEC])-1],money);
        }
        else if(choices[CHS.METHOD] == METHOD.OFFICE){
            String[] office = "워드 및 한글|피피티|웹서치".split("\\|");
            System.out.println(office[(choices[CHS.SPEC]-1)]);
            recommend = searchOfficeSpec(office[(choices[CHS.SPEC])-1],money);
        }
        else if(choices[CHS.METHOD] == METHOD.HOBBY){
            String[] hobby = "유튜브 및 OTT|웹서핑".split("\\|");
            recommend = searchHobbySpec(hobby[(choices[CHS.SPEC])-1],money);
        }
        printSelectedList(recommend,money);
        int i=0;

        for(ArrayList<Product> a: recommend){
            Collections.sort(a,Comparator.comparingInt(Product::getPrice));
            Collections.reverse(a);
        }
        return recommend;
    }
    private void addComponentsByCpu(List<Cpu> cpuList, String cpuSpec,int maxPrice) {
            if (cpuSpec != null){
                List<Manageable> cpuSpecList = Admin.cpuMgr.findAll(cpuSpec);
                for (Manageable manageable : cpuSpecList) {
                    Cpu cpu = (Cpu) manageable;
                    if (cpu.getPrice() <= maxPrice) {
                        cpuList.add(cpu);
                    }
                }
            }
        }
    private void addComponentsByRam (List<Ram> ramList, String ramSpec,int maxPrice) {
        if (ramSpec != null) {
            List<Manageable> ramSpecList = Admin.ramMgr.findAll(ramSpec);
            for (Manageable manageable : ramSpecList) {
                Ram ram = (Ram) manageable;
                if (ram.getPrice() <= maxPrice) {
                    ramList.add(ram);
                }
            }
        }
    }
    private void addComponentsByStorage(List<Storage> storageList, String storageSpec,int maxPrice) {
        if (storageSpec != null) {
            List<Manageable> storageSpecList = Admin.storageMgr.findAll(storageSpec);
            for (Manageable manageable : storageSpecList) {
                Storage storage = (Storage) manageable;
                if (storage.getPrice() <= maxPrice) {
                    storageList.add(storage);
                }
            }
        }
    }

    private void addComponentsByGraphicsCard(List<GraphicsCard> graphicsCardList, String graphicsCardSpec,int maxPrice) {
        if (graphicsCardSpec != null) {
            List<Manageable> graphicsCardSpecList = Admin.graphicsMgr.findAll(graphicsCardSpec);
            for (Manageable manageable : graphicsCardSpecList) {
                GraphicsCard graphicsCard = (GraphicsCard) manageable;
                if (graphicsCard.getPrice() <= maxPrice) {
                    graphicsCardList.add(graphicsCard);
                }
            }
        }
    }


    //사용자가 선택한 부품 출력하는 메서드
    private void printSelectedList(ArrayList[] selected,int[] money) {
        ArrayList<Ram> selectedRam = selected[TYPE.RAM];
        ArrayList<GraphicsCard> selectedGraphicsCard = selected[TYPE.GPU];
        int maxMainboardPrice = money[TYPE.MainBoard];
        int maxCasePrice = money[TYPE.CASE];
        int maxPowerSupplyPrice = money[TYPE.POW];

        System.out.println("----------선택된 RAM 제품----------");
        List<String> uniqueRamTypes = new ArrayList<>();
        ArrayList<String> printedMainboards = new ArrayList<>();

        for (Ram ram : selectedRam) {

            String ramType = ram.getType();
            if (!uniqueRamTypes.contains(ramType)) {
                uniqueRamTypes.add(ramType);
            }
        }
        //Ramtype에 맞게 mainboard 타입 선정해서 출력
        System.out.println("----------적합한 Mainboard 제품----------");
        for (String ramType : uniqueRamTypes) {
            List<Manageable> mainboardList = Admin.mainboardMgr.findAll(ramType);
            ArrayList<Manageable> filteredMainboards = new ArrayList<>();
            List<Manageable> boardType = new ArrayList<>();

            for (Manageable manageable : mainboardList) {
                MainBoard mainboard = (MainBoard) manageable;
                if (!printedMainboards.contains(mainboard.getName()) && mainboard.getPrice() <= maxMainboardPrice) {
                    filteredMainboards.add(mainboard);
                }
            }
            ArrayList<Manageable> filteredCases = new ArrayList<>();
            for (Manageable manageable : filteredMainboards) {
                MainBoard mainboard = (MainBoard) manageable;

                if (!printedMainboards.contains(mainboard.getName())) {
                    printedMainboards.add(mainboard.getName());

                    // 1개 출력하고 그것에 맞는 케이스 출력
                    String mainboardSpecification = mainboard.getBoardType();

                    boardType = Admin.caseMgr.findAll(mainboardSpecification);
                    for (Manageable board : boardType) {
                        Case computerCase = (Case) board;
                        if (computerCase.getPrice() <= maxCasePrice && !filteredCases.contains(computerCase)) {
                            filteredCases.add(computerCase);
                        }
                    }
                }
            }

            selected[TYPE.MainBoard] = filteredMainboards;
            selected[TYPE.CASE] = filteredCases;
        }

        ArrayList<Manageable> filteredPowerSupplies = new ArrayList<>();
        //내가 정한 그래픽카드의 power랑 비교해서 정격파워보다 큰것만 출력하게 해줌 중복은 안되게 해줬음
        if (!selectedGraphicsCard.isEmpty()) {
            System.out.println("----------선택된 그래픽카드 제품----------");
            List<String> uniqueGraphicsCardPower = new ArrayList<>();
            List<String> printedPowerSupplies = new ArrayList<>();

            for (GraphicsCard graphicsCard : selectedGraphicsCard) {
                String power = String.valueOf(graphicsCard.getPower());
                if (!uniqueGraphicsCardPower.contains(power)) {
                    uniqueGraphicsCardPower.add(power);
                }
            }

            //파워제품 가격한도 책정
            System.out.println("----------적합한 Power 제품----------");
            for (String power : uniqueGraphicsCardPower) {
                List<Manageable> powerSupplyList = Admin.powerMgr.findAll(power);

                // Filter power supplies based on the maximum price

                for (Manageable manageable : powerSupplyList) {
                    Power powerSupply = (Power) manageable;
                    if (powerSupply.getPrice() <= maxPowerSupplyPrice && !filteredPowerSupplies.contains(powerSupply)) {
                        filteredPowerSupplies.add(powerSupply);
                    }
                }

                // Print the filtered power supplies
                for (Manageable manageable : filteredPowerSupplies) {
                    Power powerSupply = (Power) manageable;

                    // Check if power supply has not been printed before
                    if (!printedPowerSupplies.contains(powerSupply.getName())) {
                        printedPowerSupplies.add(powerSupply.getName());  // Add to the list after printing
                    }
                }
            }

        }
        else{
            ArrayList<Manageable> powerSupplyList = Admin.powerMgr.mList;
            maxPowerSupplyPrice = money[TYPE.POW];
            for(Manageable k : powerSupplyList){
                if(((Power)k).getPrice() <= maxPowerSupplyPrice){
                    filteredPowerSupplies.add(k);
                }
            }
        }
        selected[TYPE.POW] = filteredPowerSupplies;
    }

    //cpu 캐시로 비교하게 변경

    private ArrayList[] searchGameSpec(String game, int[] money) {
        ArrayList[] ret = new ArrayList[7];
        ArrayList<Cpu> cpuArrayList = new ArrayList<>();
        ArrayList<Ram> ramArrayList = new ArrayList<>();
        ArrayList<Storage> storageArrayList = new ArrayList<>();
        ArrayList<GraphicsCard> graphicsCardArrayList = new ArrayList<>();
        ret[TYPE.CPU] = cpuArrayList;
        ret[TYPE.RAM] = ramArrayList;
        ret[TYPE.STORAGE] = storageArrayList;
        ret[TYPE.GPU] = graphicsCardArrayList;
        switch (game) {
            case "롤":
                System.out.println("롤이다다다");
                addComponentsByCpu(cpuArrayList, "16", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "1100", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "400", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "4", money[TYPE.GPU]);
                break;
            case "오버워치":
                addComponentsByCpu(cpuArrayList, "20", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "1500", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "1000", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "6", money[TYPE.GPU]);
                break;
            case "배틀그라운드":
                addComponentsByCpu(cpuArrayList, "24", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "2300", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "700", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "10", money[TYPE.GPU]);
                break;
            case "메이플스토리":
                addComponentsByCpu(cpuArrayList, "3", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "500", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "190", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "1", money[TYPE.GPU]);
                break;
            case "서든어택":
                addComponentsByCpu(cpuArrayList, "7", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "700", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "340", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "2", money[TYPE.GPU]);
                break;
        }
        return ret;
    }
    private ArrayList[] searchWorkSpec(String work, int[] money) {
        ArrayList[] ret = new ArrayList[7];
        ArrayList<Cpu> cpuArrayList = new ArrayList<>();
        ArrayList<Ram> ramArrayList = new ArrayList<>();
        ArrayList<Storage> storageArrayList = new ArrayList<>();
        ArrayList<GraphicsCard> graphicsCardArrayList = new ArrayList<>();
        ret[TYPE.CPU] = cpuArrayList;
        ret[TYPE.RAM] = ramArrayList;
        ret[TYPE.STORAGE] = storageArrayList;
        ret[TYPE.GPU] = graphicsCardArrayList;
        switch (work) {
            case "소프트웨어 개발":
                addComponentsByCpu(cpuArrayList, "12", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "800", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "1200", money[TYPE.STORAGE]);
                break;
            case "게임 개발":
                addComponentsByCpu(cpuArrayList, "14", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "1200", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "1000", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "8", money[TYPE.GPU]);
                break;
            case "그래픽 디자인":
                addComponentsByCpu(cpuArrayList, "22", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "1700", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "1100", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "15", money[TYPE.GPU]);
                break;
            case "도면 설계":
                addComponentsByCpu(cpuArrayList, "18", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "1800", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "700", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "7", money[TYPE.GPU]);
                break;
            case "웹개발 및 디자인":
                addComponentsByCpu(cpuArrayList, "11", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "900", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "500", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "6", money[TYPE.GPU]);
                break;
        }
        return ret;
    }

    private ArrayList[] searchOfficeSpec(String office, int[] money) {
        ArrayList[] ret = new ArrayList[7];
        ArrayList<Cpu> cpuArrayList = new ArrayList<>();
        ArrayList<Ram> ramArrayList = new ArrayList<>();
        ArrayList<Storage> storageArrayList = new ArrayList<>();
        ArrayList<GraphicsCard> graphicsCardArrayList = new ArrayList<>();
        ret[TYPE.CPU] = cpuArrayList;
        ret[TYPE.RAM] = ramArrayList;
        ret[TYPE.STORAGE] = storageArrayList;
        ret[TYPE.GPU] = graphicsCardArrayList;
        switch (office) {
            case "워드 및 한글":
                addComponentsByCpu(cpuArrayList, "3", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "700", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "250", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "4", money[TYPE.GPU]);
                break;
            case "피피티":
                addComponentsByCpu(cpuArrayList, "3", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "500", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "250", money[TYPE.STORAGE]);
                addComponentsByGraphicsCard(graphicsCardArrayList, "4", money[TYPE.GPU]);
                break;
            case "웹서치":
                System.out.println("awawd");
                addComponentsByCpu(cpuArrayList, "3", money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "300", money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "250", money[TYPE.STORAGE]);
                break;
        }
        return ret;
    }

    private ArrayList[] searchHobbySpec(String hobby, int[] money) {
        ArrayList[] ret = new ArrayList[7];
        ArrayList<Cpu> cpuArrayList = new ArrayList<>();
        ArrayList<Ram> ramArrayList = new ArrayList<>();
        ArrayList<Storage> storageArrayList = new ArrayList<>();
        ArrayList<GraphicsCard> graphicsCardArrayList = new ArrayList<>();
        ret[TYPE.CPU] = cpuArrayList;
        ret[TYPE.RAM] = ramArrayList;
        ret[TYPE.STORAGE] = storageArrayList;
        ret[TYPE.GPU] = graphicsCardArrayList;
        switch (hobby){
            case "유튜브 및 OTT":
                addComponentsByCpu(cpuArrayList, "10",money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "600",money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "250",money[TYPE.STORAGE]);
                break;
            case "웹서핑":
                addComponentsByCpu(cpuArrayList, "6",money[TYPE.CPU]);
                addComponentsByRam(ramArrayList, "300",money[TYPE.RAM]);
                addComponentsByStorage(storageArrayList, "250",money[TYPE.STORAGE]);
                break;
        }
        return ret;
    }
}