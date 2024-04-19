package src.domain.classes.board.operations;

import java.util.ArrayList;
import src.domain.classes.board.Cell;
import src.domain.classes.types.Pair;

public class Nothing extends Operation {

    /**
     * Retrieves the value of the single cell.
     *
     * @param grid            The grid of cells containing the value.
     * @param cellCoordinates The coordinates of the single cell in the group. The
     *                        size of this list must be 1.
     * @return The value of the single cell.
     * @throws IllegalArgumentException If the size of the cell coordinates list is
     *                                  not 1.
     */
    @Override
    public int calculateGroupResult(Cell[][] grid, ArrayList<Pair<Integer, Integer>> cellCoordinates) {
        checkNumberCells(grid.length, cellCoordinates.size());
        return 0;
    }

    /**
     * Gets the identifier of the Nothing operation.
     *
     * @return The identifier of the Nothing operation.
     */
    @Override
    public int getOpId() {
        return 0;
    }

    /**
     * Gets a string representation of the Nothing operation.
     *
     * @return A string representing the Nothing operation.
     */
    @Override
    public String getOpString() {
        return "N";
    }

    @Override
    public int maxNumberCellsAllowed(int gridSize){
        return gridSize*gridSize;
    }

    @Override
    public void checkNumberCells(int gridSize, int numberCells) throws IllegalArgumentException{
        if (numberCells < 1 || numberCells > maxNumberCellsAllowed(gridSize))
            throw new IllegalArgumentException("Group with (" + getOpString() + ") operation and " + numberCells +
                    " cells. " + "The (" + getOpString() + ") operation only allows groups with at " +
                    "least 1 or more cells.");
    }

    /**
     * Checks if a value is valid for a given cell in a grid.
     * 
     * @param cell            - The cell for which the value is being validated.
     * @param result          - The value being checked for validity.
     * @param grid            - The 2D array representing the grid.
     * @param cellCoordinates - The list of coordinates for cells in the grid.
     * @return - true if the value is valid for the cell, false otherwise.
     */
    @Override
    public boolean validValue(Cell cell, Integer result, Cell[][] grid,
            ArrayList<Pair<Integer, Integer>> cellCoordinates) {
        return true;
    }
}
