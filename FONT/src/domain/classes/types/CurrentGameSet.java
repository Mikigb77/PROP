package src.domain.classes.types;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.domain.classes.CurrentGame;

public class CurrentGameSet {
    private final Map<Integer, CurrentGame> currentGameSet;

    public CurrentGameSet() {
        this.currentGameSet = new HashMap<Integer, CurrentGame>();
    }

    /**
     * Returns the current game set.
     *
     * @return the current game set as a map where the key is the game id and the
     *         value is the current game object.
     */
    public Map<Integer, CurrentGame> getCurrentGame() {
        return this.currentGameSet;
    }

    /**
     * Adds a new current game to the set.
     *
     * @param currentGame The current game to be added to the set.
     */
    public void addCurrentGame(CurrentGame currentGame) {
        this.currentGameSet.put(currentGame.getId(), currentGame);
    }

    /**
     * Removes a current game from the set.
     *
     * @param currentGame The current game to be removed from the set.
     */
    public void removeCurrentGame(CurrentGame currentGame) {
        this.currentGameSet.remove(currentGame.getId());
    }

    /**
     * Retrieves the current game from the set by its id.
     *
     * @param id The unique identifier of the current game.
     * @return The current game object if found in the set, otherwise null.
     */
    public CurrentGame getCurrentGameById(Integer id) {
        return this.currentGameSet.get(id);
    }

    public void saveCurrentGame(CurrentGame currentGame) {
        currentGame.updateElapsedTime(LocalDateTime.now());
        this.currentGameSet.put(currentGame.getId(), currentGame);
    }

    /**
     * Retrieves a list of current games owned by a specific user.
     *
     * @param username The username of the user whose games are to be retrieved.
     * @return A list of current games owned by the specified user.
     */
    public List<CurrentGame> getCurrentGamesByUser(String username) {
        List<CurrentGame> userGames = new ArrayList<>();
        for (CurrentGame game : currentGameSet.values()) {
            if (game.getUsername().equals(username)) {
                userGames.add(game);
            }
        }
        return userGames;
    }

    /**
     * Retrieves the number of current games owned by a specific user.
     *
     * @param username The username of the user whose games are to be counted.
     * @return The number of current games owned by the specified user.
     */
    public Integer getNumberOfCurrentGamesByUser(String username) {
        Integer count = 0;
        for (CurrentGame game : currentGameSet.values()) {
            if (game.getUsername().equals(username)) {
                count++;
            }
        }
        return count;
    }

}
