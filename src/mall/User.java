package mall;

import mgr.Manageable;

import java.util.ArrayList;
import java.util.Scanner;

public class User implements Manageable{
    private String id;
    private String pwd;
    private String name;
    private String tel;
    private String address;
    static ArrayList<Manageable> userList = new ArrayList<>();
    @Override
    public void read(Scanner scan) {
        id = scan.next();
        pwd = scan.next();
        name = scan.next();
        tel = scan.next();
        address = scan.next();
    }
    @Override
    public boolean matches(String kwd) {
        if (id.equals(kwd))
            return true;
        return false;
    }
    @Override
    public void print() {
        System.out.format("[%s] (%s) %s %s (%s)\n", id, pwd, name, tel, address);
    }


}