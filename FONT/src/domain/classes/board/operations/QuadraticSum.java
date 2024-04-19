package src.domain.classes.board.operations;

import src.domain.classes.board.Cell;
import src.domain.classes.types.Pair;

import java.util.ArrayList;

public class QuadraticSum extends Operation {
    /**
     * Calculates the result of adding the values of the cells in the group.
     *
     * @param grid            The grid of cells containing the values to be added.
     * @param cellCoordinates The coordinates of the cells in the group.
     * @return The result of the addition operation.
     */
    @Override
    public int calculateGroupResult(Cell[][] grid, ArrayList<Pair<Integer,Integer>> cellCoordinates) throws IllegalArgumentException{
        checkNumberCells(grid.length, cellCoordinates.size());
        int sum = 0;
        for (Pair<Integer, Integer> pos : cellCoordinates) {
            Cell c = grid[pos.getFirst()][pos.getSecond()];
            sum += c.getValue();
        }
        return sum * sum;
    }

    /**
     * Gets the identifier of the addition operation.
     *
     * @return The identifier of the addition operation.
     */
    @Override
    public int getOpId() {
        return 6;
    }

    /**
     * Gets a string representation of the addition operation.
     *
     * @return A string representing the addition operation.
     */
    @Override
    public String getOpString() {
        return "(+)^2";
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
     * Checks if the result of the addition operation between the given cell and the
     * cells in the group is correct.
     *
     * @param cell            The cell that is being added to the group.
     * @param result          The result of the addition operation.
     * @param grid            The grid of cells containing the values to be added.
     * @param cellCoordinates The coordinates of the cells in the group.
     * @return True if the result of the addition operation is correct, false
     *         otherwise.
     */
    @Override
    public boolean validValue(Cell cell, Integer result, Cell[][] grid,
            ArrayList<Pair<Integer, Integer>> cellCoordinates) {
        Integer accumulatedValue = 0;
        Integer activeCellsCount = 0;
        for (Pair<Integer, Integer> pair : cellCoordinates) {
            Cell c = grid[pair.getFirst()][pair.getSecond()];
            if (c.getValue() != 0) {
                if (c != cell) {
                    accumulatedValue += c.getValue() * c.getValue();
                    ++activeCellsCount;
                }
            }
        }
        return (activeCellsCount == cellCoordinates.size() - 1)
                ? (cell.getValue() * cell.getValue()) + accumulatedValue == result
                : (cell.getValue() * cell.getValue()) + accumulatedValue <= result;
    }
}
