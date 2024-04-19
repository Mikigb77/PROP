package src.domain.controllers;
import src.domain.classes.*;

public class AuxDomainCtrl {
    private final StubPersistenceCtrl PC;
    private final ActiveUserCtrl activeUserCtrl;


    public AuxDomainCtrl() {
        this.PC = new StubPersistenceCtrl();
        this.activeUserCtrl = new ActiveUserCtrl();
    }

    /**
     * This function tries to assign a user to active user in ActiveUserCtrl
     * @param username Unique name of the user that logs in
     * @param password Password of the user
     * @return if successful returns true, else false
     */
    public Boolean logIn (String username, String password){
        User user = PC.getUser(username);
        return activeUserCtrl.logIn(user, password);
    }

    public Boolean logOut (){

    }
}
