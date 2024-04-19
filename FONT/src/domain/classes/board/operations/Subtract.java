package src.domain.classes.board.operations;

import java.util.ArrayList;
import src.domain.classes.board.Cell;
import src.domain.classes.types.Pair;

public class Subtract extends Operation {

    /**
     * Calculates the result of subtracting the value of the second cell from the
     * value of the first cell.
     *
     * @param grid            The grid of cells containing the values to be
     *                        subtracted.
     * @param cellCoordinates The coordinates of the cells in the group. The size of
     *                        this list must be 2.
     * @return The result of the subtraction operation.
     * @throws IllegalArgumentException If the size of the cell coordinates list is
     *                                  not 2.
     */
    @Override
    public int calculateGroupResult(Cell[][] grid, ArrayList<Pair<Integer, Integer>> cellCoordinates){
        checkNumberCells(grid.length, cellCoordinates.size());
        Cell c1 = grid[cellCoordinates.get(0).getFirst()][cellCoordinates.get(0).getSecond()];
        Cell c2 = grid[cellCoordinates.get(1).getFirst()][cellCoordinates.get(1).getSecond()];
        return c1.getValue() - c2.getValue();
    }

    /**
     * Gets the identifier of the subtraction operation.
     *
     * @return The identifier of the subtraction operation.
     */
    @Override
    public int getOpId() {
        return 2;
    }

    /**
     * Gets a string representation of the subtraction operation.
     *
     * @return A string representing the subtraction operation.
     */
    @Override
    public String getOpString() {
        return "-";
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
     * Checks if the given value is valid for the current operation and group of
     * cells.
     *
     * @param cell            The cell whose value is being checked.
     * @param result          The result of the operation.
     * @param grid            The grid of cells containing the values to be checked.
     * @param cellCoordinates The coordinates of the cells in the group. The size of
     *                        this list must be 2.
     * @return True if the value is valid, false otherwise.
     * @throws IllegalArgumentException If the size of the cell coordinates list is
     *                                  not 2.
     */
    @Override
    public boolean validValue(Cell cell, Integer result, Cell[][] grid,
            ArrayList<Pair<Integer, Integer>> cellCoordinates) throws IllegalArgumentException {
        if (cellCoordinates.size() != 2)
            throw new IllegalArgumentException("The Subtract operation can only work with groups of 2 cell");
        for (Pair<Integer, Integer> pair : cellCoordinates) {
            Cell c = grid[pair.getFirst()][pair.getSecond()];
            if (c != cell) {
                if (c.getValue() == 0)
                    return true;
                if (c.getValue() != 0) {
                    if (c.getValue() - cell.getValue() == result)
                        return true;
                    if (cell.getValue() - c.getValue() == result)
                        return true;
                }
            }
        }
        return false;
    }
}