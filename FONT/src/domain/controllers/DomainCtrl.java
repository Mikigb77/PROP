package src.domain.controllers;

import src.domain.classes.kenken.Kenken;

import java.util.ArrayList;

/**
 * Singleton pattern
 * The goal is to make all classes share the same instance of the domain controller.

 *To request the DomainCtrl instance in other classes:
 *    -> DomainCtrl domainCtrl = DomainCtrl.getInstance();
 */
public class DomainCtrl {
    private final KenkenCtrl kenkenCtrl;
    private static final DomainCtrl instance = new DomainCtrl();

    private DomainCtrl() {
        kenkenCtrl = new KenkenCtrl();
    }

    public static DomainCtrl getInstance() {
        return instance;
    }

    public int newKenken(String author, ArrayList<String> strings) {
        return kenkenCtrl.newKenken(author, strings);
    }

    public Kenken getKenken(int kenkenId) {
        return kenkenCtrl.getKenken(kenkenId);
    }

    /*
    public Kenken solveKenken(Kenken kenken) {
    }
     */
}
