package Main.mall;

import Main.mall.Item.Cpu;
import Main.mall.Item.GraphicsCard;
import Main.mall.Item.Ram;
import Main.mall.Item.Storage;
import Main.mgr.Manageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserChoiceHandler {
    ArrayList<Cpu> cpuArrayList = new ArrayList<>();
    ArrayList<Ram> ramArrayList = new ArrayList<>();
    ArrayList<Storage> storageArrayList = new ArrayList<>();
    ArrayList<GraphicsCard> graphicsCardArrayList = new ArrayList<>();
    private Scanner scanner;
    //게임스펙에 관해서 검색후 모두 리스트에 추가하는 메서드
    private void addComponentsBySpec(String cpuSpec, String ramSpec, String storageSpec, String graphicsCardSpec) {
        if (cpuSpec != null){
        List<Manageable> cpuSpecList = Admin.cpuMgr.findAll(cpuSpec);
        for (Manageable manageable : cpuSpecList) {
            cpuArrayList.add((Cpu) manageable);
            }
        }
        if (ramSpec != null) {
            List<Manageable> ramSpecList = Admin.ramMgr.findAll(ramSpec);
            for (Manageable manageable : ramSpecList) {
                ramArrayList.add((Ram) manageable);
            }
        }
        if (storageSpec != null) {
            List<Manageable> storageSpecList = Admin.storageMgr.findAll(storageSpec);
            for (Manageable manageable : storageSpecList) {
                storageArrayList.add((Storage) manageable);
            }
        }
        if (graphicsCardSpec != null){
        List<Manageable> graphicsCardSpecList = Admin.graphicsMgr.findAll(graphicsCardSpec);
        for (Manageable manageable : graphicsCardSpecList) {
            graphicsCardArrayList.add((GraphicsCard) manageable);
            }
        }
    }
    //출력하는 메서드 추가 부품별 출력메서드  총 4개
    private void printCpuDetailsList(List<Cpu> cpuList) {
        for (Cpu cpu : cpuList) {
            System.out.println("CPU 제품명: " + cpu.getName() + ", 가격: " + cpu.getPrice() + " 상세정보: " + cpu);
        }
    }

    private void printRamDetailsList(List<Ram> ramList) {
        for (Ram ram : ramList) {
            System.out.println("RAM 제품명: " + ram.getName() + ", 가격: " + ram.getPrice() + " 상세정보: " + ram);
        }
    }

    private void printStorageDetailsList(List<Storage> storageList) {
        for (Storage storage : storageList) {
            System.out.println("Storage 제품명: " + storage.getName() + ", 가격: " + storage.getPrice() + " 상세정보: " + storage);
        }
    }

    private void printGraphicsCardDetailsList(List<GraphicsCard> graphicsCardList) {
        for (GraphicsCard graphicsCard : graphicsCardList) {
            System.out.println("그래픽 카드 제품명: " + graphicsCard.getName() + ", 가격: " + graphicsCard.getPrice() + " 상세정보: " + graphicsCard);
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
        }
        askUserChoice();
    }
    private void searchGameSpec(String game) {
        switch (game){
            case "롤":
                addComponentsBySpec("16", "1100", "400", "4");
                break;
            case "오버워치":
                addComponentsBySpec("20", "1500", "1000", "6");
                break;
            case "배틀그라운드":
                addComponentsBySpec("24", "2300", "700", "10");
                break;
            case "메이플스토리":
                addComponentsBySpec("3", "500", "190", "1");
                break;
            case "서든어택":
                addComponentsBySpec("7", "700", "340", "2");
                break;
        }

        //제품명하고 가격도 같이 나오게 출력
        printCpuDetailsList(cpuArrayList);
        printRamDetailsList(ramArrayList);
        printStorageDetailsList(storageArrayList);
        printGraphicsCardDetailsList(graphicsCardArrayList);

    }
    private void searchWorkSpec(String work) {
        switch (work){
            case "소프트웨어 개발":
                addComponentsBySpec("12", "800", "1200", null);
                break;
            case "게임 개발":
                addComponentsBySpec("14", "1200", "1000", "8");
                break;
            case "그래픽 디자인":
                addComponentsBySpec("22", "1700", "1100", "15");
                break;
            case "도면 설계":
                addComponentsBySpec("18", "1800", "700", "7");
                break;
            case "웹개발 및 디자인":
                addComponentsBySpec("11", "900", "500", "6");
                break;
        }
        printCpuDetailsList(cpuArrayList);
        printRamDetailsList(ramArrayList);
        printStorageDetailsList(storageArrayList);
        printGraphicsCardDetailsList(graphicsCardArrayList);

    }

    private void searchOfficeSpec(String office) {
        switch (office){
            case "워드 및 한글":
                addComponentsBySpec("4", "700", "250", null);
                break;
            case "피피티":
                addComponentsBySpec("6", "500", "250", null);
                break;
            case "웹서치":
                addComponentsBySpec("6", "300", "250", null);
                break;
        }
        printCpuDetailsList(cpuArrayList);
        printRamDetailsList(ramArrayList);
        printStorageDetailsList(storageArrayList);
    }

    private void searchHobbySpec(String hobby) {
        switch (hobby){
            case "유튜브 및 OTT":
                addComponentsBySpec("10", "600", "250", null);
                break;
            case "웹서핑":
                addComponentsBySpec("6", "300", "250", null);
                break;
        }
        printCpuDetailsList(cpuArrayList);
        printRamDetailsList(ramArrayList);
        printStorageDetailsList(storageArrayList);
    }
    //추가적인 용량과 더 빠른 속도를 체크하기 위한 질문 가격에 관해서 스펙을 짜주는건 너무 방대하다고 생각함
    //가격만 입력해서 해주기엔 무리같아서 제품 추천해주고 가격은 소비자가 고르는걸로 해도 무관하지 않나 생각중
    //여기선 위에 게임과 별개의 arraylist 사용해서 중복을 피했음
    public void askUserChoice(){
        ArrayList<Ram> ramArrayList1 = new ArrayList<>();
        ArrayList<Storage> storageArrayList1 = new ArrayList<>();
        System.out.println("사용자 선택");
        System.out.print("큰 용량이 필요한가요? (Y/N): ");

        String capacityChoice = scanner.next().toUpperCase();
        if ("Y".equals(capacityChoice)) {
            List<Manageable> storageSpecList = Admin.storageMgr.findAll("1000");
            for (Manageable manageable : storageSpecList) {
                storageArrayList1.add((Storage) manageable);
            }
        }
        for (Storage storage : storageArrayList1) {
            System.out.println("Storage 제품명: " + storage.getName() + ", 가격: " + storage.getPrice() + " 상세정보(" + storage + ")");
        }
        System.out.print("빠른 속도가 필요한가요? (Y/N): ");
        String speedChoice = scanner.next().toUpperCase();
        if ("Y".equals(speedChoice)) {
            List<Manageable> ramSpecList = Admin.ramMgr.findAll("3200");
            for (Manageable manageable : ramSpecList) {
                ramArrayList1.add((Ram) manageable);
            }
        }
        for (Ram ram : ramArrayList1) {
            System.out.println("RAM 제품명: " + ram.getName() + ", 가격: " + ram.getPrice() + " 상세정보: " + ram);
        }
    }
}
