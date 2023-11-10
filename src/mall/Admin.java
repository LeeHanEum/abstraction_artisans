package mall;

import mgr.Manager;

public class Admin {
    static Manager userMgr = new Manager();
    public void adminPage(){
        if(!Login.currentUser.id.equals("admin")){
            System.out.println("Permission denied");
            return;
        }
        //TODO : make manage page for admin
        System.out.println("Hello admin");
    }
}
