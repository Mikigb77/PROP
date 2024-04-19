package src.domain.classes.types;

import java.util.HashMap;
import java.util.Map;
import src.domain.classes.kenken.Kenken;

public class KenkenSet {
    private final Map<Integer, Kenken> kenkens;
    private int kenkenId;

    public KenkenSet() {
        kenkens = new HashMap<>();
        kenkenId = 0;
    }

    /**
     * Assigns a new unique id to the given Kenken object and adds it to the set.
     *
     * @param kenken The Kenken object to be assigned a new id.
     * @return The new id assigned to the Kenken object.
     * @throws IllegalArgumentException if the Kenken object is null.
     */
    public int newId(Kenken kenken) throws IllegalArgumentException {
        if (kenken == null) {
            throw new IllegalArgumentException("Kenken object cannot be null.");
        }

        kenken.setKenkenId(kenkenId);
        this.kenkens.put(kenkenId, kenken);
        kenkenId++;
        return kenkenId - 1;
    }

    /**
     * Retrieves a Kenken object from the set using its unique id.
     *
     * @param kenkenId The unique id of the Kenken object to be retrieved.
     * @return The Kenken object with the given id, or null if it does not exist in
     *         the set.
     * @throws IllegalArgumentException if the provided id is negative or not
     *                                  present in the set.
     */
    public Kenken getKenken(int kenkenId) throws IllegalArgumentException {
        if (kenkenId < 0) {
            throw new IllegalArgumentException("Kenken id cannot be negative.");
        }
        return kenkens.get(kenkenId);
    }
}
