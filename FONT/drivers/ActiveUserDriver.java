package drivers;

import src.domain.classes.User;
import src.domain.controllers.ActiveUserCtrl;
import java.util.Scanner;

public class ActiveUserDriver {
    private final Scanner sc = new Scanner(System.in);




    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the driver to test User\n");
        System.out.println("First we create a new ActiveUserCtrl with no active user");
        ActiveUserCtrl auc = new ActiveUserCtrl();
        System.out.println("Controller created correctly\n");

        System.out.println("Now we have to register a new user:\n");

        System.out.println("1- Test register new User\n2- Test log in a User\n3- Exit\n");

        int command = 0;

        while (command != 3) {
            command = sc.nextInt();
            switch (command) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    System.out.println("Exiting driver...");
                    break;
                default:
                    System.out.println("Wrong command.\n1- Test create a new User\n2- Test load a User\n3- Exit\n");
                    break;
            }
        }
    }
}