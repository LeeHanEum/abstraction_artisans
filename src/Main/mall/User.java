package Main.mall;

import Main.mall.dto.UserDto;
import Main.mgr.Manageable;
import Main.mgr.Manager;

import java.util.ArrayList;
import java.util.Scanner;

public class User implements Manageable{
    private String id;
    private String pw;
    private String name;
    private String tel;
    private String address;

    private Cart cart;

    private Interest interest;

    private ArrayList[] recommend;

    static Manager paymentMgr = new Manager();
    //interest 클래스 불러오기
    @Override
    public void read(Scanner scan) {
        id = scan.next();
        pw = Password.encrypt(scan.next());
        name = scan.next();
        tel = scan.next();
        address = scan.next();
        cart = new Cart(this);
        interest = new Interest(this);
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

    //interest 부르는 메서드 추가
    public Interest getInterest(){
        return interest;
    }

    public ArrayList[] getRecommend() {
        return recommend;
    }

    public void setRecommend(ArrayList[] recommend){
        this.recommend = recommend;
    }

    public UserDto getUserInfo(){
        return UserDto.builder(this);
    }

    public void setUserInfo(UserDto userDto){
        this.name = userDto.getName();
        this.tel = userDto.getTel();
        this.address = userDto.getAddress();
    }

    public static User builder(UserDto userDto){
        User user = new User();
        user.name = userDto.getName();
        user.tel = userDto.getTel();
        user.address = userDto.getAddress();
        return user;
    }
}