package src.domain.classes.types;

public class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    /**
     * Checks if the given object is equal to this pair.
     * Two pairs are considered equal if their first and second elements are equal.
     *
     * @param o the object to compare with this pair
     * @return true if the given object is equal to this pair, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    /**
     * Returns the hash code for this pair.
     * The hash code is computed by combining the hash codes of the first and second
     * elements using the 31-bit multiplication method.
     *
     * @return the hash code for this pair
     */
    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }
}
