package src.domain.controllers;

import src.domain.classes.User;

public class ExternalUserCtrl {
    private User externalUser;

    public ExternalUserCtrl(User externalUser) {
        this.externalUser = externalUser;
    }

    public User getExternalUser() {
        return externalUser;
    }


}
