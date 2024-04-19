package src.domain.classes.board;

import src.domain.classes.board.operations.*;
import src.domain.classes.types.Pair;

import java.util.*;

public class Group {

    private int groupId;
    private int result;
    public Operation operation;
    private final ArrayList<Pair<Integer, Integer>> cellCoordinates;

    // Constructor

    /**
     * The constructor creates a group instance from the groupId, OpId and result.
     *
     * @param opId   Mathematical Operation Identifier of the group:
     * @param result The expected result of the mathematical operation for this
     *               group.
     */
    public Group(int groupId, int opId, int result) {
        this.groupId = groupId;
        this.result = result;
        this.setOpId(opId);
        this.cellCoordinates = new ArrayList<>();
    }

    // Setters

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setResult(int result) {
        this.result = result;
    }
    /**
     * Sets the operation identifier for this group. This method assigns an
     * operation identifier
     * to this group and creates an instance of the corresponding operation
     * according to the
     * specified operation identifier.
     *
     * @param opId The operation identifier to set. Valid values are: {0: nothing},
     *             {1: Sum}, {2: Subtract},
     *             {3: Multiply}, {4: Divide}, {5: Modulo}, {6: QuadraticSum}
     * @throws IllegalArgumentException If the operation identifier is outside the
     *                                  allowed range [0-6].
     */
    public void setOpId(int opId) throws IllegalArgumentException {
        if (opId < 0 || opId > 6)
            throw new IllegalArgumentException("OpId out of range");
        this.operation = defineOperation(opId);
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    // Getters

    /**
     * Gets the group identifier assigned to this group.
     *
     * @return The group identifier assigned to this group.
     * @throws IllegalStateException If the group does not have a group identifier
     *                               assigned to it.
     */
    public int getGroupId() throws IllegalStateException {
        if (groupId == -1)
            throw new IllegalStateException("The group has no groupId assigned");
        return this.groupId;
    }

    public int getOpId() {
        return this.operation.getOpId();
    }

    public Operation getOperation() {
        return this.operation;
    }

    public int getResult() {
        return this.result;
    }

    /**
     * This method returns the cell coordinates assigned to this group
     *
     * @return A list of Pair objects representing the cell coordinates assigned to
     *         this group
     * @throws IllegalStateException If the group has no cells assigned.
     */
    public ArrayList<Pair<Integer, Integer>> getCellCoordinates() throws IllegalStateException {
        if (cellCoordinates.isEmpty())
            throw new IllegalStateException("Group without assigned cells");
        return this.cellCoordinates;
    }

    public int getNumberCells() {
        return this.cellCoordinates.size();
    }

    // Others

    public void assignCellToGroup(int X, int Y, int gridSize) throws IllegalStateException {
        checkOpConsistency(gridSize);
        if (!findAdjacentCellsInGroup(X,Y,gridSize))
            throw new IllegalStateException("The cell ("+ X + "," + Y + ") is not adjacent " +
                    "to any other cell in its group.");
        this.cellCoordinates.add(new Pair<>(X, Y));
    }

    /**
     * Calculates the result of the group based on its operation and the values of
     * its cells.
     *
     * @param grid The grid of cells from which the cells that belong to the group
     *             are selected.
     * @return The result of the group's operation.
     */
    public int calculateGroupResult(Cell[][] grid) {
        int opId = this.operation.getOpId();
        if (opId == 0 || opId == 1 || opId == 3 || opId == 6)
            return this.operation.calculateGroupResult(grid, this.cellCoordinates);
        else {
            int aux = this.operation.calculateGroupResult(grid, this.cellCoordinates);
            if (aux == this.result)
                return aux;
            else {
                ArrayList<Pair<Integer, Integer>> ArrayAux = this.cellCoordinates;
                Collections.swap(ArrayAux, 0, 1);
                return operation.calculateGroupResult(grid, ArrayAux);
            }
        }
    }

    /**
     * Compares the result of the mathematical operation of the group (using the
     * grid and the
     * coordinates of the cells assigned to the group) with the expected result.
     *
     * @param grid The grid of cells from which the cells that belong to the group
     *             are selected.
     * @return true if the result of the mathematical operation of the group matches
     *         the expected result,
     *         false otherwise.
    */
    public boolean compareResults(Cell[][] grid){
        if (this.operation.getOpId() == 0) return true;
        int operationResult = this.calculateGroupResult(grid);
        return (operationResult == result);
    }

    /**
     * Defines the mathematical operation corresponding to the given operation
     * identifier.
     *
     * @param opId The operation identifier for which the mathematical operation is
     *             to be defined.
     * @return An instance of the mathematical operation corresponding to the given
     *         identifier.
     */
    private Operation defineOperation(int opId) {
        switch (opId) {
            case 0:
                return new Nothing();
            case 1:
                return new Sum();
            case 2:
                return new Subtract();
            case 3:
                return new Multiply();
            case 4:
                return new Divide();
            case 5:
                return new Modulo();
            case 6:
                return new QuadraticSum();
            default:
                return null;
        }
    }

    private boolean findAdjacentCellsInGroup(int X, int Y, int gridSize){
        if (this.cellCoordinates.isEmpty()) return true;

        @SuppressWarnings("unchecked")
        Pair<Integer,Integer>[] dirs = new Pair[]{
                new Pair<>(0,1),
                new Pair<>(1,0),
                new Pair<>(0,-1),
                new Pair<>(-1,0),
        };

        for(Pair<Integer,Integer> d: dirs){
            int nX = X + d.getFirst();
            int nY = Y + d.getSecond();

            if (checkCoordinates(nX, nY, gridSize) && findCellInGroup(nX, nY)) return true;
        }
        return false;
    }

    private boolean checkCoordinates(int X, int Y, int gridSize){
        return (X > 0 && X <= gridSize && Y > 0 && Y <= gridSize);
    }

    private void checkOpConsistency(int gridSize) throws IllegalStateException {
        int numberCells = this.cellCoordinates.size()+1;
        if (numberCells > this.operation.maxNumberCellsAllowed(gridSize))
            throw new IllegalStateException("The current group (groupId :" + groupId+") already has " +
                    "the maximum number of cells allowed for the operation (" + this.operation.getOpString() + ")");
    }


    private boolean findCellInGroup(int X, int Y) throws IllegalArgumentException{
        for (Pair<Integer, Integer> pos : this.cellCoordinates){
            if (pos.getFirst() == X && pos.getSecond() == Y) return true;
        }
        return false;
    }

    /**
     * This method checks if the given value is a valid value for the given cell,
     * considering the mathematical operation of the group and the grid of cells.
     *
     * @param cell  The cell for which the value is to be checked.
     * @param value The value to be checked.
     * @param grid  The grid of cells.
     * @return True if the value is a valid value for the given cell, false
     *         otherwise.
     */
    public boolean validValue(Cell cell, Integer value, Cell[][] grid) {
        int currentValue = cell.getValue();
        cell.setValue(value);
        boolean bResult = this.operation.validValue(cell, result, grid, cellCoordinates);
        cell.setValue(currentValue);

        return bResult;
    }

}
