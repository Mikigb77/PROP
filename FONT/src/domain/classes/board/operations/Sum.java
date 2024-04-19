package src.domain.classes.board.operations;

import java.util.ArrayList;

import src.domain.classes.board.Cell;
import src.domain.classes.types.Pair;

public class Sum extends Operation {

    /**
     * Calculates the result of adding the values of the cells in the group.
     *
     * @param grid            The grid of cells containing the values to be added.
     * @param cellCoordinates The coordinates of the cells in the group.
     * @return The result of the addition operation.
     */
    @Override
    public int calculateGroupResult(Cell[][] grid, ArrayList<Pair<Integer, Integer>> cellCoordinates){
        checkNumberCells(grid.length, cellCoordinates.size());
        int sum = 0;
        for (Pair<Integer, Integer> pos : cellCoordinates) {
            Cell c = grid[pos.getFirst()][pos.getSecond()];
            sum += c.getValue();
        }
        return sum;
    }

    /**
     * Gets the identifier of the addition operation.
     *
     * @return The identifier of the addition operation.
     */
    @Override
    public int getOpId() {
        return 1;
    }

    /**
     * Gets a string representation of the addition operation.
     *
     * @return A string representing the addition operation.
     */
    @Override
    public String getOpString() {
        return "+";
    }


    @Override
    public int maxNumberCellsAllowed(int gridSize){
        return gridSize*gridSize;
    }

    @Override
    public void checkNumberCells(int gridSize, int numberCells) throws IllegalArgumentException{
        if (numberCells < 2 || numberCells > maxNumberCellsAllowed(gridSize))
            throw new IllegalArgumentException("Group with (" + getOpString() + ") operation and " + numberCells +
                    " cells. The (" + getOpString() + ") operation only allows groups with at " +
                    "least 1 or more cells.");
    }

    /**
     * Checks if the given value for the current cell is valid based on the current
     * state of the group.
     *
     * @param cell            The current cell whose value is to be validated.
     * @param result          The expected result of the operation.
     * @param grid            The grid of cells containing the values to be added.
     * @param cellCoordinates The coordinates of the cells in the group.
     * @return True if the given value is valid, false otherwise.
     */
    @Override
    public boolean validValue(Cell cell, Integer result, Cell[][] grid,
            ArrayList<Pair<Integer, Integer>> cellCoordinates) {
        Integer accumulatedValue = 0;
        Integer activeCellsCount = 0;
        for (Pair<Integer, Integer> pair : cellCoordinates) {
            Cell c = grid[pair.getFirst()][pair.getSecond()];
            if (c != cell) {
                if (c.getValue() != 0) {
                    accumulatedValue += c.getValue();
                    ++activeCellsCount;
                }
            }
        }
        return (activeCellsCount == cellCoordinates.size() - 1) ? accumulatedValue + cell.getValue() == result
                : accumulatedValue + cell.getValue() <= result;
    }
}
