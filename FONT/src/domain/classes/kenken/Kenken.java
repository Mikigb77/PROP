package src.domain.classes.kenken;

import java.util.Date;

import src.domain.classes.board.*;

public class Kenken {
    private int kenkenId;
    private String author;
    private Date creationDate;
    private Board board;

    // Constructor

    public Kenken(int kenkenId, String author, Date creationDate) {
        this.kenkenId = kenkenId;
        this.author = author;
        this.creationDate = creationDate;
    }

    // Setters

    public void setKenkenId(int kenkenId) {
        this.kenkenId = kenkenId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setNewBoard(int N) {
        this.board = new Board(N);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    // Getters

    public int getKenkenId() {
        return this.kenkenId;
    }

    public String getAuthor() {
        return this.author;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Board getBoard() {
        return this.board;
    }

    // Others
}
