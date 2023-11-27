package Main.mall;

import Main.mgr.Manager;
import Main.mgr.Manageable;

import java.util.Scanner;

public class Login {
    public static User currentUser;

    //아이디와 비밀번호를 입력받고 해당 객체를 리턴함.
    public User login(String userId, String password){

        User usr = findUserByID(userId);
        if(usr == null || !usr.matchPW(password)){
            return null;
        }
        this.currentUser = usr;
        return usr;
    }

    //해당 ID를 가진 User 객체를 반환.
    public static User findUserByID(String inputID) {
        for (Manageable T : Admin.userMgr.mList) {
            User usr = (User) T;
            if (usr.matchID(inputID)) {
                return usr;
            }
        }
        return null;
    }

    public static void logout(){
        currentUser = null;
    }
}