package src.domain.classes.board.operations;

import src.domain.classes.board.Cell;
import src.domain.classes.types.Pair;

import java.util.ArrayList;

public class Modulo extends Operation {

    /**
     * Calculates the result of applying the modulo operation on the values of two
     * cells in the group.
     *
     * @param grid            The grid of cells containing the values.
     * @param cellCoordinates The coordinates of the cells in the group.
     * @return The remainder of dividing the value of the first cell by the value of
     *         the second cell in the group.
     * @throws IllegalArgumentException If the group does not contain exactly two
     *                                  cells.
     */
    @Override
    public int calculateGroupResult(Cell[][] grid, ArrayList<Pair<Integer, Integer>> cellCoordinates){
        checkNumberCells(grid.length, cellCoordinates.size());
        Cell c1 = grid[cellCoordinates.get(0).getFirst()][cellCoordinates.get(0).getSecond()];
        Cell c2 = grid[cellCoordinates.get(1).getFirst()][cellCoordinates.get(1).getSecond()];
        return c1.getValue() % c2.getValue();
    }

    /**
     * Gets the identifier of the Modulo operation.
     *
     * @return The identifier of the Modulo operation.
     */
    @Override
    public int getOpId() {
        return 5;
    }

    /**
     * Gets a string representation of the Modulo operation.
     *
     * @return A string representing the Modulo operation.
     */
    @Override
    public String getOpString() {
        return "%";
    }

    @Override
    public int  maxNumberCellsAllowed(int gridSize){
        return 2;
    }

    @Override
    public void checkNumberCells(int gridSize, int numberCells) throws IllegalArgumentException{
        if (numberCells != maxNumberCellsAllowed(gridSize))
            throw new IllegalArgumentException("Group with (" + getOpString() + ") operation and " + numberCells +
                    " cells. " + "The (" + getOpString() + ") operation only allows groups with 2 cells.");
    }
    
    /**
     * Validates if the given result is valid for the current cell and the group of
     * cells.
     *
     * @param cell            The current cell being evaluated.
     * @param result          The result of the operation.
     * @param grid            The grid of cells containing the values.
     * @param cellCoordinates The coordinates of the cells in the group.
     * @return True if the result is valid, false otherwise.
     * @throws IllegalArgumentException If the group does not contain exactly two
     *                                  cells.
     */
    @Override
    public boolean validValue(Cell cell, Integer result, Cell[][] grid,
            ArrayList<Pair<Integer, Integer>> cellCoordinates) {
        // Check if the group contains exactly two cells
        if (cellCoordinates.size() != 2)
            throw new IllegalArgumentException("The Modulo operation can only work with groups of 2 cells");

        for (Pair<Integer, Integer> pair : cellCoordinates) {
            Cell c = grid[pair.getFirst()][pair.getSecond()];
            if (c != cell) {
                if (c.getValue() == 0)
                    return true;
                if (c.getValue() != 0) {
                    if (c.getValue() % cell.getValue() == result)
                        return true;
                    if (cell.getValue() % c.getValue() == result)
                        return true;
                }
            }
        }
        return false;
    }
}
