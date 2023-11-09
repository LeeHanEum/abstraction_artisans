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
    Manager cartList;
    Manager interList;

    public void read(Scanner scan){

    }
    public void print(){

    }
    public boolean matches(String kwd){
        return false;
    }
}
