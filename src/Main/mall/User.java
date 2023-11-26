package Main.mall;

import Main.mall.dto.UserDto;
import Main.mgr.Manageable;
import Main.mgr.Manager;

import java.util.Scanner;

public class User implements Manageable{
    private String id;
    private String pw;
    private String name;
    private String tel;
    private String address;

    private Cart cart;

    static Manager interestMgr = new Manager();

    static Manager paymentMgr = new Manager();

    @Override
    public void read(Scanner scan) {
        id = scan.next();
        pw = Password.encrypt(scan.next());
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
    public boolean matchID(String kwd){
        return id.equals(kwd);
    }
    public boolean matchPW(String kwd){
        return Password.encrypt(kwd).equals(pw);
    }
    @Override
    public void print() {
        System.out.format("[%s] (%s) %s %s (%s)\n", id, pw, name, tel, address);
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public Cart getCart() {
        return cart;
    }

    public static User builder(UserDto userDto){
        User user = new User();
        user.name = userDto.getName();
        user.tel = userDto.getTel();
        user.address = userDto.getAddress();
        return user;
    }
}