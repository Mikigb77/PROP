package src.domain.classes.board.operations;

import java.util.ArrayList;
import src.domain.classes.board.Cell;
import src.domain.classes.types.Pair;

public class Multiply extends Operation {

    /**
     * Calculates the result of multiplying the values of all cells in the group.
     *
     * @param grid            The grid of cells containing the values.
     * @param cellCoordinates The coordinates of the cells in the group.
     * @return The result of multiplying the values of all cells in the group.
     */
    @Override
    public int calculateGroupResult(Cell[][] grid, ArrayList<Pair<Integer, Integer>> cellCoordinates) {
        checkNumberCells(grid.length, cellCoordinates.size());
        int aux = 1;
        for (Pair<Integer, Integer> pos : cellCoordinates) {
            Cell c = grid[pos.getFirst()][pos.getSecond()];
            aux *= c.getValue();
        }
        return aux;
    }

    /**
     * Gets the identifier of the Multiply operation.
     *
     * @return The identifier of the Multiply operation.
     */
    @Override
    public int getOpId() {
        return 3;
    }

    /**
     * Gets a string representation of the Multiply operation.
     *
     * @return A string representing the Multiply operation.
     */
    @Override
    public String getOpString() {
        return "*";
    }

    @Override
    public int maxNumberCellsAllowed(int gridSize){
        return gridSize*gridSize;
    }

    @Override
    public void checkNumberCells(int gridSize, int numberCells) throws IllegalArgumentException{
        if (numberCells < 2 || numberCells > maxNumberCellsAllowed(gridSize))
            throw new IllegalArgumentException("Group with (" + getOpString() + ") operation and " + numberCells +
                    " cells. " + "The (" + getOpString() + ") operation only allows groups with at " +
                    "least 2 or more cells.");
    }

    /**
     * Checks if a given cell's value is valid in the context of a grid and a
     * specified result.
     *
     * @param cell            The cell whose value is being validated.
     * @param result          The expected result.
     * @param grid            The grid of cells.
     * @param cellCoordinates The list of coordinates of other cells to consider.
     * @return True if the cell's value is valid, false otherwise.
     */
    @Override
    public boolean validValue(Cell cell, Integer result, Cell[][] grid,
            ArrayList<Pair<Integer, Integer>> cellCoordinates) {
        Integer accumulatedValue = 1;
        Integer activeCellsCount = 0;
        for (Pair<Integer, Integer> pair : cellCoordinates) {
            Cell c = grid[pair.getFirst()][pair.getSecond()];
            if (c != cell && c.getValue() != 0) {
                accumulatedValue *= c.getValue();
                ++activeCellsCount;
            }
        }
        return (activeCellsCount == cellCoordinates.size() - 1) ? (accumulatedValue * cell.getValue() == result)
                : (accumulatedValue * cell.getValue() <= result);
    }
}
