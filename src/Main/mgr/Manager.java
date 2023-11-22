package Main.mgr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    public void search(Scanner scan) {
        String kwd = null;
        while (true) {
            System.out.print(">> ");
            kwd = scan.next();
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
}