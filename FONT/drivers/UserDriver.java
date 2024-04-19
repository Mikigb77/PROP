package drivers;
import src.domain.classes.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class UserDriver {
    private User testUser;
    private final Scanner sc = new Scanner(System.in);

    //Creators
    public void testCreatorNewUser (){
        System.out.println("Please input a username:");
        String name = sc.nextLine();
        System.out.println("Please input a password:");
        String password = sc.nextLine();
        System.out.println("Please input a email:");
        String email = sc.nextLine();
        testUser = new User(name, password, email);
        System.out.println("User created successfully\n");
    }

    public void testCreatorLoadUser (){
        try {
            System.out.println("Please input a username:");
            String name = sc.nextLine();

            System.out.println("Please input a password:");
            String password = sc.nextLine();

            System.out.println("Please input a email:");
            String email = sc.nextLine();

            System.out.println("Please input a creation date in format dd-MM-yyyy:");
            String stringDate = sc.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = dateFormat.parse(stringDate);

            System.out.println("Please type the Kenkens id list owned by the user, with format:");
            System.out.println("id1 id2 id3 id4 ...");
            ArrayList<Integer> myKenkens = new ArrayList<>();
            String ids = sc.nextLine();
            String[] idsArray = ids.split(" ");

            for(String str : idsArray){
                int kenkenId = Integer.parseInt(str);
                myKenkens.add(kenkenId);
            }

            System.out.println("Please type the new KenkensCompleted value");
            int kenkenCompleted = sc.nextInt();

            testUser = new User(name, password, email, date, myKenkens, kenkenCompleted);
            System.out.println("User loaded successfully\n");
        }
        catch (ParseException e) {
            throw new IllegalArgumentException("Wrong date format\n");
        }
    }

    //Getters User
    public void testGetUsername() {
        String result = testUser.getUsername();
        System.out.println("The username is " + result + "\n");
    }

    public void testGetPassword() {
        String result = testUser.getPassword();
        System.out.println("The password is " + result + "\n");
    }

    public void testGetEmail() {
        String result = testUser.getEmail();
        System.out.println("The email is " + result + "\n");
    }

    public void testGetCreationDate() {
        Date result = testUser.getCreationDate();
        System.out.println("The creation date is " + result + "\n");
    }

    public void testGetKenkenCompleted() {
        int result = testUser.getKenkenCompleted();
        System.out.println("Kenkens completed by the user " + result + "\n");
    }

    public void testGetKenkens() {
        ArrayList<Integer> result = testUser.getMyKenkens();
        System.out.println("The id of the Kenkens of the user " + result + "\n");
    }

    //Setters User

    public void testSetPassword() {
        System.out.println("Please type the new password");
        String password = sc.nextLine();
        testUser.setPassword(password);
        System.out.println("Password set successfully\n");
    }

    public void testSetEmail() {
        System.out.println("Please type the new email");
        String email = sc.nextLine();
        testUser.setEmail(email);
        System.out.println("Email set successfully\n");
    }


    public void testSetKenkenCompleted() {
        System.out.println("Please type the new KenkensCompleted value");
        int kenkenCompleted = sc.nextInt();
        testUser.setKenkenCompleted(kenkenCompleted);
        System.out.println("KenkensCompleted set successfully\n");
    }

    public void testSetKenkensUser() {
        System.out.println("Please type the new Kenkens id list owned by the user, with format:");
        System.out.println("id1 id2 id3 id4 ...");
        ArrayList<Integer> myKenkens = new ArrayList<>();
        String ids = sc.nextLine();
        String[] idsArray = ids.split(" ");

        for(String str : idsArray){
            int kenkenId = Integer.parseInt(str);
            myKenkens.add(kenkenId);
        }

        testUser.setMyKenkens(myKenkens);
        System.out.println("Kenkens set successfully\n");
    }




    public static void main (String [] args){

        UserDriver ud = new UserDriver();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the driver to test User\n");
        System.out.println("First we need a User, choose between:");
        System.out.println("1- Test create a new User\n2- Test load a User\n3- Exit\n");

        int command = 0;

        while(command != 3 && command != 2 && command != 1){
            command = sc.nextInt();
            switch (command) {
                case 1:
                    ud.testCreatorNewUser();
                    break;
                case 2:
                    ud.testCreatorLoadUser();
                    break;
                case 3:
                    System.out.println("Exiting driver...");
                    break;
                default:
                    System.out.println("Wrong command.\n1- Test create a new User\n2- Test load a User\n3- Exit\n");
                    break;
            }
        }

        if (command == 3) command = 11;
        else System.out.println("Input command\n" +
                "1- Test getUsername\n2- Test getPassword\n3- Test getEmail\n4- Test getCreationDate\n5- Test getKenkenCompleted\n6- Test getMyKenkens\n7- Test setPassword\n8- Test setEmail\n9- Test setKenkenCompleted\n10- Test setMyKenkens\n11- Exit\n");


        while(command != 11) {
            command = sc.nextInt();
            switch (command) {
                case 1:
                    ud.testGetUsername();
                    break;

                case 2:
                    ud.testGetPassword();
                    break;
                case 3:
                    ud.testGetEmail();
                    break;

                case 4:
                    ud.testGetCreationDate();
                    break;
                case 5:
                    ud.testGetKenkenCompleted();
                    break;
                case 6:
                    ud.testGetKenkens();
                    break;

                case 7:
                    ud.testSetPassword();
                    break;
                case 8:
                    ud.testSetEmail();
                    break;
                case 9:
                    ud.testSetKenkenCompleted();
                    break;
                case 10:
                    ud.testSetKenkensUser();
                    break;

                case 11:
                    System.out.println("Exiting driver...");
                    sc.close();
                    break;

                default:
                    System.out.println("Wrong command.\n1- Test getUsername\n2- Test getPassword\n3- Test getEmail\n4- Test getCreationDate\n5- Test getKenkenCompleted\n6- Test getMyKenkens\n7- Test setPassword\n8- Test setEmail\n9- Test setKenkenCompleted\n10- Test setMyKenkens\n12- Exit\n");
                    break;
            }
        }
    }
}
