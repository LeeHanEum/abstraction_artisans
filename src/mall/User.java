package mall;

import mgr.Manageable;

import java.util.Scanner;

public class User implements Manageable{
    private String id;
    private String pw;
    private String name;
    private String tel;
    private String address;

    @Override
    public void read(Scanner scan) {
        id = scan.next();
        pw = scan.next();
        name = scan.next();
        tel = scan.next();
        address = scan.next();
    }
    @Override
    public boolean matches(String kwd) {
        if (id.equals(kwd))
            return true;
        if (name.equals(kwd))
            return true;
        if (tel.contains(kwd))
            return true;
        if (address.contains(kwd))
            return true;
        return false;
    }
    @Override
    public void print() {
        System.out.format("[%s] (%s) %s %s (%s)\n", id, pw, name, tel, address);
    }

}