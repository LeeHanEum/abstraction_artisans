package Main.mall;

import Main.mall.Item.Cpu;
import Main.mall.Item.GraphicsCard;
import Main.mall.Item.MainBoard;
import Main.mall.Item.Power;
import Main.mall.Item.Ram;
import Main.mall.Item.Storage;
import Main.mgr.Manageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserChoiceHandler {
    //이 전역변수는 선탣된 부품 저장하는 리스트
    ArrayList<Cpu> selectedCpu = new ArrayList<>();
    ArrayList<Ram> selectedRam = new ArrayList<>();
    ArrayList<Storage> selectedStorage = new ArrayList<>();
    ArrayList<GraphicsCard> selectedGraphicsCard = new ArrayList<>();

    private Scanner scanner;
    //게임스펙에 관해서 검색후 모두 리스트에 추가하는 메서드
    //arraylist를 모두 지역변수로 바꿨음 계속 검색하면 같은 list에 출력되는걸 방지
    //부품별로 검색하는걸 나눔 가격 한도 입력해서 그 가격보다 작은 것만 출력하게 수정
    private void addComponentsByCpu(List<Cpu> cpuList, String cpuSpec) {
            System.out.print("CPU 제품 가격 한도를 입력해주세요: ");
            int maxPrice = scanner.nextInt();
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
        private void addComponentsByRam (List<Ram> ramList, String ramSpec) {
            System.out.print("RAM 제품 가격 한도를 입력해주세요: ");
            int maxPrice = scanner.nextInt();
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
    private void addComponentsByStorage(List<Storage> storageList, String storageSpec) {
        System.out.print("SSD 제품 가격 한도를 입력해주세요: ");
        int maxPrice = scanner.nextInt();
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

    private void addComponentsByGraphicsCard(List<GraphicsCard> graphicsCardList, String graphicsCardSpec) {
        System.out.print("그래픽카드 제품 가격 한도를 입력해주세요: ");
        int maxPrice = scanner.nextInt();
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

    //출력하는 메서드 추가 부품별 출력메서드  총 4개
    private void printCpuDetailsList(List<Cpu> cpuList) {
        for (Cpu cpu : cpuList) {
            System.out.println("CPU 제품명: " + cpu.getName() + ", 가격: " + cpu.getPrice() + "원, 상세정보(" + cpu + ")");
        }
    }

    private void printRamDetailsList(List<Ram> ramList) {
        for (Ram ram : ramList) {
            System.out.println("RAM 제품명: " + ram.getName() + ", 가격: " + ram.getPrice() + "원, 상세정보( " + ram + ")");
        }
    }

    private void printStorageDetailsList(List<Storage> storageList) {
        for (Storage storage : storageList) {
            System.out.println("Storage 제품명: " + storage.getName() + ", 가격: " + storage.getPrice() + "원, 상세정보(" + storage + ")");
        }
    }

    private void printGraphicsCardDetailsList(List<GraphicsCard> graphicsCardList) {
        for (GraphicsCard graphicsCard : graphicsCardList) {
            System.out.println("그래픽 카드 제품명: " + graphicsCard.getName() + ", 가격: " + graphicsCard.getPrice() + "원, 상세정보(" + graphicsCard + ")");
        }
    }

    //사용자가 선택한 부품 출력하는 메서드
    private void printSelectedList() {
        System.out.println("----------선택된 CPU 제품----------");
        for (Cpu cpu : selectedCpu) {
            System.out.println(cpu.getName() + ", 가격: " + cpu.getPrice() + "원, 상세정보(" + cpu + ")");
        }

        System.out.println("----------선택된 RAM 제품----------");
        List<String> uniqueRamTypes = new ArrayList<>();
        List<String> printedMainboards = new ArrayList<>();

        for (Ram ram : selectedRam) {
            System.out.println("선택된 RAM 제품: " + ram.getName() + ", 가격: " + ram.getPrice() + "원, 상세정보(" + ram + ")");

            String ramType = ram.getType();
            if (!uniqueRamTypes.contains(ramType)) {
                uniqueRamTypes.add(ramType);
            }
        }
        //Ramtype에 맞게 mainboard 타입 선정해서 출력
        System.out.println("----------적합한 Mainboard 제품----------");
        for (String ramType : uniqueRamTypes) {
            List<Manageable> mainboardList = Admin.mainboardMgr.findAll(ramType);
            List<Manageable> boardType = new ArrayList<>();

            for (Manageable manageable : mainboardList) {
                MainBoard mainboard = (MainBoard) manageable;

                if (!printedMainboards.contains(mainboard.getName())) {
                    System.out.println("\n적합한 Mainboard 제품: " + mainboard.getName() + ", 가격: " + mainboard.getPrice() + "원, 상세정보(" + mainboard + ")");
                    printedMainboards.add(mainboard.getName());
                }
                //1개 출력하고 그것에 맞는 케이스 출력
                String mainboardSpecification = mainboard.getBoardType();
                switch (mainboardSpecification) {
                    case "ATX":
                        System.out.println("-----Mainboard에 맞는 케이스 목록-----");
                        boardType = Admin.mainboardMgr.findAll("ATX");
                        for (Manageable board : boardType)
                            board.print();
                        break;
                    case "M-ATX":
                        System.out.println("-----Mainboard에 맞는 케이스 목록-----");
                        boardType = Admin.mainboardMgr.findAll("M-ATX");
                        for (Manageable board : boardType)
                            board.print();
                        break;
                    case "E-ATX":
                        System.out.println("-----Mainboard에 맞는 케이스 목록-----");
                        boardType = Admin.mainboardMgr.findAll("E-ATX");
                        for (Manageable board : boardType)
                            board.print();
                        break;
                    default:
                        System.out.println("해당하는 규격에 맞는 케이스가 없습니다.");
                }
            }
        }


        System.out.println("----------선택된 SSD 제품----------");
        for (Storage storage : selectedStorage) {
            System.out.println("선택된 SSD 제품: " + storage.getName() + ", 가격: " + storage.getPrice() + "원, 상세정보(" + storage + ")");
        }

        //내가 정한 그래픽카드의 power랑 비교해서 정격파워보다 큰것만 출력하게 해줌 중복은 안되게 해줬음
        if (selectedGraphicsCard != null) {
            System.out.println("----------선택된 그래픽카드 제품----------");
            List<String> uniqueGraphicsCardPower = new ArrayList<>();
            List<String> printedPowerSupplies = new ArrayList<>();

            for (GraphicsCard graphicsCard : selectedGraphicsCard) {
                System.out.println("선택된 그래픽카드 제품: " + graphicsCard.getName() + ", 가격: " + graphicsCard.getPrice() + "원, 상세정보(" + graphicsCard + ")");

                String power = String.valueOf(graphicsCard.getPower());
                if (!uniqueGraphicsCardPower.contains(power)) {
                    uniqueGraphicsCardPower.add(power);
                }
            }
            System.out.println("----------적합한 Power 제품----------");
            for (String power : uniqueGraphicsCardPower) {
                List<Manageable> powerSupplyList = Admin.powerMgr.findAll(power);

                for (Manageable manageable : powerSupplyList) {
                    Power powerSupply = (Power) manageable;

                    // Check if power supply has not been printed before
                    if (!printedPowerSupplies.contains(powerSupply.getName())) {
                        System.out.println("적합한 Power 제품: " + powerSupply.getName() + ", 가격: " + powerSupply.getPrice() + "원, 상세정보(" + powerSupply + ")");
                        printedPowerSupplies.add(powerSupply.getName());  // Add to the list after printing
                    }
                }
            }
        }
    }
    //사용자에게 제품명 정확히 입력받아 반환
    //list가 비었다면 넘어가게 수정
    private void selectComponentCpu(List<Cpu> cpuList) {
        boolean continueSelecting = true;
        System.out.println("가능한 CPU 목록:");
        printCpuDetailsList(cpuList);
        if (cpuList.isEmpty()) {
            System.out.println("현재 선택 가능한 CPU가 없습니다. 다음 단계로 진행합니다.");
            return;
        }
        while (continueSelecting) {

            System.out.print("CPU 제품명을 입력해주세요: ");
            String cpuSpec = scanner.nextLine();

            for (Cpu cpu : cpuList) {
                if (cpu.getName().equals(cpuSpec)) {
                    System.out.println("CPU " + cpuSpec + "가 선택되었습니다.");
                    selectedCpu.add(cpu);
                    break;
                }
            }
            System.out.print("더 선택하시겠습니까? (y/n): ");
            String choice = scanner.nextLine().toLowerCase();

            if (!choice.equals("y")) {
                continueSelecting = false;
            }
        }
    }

    private void selectComponentRam(List<Ram> ramList) {
        boolean continueSelecting = true;
        System.out.println("가능한 Ram 목록:");
        printRamDetailsList(ramList);
        if (ramList.isEmpty()) {
            System.out.println("현재 선택 가능한 SSD가 없습니다. 다음 단계로 진행합니다.");
            return;
        }
        while (continueSelecting) {

            System.out.print("Ram 제품명을 입력해주세요: ");
            String ramSpec = scanner.nextLine();

            for (Ram ram : ramList) {
                if (ram.getName().equals(ramSpec)) {
                    System.out.println("Ram " + ramSpec + "가 선택되었습니다.");
                    selectedRam.add(ram);
                    break;
                }
            }

            System.out.print("더 선택하시겠습니까? (y/n): ");
            String choice = scanner.nextLine().toLowerCase();

            if (!choice.equals("y")) {
                continueSelecting = false;
            }
        }
    }

    private void selectComponentStorage(List<Storage> storageList) {
        boolean continueSelecting = true;
        System.out.println("가능한 Storage 목록:");
        printStorageDetailsList(storageList);
        if (storageList.isEmpty()) {
            System.out.println("현재 선택 가능한 SSD가 없습니다. 다음 단계로 진행합니다.");
            return;
        }
        while (continueSelecting) {

            System.out.print("Storage 제품명을 입력해주세요: ");
            String storageSpec = scanner.nextLine();

            for (Storage storage : storageList) {
                if (storage.getName().equals(storageSpec)) {
                    System.out.println("Storage " + storageSpec + "가 선택되었습니다.");
                    selectedStorage.add(storage);
                    break;
                }
            }

            System.out.print("더 선택하시겠습니까? (y/n): ");
            String choice = scanner.nextLine().toLowerCase();

            if (!choice.equals("y")) {
                continueSelecting = false;
            }
        }
    }
    private void selectComponentGraphicsCard(List<GraphicsCard> graphicsCardList) {
        boolean continueSelecting = true;
        System.out.println("가능한 Graphics Card 목록:");
        printGraphicsCardDetailsList(graphicsCardList);
        if (graphicsCardList.isEmpty()) {
            System.out.println("현재 선택 가능한 그래픽카드가 없습니다. 다음 단계로 진행합니다.");
            return;
        }
        while (continueSelecting) {

            System.out.print("Graphics Card 제품명을 입력해주세요: ");
            String graphicsCardSpec = scanner.nextLine();

            for (GraphicsCard graphicsCard : graphicsCardList) {
                if (graphicsCard.getName().equals(graphicsCardSpec)) {
                    System.out.println("Graphics Card " + graphicsCardSpec + "가 선택되었습니다.");
                    selectedGraphicsCard.add(graphicsCard);
                    break;
                }
            }
            System.out.print("더 선택하시겠습니까? (y/n): ");
            String choice = scanner.nextLine().toLowerCase();

            if (!choice.equals("y")) {
                continueSelecting = false;
            }
        }
    }

    public UserChoiceHandler() {
        this.scanner = new Scanner(System.in);
    }
    //cpu 캐시로 비교하게 변경
    public void handleUserChoice() {
        while (true) {
            System.out.println("1. 게임용으로 검색");
            System.out.println("2. 작업용으로 검색");
            System.out.println("3. 사무용으로 검색");
            System.out.println("4. 취미용으로 검색");
            System.out.print("사용자 선택: ");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    handleGameSearch();
                    break;
                case 2:
                    handleWorkSearch();
                    break;
                case 3:
                    handleOfficeSearch();
                    break;
                case 4:
                    handlehobbySearch();
                    break;
                default:
                    System.out.println("올바른 선택이 아닙니다. 프로그램을 종료합니다.");
                    break;
            }
        }
    }
    private void handleGameSearch() {
        System.out.print("게임을 입력해주세요: ");
        String game = scanner.next();
        switch (game) {
            case "롤":
                searchGameSpec("롤");
                break;
            case "오버워치":
                searchGameSpec("오버워치");
                break;
            case "배틀그라운드":
                searchGameSpec("배틀그라운드");
                break;
            case "메이플스토리":
                searchGameSpec("메이플스토리");
                break;
            case "서든어택":
                searchGameSpec("서든어택");
                break;
            default: return;
        }
        askUserChoice();
    }

    private void handleWorkSearch() {
        System.out.print("작업하시는 용도를 입력해주세요: ");
        String work = scanner.nextLine();
        switch (work) {
            case "소프트웨어 개발":
                searchWorkSpec("소프트웨어 개발");
                break;
            case "게임 개발":
                searchWorkSpec("게임 개발");
                break;
            case "그래픽 디자인":
                searchWorkSpec("그래픽 디자인");
                break;
            case "도면 설계":
                searchWorkSpec("도면 설계");
                break;
            case "웹개발 및 디자인":
                searchWorkSpec("웹개발 및 디자인");
                break;
            default: return;
        }
        askUserChoice();
    }

    private void handleOfficeSearch() {
        System.out.print("주로 작업하는 사무 용도를 입력해주세요: ");
        String office = scanner.nextLine();
        switch (office) {
            case "워드 및 한글":
                searchOfficeSpec("워드 및 한글");
                break;
            case "피피티":
                searchOfficeSpec("피피티");
                break;
            case "웹서치":
                searchOfficeSpec("웹서치");
                break;
            default: return;
        }
        askUserChoice();
    }

    private void handlehobbySearch() {
        System.out.print("취미로 하는 활동을 입력해주세요: ");
        String hobby = scanner.next();
        switch (hobby) {
            case "유튜브 및 OTT":
                searchHobbySpec("유튜브 및 OTT");
                break;
            case "웹서핑":
                searchHobbySpec("웹서핑");
                break;
            default: return;
        }
        askUserChoice();
    }
    private void searchGameSpec(String game) {
        ArrayList<Cpu> cpuArrayList = new ArrayList<>();
        ArrayList<Ram> ramArrayList = new ArrayList<>();
        ArrayList<Storage> storageArrayList = new ArrayList<>();
        ArrayList<GraphicsCard> graphicsCardArrayList = new ArrayList<>();
        switch (game){
            case "롤":
                addComponentsByCpu(cpuArrayList, "16");
                addComponentsByRam(ramArrayList, "1100");
                addComponentsByStorage(storageArrayList, "400");
                addComponentsByGraphicsCard(graphicsCardArrayList, "4");
                break;
            case "오버워치":
                addComponentsByCpu(cpuArrayList, "20");
                addComponentsByRam(ramArrayList, "1500");
                addComponentsByStorage(storageArrayList, "1000");
                addComponentsByGraphicsCard(graphicsCardArrayList, "6");
                break;
            case "배틀그라운드":
                addComponentsByCpu(cpuArrayList, "24");
                addComponentsByRam(ramArrayList, "2300");
                addComponentsByStorage(storageArrayList, "700");
                addComponentsByGraphicsCard(graphicsCardArrayList, "10");
                break;
            case "메이플스토리":
                addComponentsByCpu(cpuArrayList, "3");
                addComponentsByRam(ramArrayList, "500");
                addComponentsByStorage(storageArrayList, "190");
                addComponentsByGraphicsCard(graphicsCardArrayList, "1");
                break;
            case "서든어택":
                addComponentsByCpu(cpuArrayList, "7");
                addComponentsByRam(ramArrayList, "700");
                addComponentsByStorage(storageArrayList, "340");
                addComponentsByGraphicsCard(graphicsCardArrayList, "2");
                break;
        }
        System.out.print("부품마다 선택할 제품명을 입력해주세요 ");
        scanner.nextLine();
        selectComponentCpu(cpuArrayList);
        selectComponentRam(ramArrayList);
        selectComponentStorage(storageArrayList);
        selectComponentGraphicsCard(graphicsCardArrayList);
    }
    private void searchWorkSpec(String work) {
        ArrayList<Cpu> cpuArrayList = new ArrayList<>();
        ArrayList<Ram> ramArrayList = new ArrayList<>();
        ArrayList<Storage> storageArrayList = new ArrayList<>();
        ArrayList<GraphicsCard> graphicsCardArrayList = new ArrayList<>();
        switch (work){
            case "소프트웨어 개발":
                addComponentsByCpu(cpuArrayList, "12");
                addComponentsByRam(ramArrayList, "800");
                addComponentsByStorage(storageArrayList, "1200");
                break;
            case "게임 개발":
                addComponentsByCpu(cpuArrayList, "14");
                addComponentsByRam(ramArrayList, "1200");
                addComponentsByStorage(storageArrayList, "1000");
                addComponentsByGraphicsCard(graphicsCardArrayList, "8");
                break;
            case "그래픽 디자인":
                addComponentsByCpu(cpuArrayList, "22");
                addComponentsByRam(ramArrayList, "1700");
                addComponentsByStorage(storageArrayList, "1100");
                addComponentsByGraphicsCard(graphicsCardArrayList, "15");
                break;
            case "도면 설계":
                addComponentsByCpu(cpuArrayList, "18");
                addComponentsByRam(ramArrayList, "1800");
                addComponentsByStorage(storageArrayList, "700");
                addComponentsByGraphicsCard(graphicsCardArrayList, "7");
                break;
            case "웹개발 및 디자인":
                addComponentsByCpu(cpuArrayList, "11");
                addComponentsByRam(ramArrayList, "900");
                addComponentsByStorage(storageArrayList, "500");
                addComponentsByGraphicsCard(graphicsCardArrayList, "6");
                break;
        }
            System.out.println("부품마다 선택할 제품명을 입력해주세요 ");
            scanner.nextLine();
            selectComponentCpu(cpuArrayList);
            selectComponentRam(ramArrayList);
            selectComponentStorage(storageArrayList);
            selectComponentGraphicsCard(graphicsCardArrayList);
        }

    private void searchOfficeSpec(String office) {
        ArrayList<Cpu> cpuArrayList = new ArrayList<>();
        ArrayList<Ram> ramArrayList = new ArrayList<>();
        ArrayList<Storage> storageArrayList = new ArrayList<>();
        ArrayList<GraphicsCard> graphicsCardArrayList = new ArrayList<>();
        switch (office){
            case "워드 및 한글":
                addComponentsByCpu(cpuArrayList, "3");
                addComponentsByRam(ramArrayList, "700");
                addComponentsByStorage(storageArrayList, "250");
                addComponentsByGraphicsCard(graphicsCardArrayList, "4");
                break;
            case "피피티":
                addComponentsByCpu(cpuArrayList, "3");
                addComponentsByRam(ramArrayList, "500");
                addComponentsByStorage(storageArrayList, "250");
                addComponentsByGraphicsCard(graphicsCardArrayList, "4");
                break;
            case "웹서치":
                addComponentsByCpu(cpuArrayList, "3");
                addComponentsByRam(ramArrayList, "300");
                addComponentsByStorage(storageArrayList, "250");
                break;
        }
        System.out.println("부품마다 선택할 제품명을 입력해주세요 ");
        scanner.nextLine();
        selectComponentCpu(cpuArrayList);
        selectComponentRam(ramArrayList);
        selectComponentStorage(storageArrayList);
        selectComponentGraphicsCard(graphicsCardArrayList);
    }

    private void searchHobbySpec(String hobby) {
        ArrayList<Cpu> cpuArrayList = new ArrayList<>();
        ArrayList<Ram> ramArrayList = new ArrayList<>();
        ArrayList<Storage> storageArrayList = new ArrayList<>();
        ArrayList<GraphicsCard> graphicsCardArrayList = new ArrayList<>();
        switch (hobby){
            case "유튜브 및 OTT":
                addComponentsByCpu(cpuArrayList, "10");
                addComponentsByRam(ramArrayList, "600");
                addComponentsByStorage(storageArrayList, "250");
                break;
            case "웹서핑":
                addComponentsByCpu(cpuArrayList, "6");
                addComponentsByRam(ramArrayList, "300");
                addComponentsByStorage(storageArrayList, "250");
                break;
        }
        System.out.println("부품마다 선택할 제품명을 입력해주세요 ");
        scanner.nextLine();
        selectComponentCpu(cpuArrayList);
        selectComponentRam(ramArrayList);
        selectComponentStorage(storageArrayList);
        selectComponentGraphicsCard(graphicsCardArrayList);
    }

    //여기선 위에 게임과 별개의 arraylist 사용해서 중복을 피했음
    public void askUserChoice(){
        ArrayList<Ram> ramArrayList = new ArrayList<>();
        ArrayList<Storage> storageArrayList = new ArrayList<>();
        System.out.println("사용자 선택");
        System.out.print("큰 용량이 필요한가요? (Y/N): ");

        String capacityChoice = scanner.next().toUpperCase();
        if ("Y".equals(capacityChoice)) {
            addComponentsByStorage(storageArrayList, "1000");
            scanner.nextLine();
            selectComponentStorage(storageArrayList);
        }


        System.out.print("빠른 속도가 필요한가요? (Y/N): ");
        String speedChoice = scanner.next().toUpperCase();
        if ("Y".equals(speedChoice)) {
            addComponentsByRam(ramArrayList, "3200");
            scanner.nextLine();
            selectComponentRam(ramArrayList);
        }
        printSelectedList();
    }
}