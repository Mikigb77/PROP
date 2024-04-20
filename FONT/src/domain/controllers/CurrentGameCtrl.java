package src.domain.controllers;

import src.domain.classes.CurrentGame;
import src.domain.classes.User;
import src.domain.classes.types.CurrentGameSet;

public class CurrentGameCtrl {
    private User user;
    private CurrentGameSet currentGameSet;

    public CurrentGameCtrl(User user, CurrentGameSet currentGameSet) {
        this.user = user;
        this.currentGameSet = currentGameSet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CurrentGameSet getCurrentGameSet() {
        return currentGameSet;
    }

    public void setCurrentGameSet(CurrentGameSet currentGameSet) {
        this.currentGameSet = currentGameSet;
    }

    public void setCurrentGame(CurrentGame currentGame) {
        this.currentGameSet.saveCurrentGame(currentGame);
    }

}