package src.domain.classes;

import java.time.Duration;
import java.time.LocalDateTime;

import src.domain.classes.kenken.Kenken;

public class CurrentGame {
    private Kenken kenken;
    private Integer id;
    private String username;
    private LocalDateTime startTime;
    private Duration elapsedTime = Duration.ZERO;
    private static Integer gamesId = 0;

    public CurrentGame(Kenken kenken, String username, LocalDateTime startTime) {
        this.kenken = kenken;
        this.username = username;
        this.startTime = startTime;
        this.id = gamesId++;
    }

    public Kenken getKenken() {
        return kenken;
    }

    public void setKenken(Kenken kenken) {
        this.kenken = kenken;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Duration getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Duration elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void updateElapsedTime(LocalDateTime finishTime) {
        Duration duration = Duration.between(startTime, finishTime);
        this.elapsedTime = this.elapsedTime.plus(duration);
    }

    public static Integer getGamesId() {
        return gamesId;
    }
}
