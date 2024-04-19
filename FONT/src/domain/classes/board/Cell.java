package src.domain.classes.board;

public class Cell {
    private int value;
    private int groupId;
    private final int X;
    private final int Y;

    // Constructor

    /**
     * Constructs an instance of the Cell class with the X and Y coordinates of the
     * cell.
     * Subsequently,values must be assigned to the cell such as the cell value and
     * the groupId
     * of the group to which it belongs.
     *
     * @param X The X coordinate of the cell.
     * @param Y The Y coordinate of the cell.
     * @throws IllegalArgumentException If the X or Y coordinates are less than 1
     */
    public Cell(int X, int Y) throws IllegalArgumentException {
        if (X < 1 || Y < 1) throw new IllegalArgumentException("Invalid Coordinates (" + X + ", " + Y + ")");
        this.value = 0;
        this.groupId = -1;
        this.X = X;
        this.Y = Y;
    }

    // Setters

    /**
     * Sets a value to the cell
     *
     * @param value Value to assign to the cell
     * @throws IllegalArgumentException If the value is <= 0 or > kenken.Size()
     */
    public void setValue(int value) throws IllegalArgumentException {
        if (value <= 0) throw new IllegalArgumentException("Value out of range");
        this.value = value;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    // Getters

    /**
     * @return The value of the cell
     * @throws IllegalStateException If the cell does not have a value assigned to
     *                               it
     */
    public int getValue() throws IllegalStateException {
        //if (value == 0) throw new IllegalStateException("Value not defined");
        return value;
    }

    /**
     * @return The groupId of the group the cell belongs to
     * @throws IllegalStateException If the cell does not have a groupId assigned to
     *                               it
     */
    public int getGroupId() throws IllegalStateException {
        if (groupId == -1)
            throw new IllegalStateException("groupId not defined");
        return groupId;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    // Others
}
