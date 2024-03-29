package Main.mgr;

import Main.mall.Item.Product;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Manager{
    //static 빼서 해봤어요 중복 출력돼서
    public ArrayList<Manageable> mList = new ArrayList<>();

    public void readAll(String filename, Factory fac) {
        Scanner filein = openFile(filename);
        Manageable m = null;
        while (filein.hasNext()) {
            m = fac.create();
            m.read(filein);
            mList.add(m);
        }
        filein.close();
    }
    public Scanner openFile(String filename) {
        Scanner filein = null;
        try {
            filein = new Scanner(new File(filename));
        } catch (IOException e)
        {
            System.out.println("파일 입력 오류");
            // exception 출력
            e.printStackTrace();
            System.exit(0);
        }
        return filein;
    }

    public void printAll() {
        for (Manageable m: mList)
            m.print();
    }
    //next에서 nextline으로 수정 제품명 검색때 띄어쓰기별로 나오는걸 수정
    public void search(Scanner scan) {
        String kwd = null;
        while (true) {
            System.out.print(">> ");
            kwd = scan.nextLine();
            if (kwd.isEmpty()){
                continue;
            }
            if (kwd.equals("end"))
                break;
            for (Manageable m : mList) {
                if (m.matches(kwd))
                    m.print();
            }
        }
    }
    public Manageable find(String kwd){
        for (Manageable m: mList) {
            if (m.matches(kwd))
                return m;
        }
        return null;
    }

    public void replace(String kwd, Manageable m){
        Manageable origin = this.find(kwd);
        mList.remove(origin);
        mList.add(m);
    }

    public void delete(Manageable m){
        mList.remove(m);
    }

    public void add(Manageable m){
        mList.add(m);
    }
    
    //검색된 상품을 모두 리스트에 담는 메서드를 추가했음
    public List<Manageable> findAll(String keyword) {
        List<Manageable> matchingList = new ArrayList<>();

        for (Manageable manageable : mList) {
            if (manageable.matches(keyword)) {
                matchingList.add(manageable);
            }
        }
        return matchingList;
    }
}