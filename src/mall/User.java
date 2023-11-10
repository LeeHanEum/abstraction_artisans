package mall;

import mgr.Manageable;
import mgr.Manager;

import java.util.Scanner;

public class User implements Manageable {
    String id;
    String pw;
    String userName;
    String phoneNum;
    String address;
    Cart cart;
    //Interest interest;

    public void read(Scanner scan){

    }
    public void print(){
        System.out.printf("%s | %s | %s | %s | %s\n", id, pw, userName, phoneNum, address);
    }
    public boolean matches(String kwd){

        return false;
    }
    public boolean matchID(String kwd){
        return id.equals(kwd);
    }
    public boolean matchPW(String kwd){
        return pw.equals(kwd);
    }
}
