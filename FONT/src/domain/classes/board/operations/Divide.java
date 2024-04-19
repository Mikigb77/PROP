package src.domain.classes.board.operations;

import java.util.ArrayList;
import src.domain.classes.board.Cell;
import src.domain.classes.types.Pair;

public class Divide extends Operation {

    /**
     * Calculates the result of dividing the value of the first cell by the value of
     * the second cell in the group.
     *
     * @param grid            The grid of cells containing the values.
     * @param cellCoordinates The coordinates of the cells in the group.
     * @return The result of dividing the value of the first cell by the value of
     *         the second cell in the group.
     * @throws IllegalArgumentException If the group does not contain exactly two
     *                                  cells.
     */
    @Override
    public int calculateGroupResult(Cell[][] grid, ArrayList<Pair<Integer, Integer>> cellCoordinates){
        checkNumberCells(grid.length, cellCoordinates.size());
        Cell c1 = grid[cellCoordinates.get(0).getFirst()][cellCoordinates.get(0).getSecond()];
        Cell c2 = grid[cellCoordinates.get(1).getFirst()][cellCoordinates.get(1).getSecond()];
        return c1.getValue() / c2.getValue();
    }

    /**
     * Gets the identifier of the Divide operation.
     *
     * @return The identifier of the Divide operation.
     */
    @Override
    public int getOpId() {
        return 4;
    }

    /**
     * Gets a string representation of the Divide operation.
     *
     * @return A string representing the Divide operation.
     */
    @Override
    public String getOpString() {
        return "/";
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
     * Validates if the given cell value is valid for the division operation.
     *
     * @param cell            The cell whose value is to be validated.
     * @param result          The expected result of the division operation.
     * @param grid            The grid of cells containing the values.
     * @param cellCoordinates The coordinates of the cells in the group.
     * @return True if the cell value is valid for the division operation, false
     *         otherwise.
     * @throws IllegalArgumentException If the group does not contain exactly two
     *                                  cells.
     */
    public boolean validValue(Cell cell, Integer result, Cell[][] grid,
            ArrayList<Pair<Integer, Integer>> cellCoordinates) {
        if (cellCoordinates.size() != 2)
            throw new IllegalArgumentException("The Division operation can only work with groups of 2 cell");

        for (Pair<Integer, Integer> pair : cellCoordinates) {
            Cell c = grid[pair.getFirst()][pair.getSecond()];
            if (c != cell) {
                if (c.getValue() == 0)
                    return true;
                else if (c.getValue() != 0) {
                    double maxValue = (double) Math.max(c.getValue(), cell.getValue());
                    double minValue = (double) Math.min(c.getValue(), cell.getValue());
                    double divisionResult = maxValue / minValue;
                    double resultDouble = (double) result;
                    return Double.compare(divisionResult, resultDouble) == 0;

                }
            }
        }
        return true;
    }
}