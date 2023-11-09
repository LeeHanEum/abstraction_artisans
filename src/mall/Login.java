package mall;

import mgr.Manager;
import mgr.Manageable;

import java.util.Scanner;

public class Login {
    static Manager UserList = new Manager();

    //아이디와 비밀번호를 입력받고 해당 객체를 리턴함.
    public static User login(Scanner scan){
        String[] input = new String[2];

        System.out.print("아이디를 입력해주세요");
        input[0] = scan.nextLine();
        System.out.print("비밀번호를 입력해주세요");
        input[1] = scan.nextLine();

        User usr = findUserByID(input[0]);
        if(usr == null || !usr.matchPW(input[1])){
            return null;
        }


        return null;
    }

    //해당 ID를 가진 User 객체를 반환.
    public static User findUserByID(String inputID){
        //TODO: mList 권한 설정 해야함
        /*
        for(Manageable T : UserList.mList) {
            User usr = (User) T;
            if (usr.matchID(inputID)){
                return usr;
            }
        }
        */
        return null;
    }


}