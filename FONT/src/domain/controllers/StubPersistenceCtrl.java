package src.domain.controllers;
import src.domain.classes.User;

public class StubPersistenceCtrl
{
    public StubPersistenceCtrl() {
    }

    public User getUser(String username){
        return new User("Username", "Password", "Mail");
    }
}
