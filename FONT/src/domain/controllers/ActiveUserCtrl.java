package src.domain.controllers;
import src.domain.classes.User;

import java.util.ArrayList;
import java.util.Date;

public class ActiveUserCtrl {
    private User activeUser;
    //Constructor
    public ActiveUserCtrl() {
        activeUser = null;
    }

    //Returns the active user
    public User getActiveUser() {
        if (activeUser != null) return activeUser;
        throw new NullPointerException("Active user is null");
    }

    //Assign the given user (if the password is correct) as the active user
    public Boolean logIn(User user, String password) {
        if(activeUser == null) {
            if(password.equals(user.getPassword())) {
                activeUser = user;
                return true;
            }
        }
        return false;
    }

    //If there is an active user assign it to null and have no longer an active user
    public Boolean logOut() {
        if(activeUser != null) {
            activeUser = null;
            return true;
        }
        else return false;
    }

    //Creates a new user with the given parameters
    public User register(String username, String password, String email){
        return new User(username, password, email);
    }

    public String getActiveUserUsername() {
        return activeUser.getUsername();
    }

    public String getActiveUserPassword() {
        return activeUser.getPassword();
    }

    public String getActiveUserEmail() {
        return activeUser.getEmail();
    }

    public Date getActiveUserCreationDate() {
        return activeUser.getCreationDate();
    }

    public int getActiveUserKenkenCompleted() {
        return activeUser.getKenkenCompleted();
    }

    public ArrayList<Integer> getActiveUserKenkens() {
        return activeUser.getMyKenkens();
    }

    public void setActiveUserPassword(String password) {
        activeUser.setPassword(password);
    }

    public void setActiveUserEmail(String email) {
        activeUser.setEmail(email);
    }

    public void setActiveUserKenkenCompleted(int kenkenCompleted) {
        activeUser.setKenkenCompleted(kenkenCompleted);
    }

    public void setActiveUserKenkens(ArrayList<Integer> myKenkens) {
        activeUser.setMyKenkens(myKenkens);
    }

}
