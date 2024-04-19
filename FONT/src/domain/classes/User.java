package src.domain.classes;

//use arrays
import java.util.ArrayList;
//use data class
import java.util.Date;


public class User {

    private final String username;
    private String password;
    private String email;
    private final Date creationDate;
    private int kenkenCompleted;
    private ArrayList<Integer> myKenkens;

    // Constructors
    //new User creator
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.creationDate = new Date();
        this.myKenkens = new ArrayList<>();
        kenkenCompleted = 0;
    }

    //already existing user creator
    public User(String username, String password, String email, Date creationDate, ArrayList<Integer> myKenkens, int kenkenCompleted) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.creationDate = creationDate;
        this.myKenkens = myKenkens;
        this.kenkenCompleted = kenkenCompleted;
    }

    // Setters

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setKenkenCompleted(int kenkenCompleted) {
        this.kenkenCompleted = kenkenCompleted;
    }

    public void setMyKenkens(ArrayList<Integer> myKenkens) {
        this.myKenkens = myKenkens;
    }

    // Getters
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public int getKenkenCompleted() {
        return this.kenkenCompleted;
    }

    public ArrayList<Integer> getMyKenkens() { return this.myKenkens;}
}
