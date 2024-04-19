package src.domain.classes.board.operations;

import java.util.ArrayList;
import src.domain.classes.board.Cell;
import src.domain.classes.types.Pair;

public abstract class Operation {

    /**
     * Calculates the result of the group based on its operation and the values of
     * its cells.
     *
     * @param grid            The grid of cells from which the cells that belong to
     *                        the group are selected.
     * @param cellCoordinates The coordinates of the cells assigned to this group.
     * @return The result of the group's operation.
     */
    public abstract int calculateGroupResult(Cell[][] grid, ArrayList<Pair<Integer, Integer>> cellCoordinates);

    /**
     * Gets the identifier value of the operation.
     *
     * @return The identifier of the operation.
     */
    public abstract int getOpId();

    /**
     * Gets a string representation of the operation.
     *
     * @return A string representing the operation.
     */
    public abstract String getOpString();

    public abstract int maxNumberCellsAllowed(int gridSize);

    public abstract void checkNumberCells(int gridSize, int numberCells) throws IllegalArgumentException;

    /**
     * Checks if the given value is a valid input for the operation.
     * 
     * Takes into account the current values of the cells in the group and checks if
     * the given value still makes the operation result equal or less than the
     * actual result.
     *
     * @param cell            the cell whose value is to be checked
     * @param result          the current result of the operation
     * @param grid            the grid of cells from which the cells that belong to
     *                        the group are selected
     * @param cellCoordinates the coordinates of the cells assigned to this group
     * @return true if the value is valid, false otherwise
     */
    public abstract boolean validValue(Cell cell, Integer result, Cell[][] grid,
            ArrayList<Pair<Integer, Integer>> cellCoordinates);
}
