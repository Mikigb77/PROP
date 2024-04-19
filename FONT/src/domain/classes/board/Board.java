package src.domain.classes.board;

import src.domain.classes.board.operations.Operation;

import java.util.*;

public class Board {
    private Cell[][] grid;
    private Map<Integer, Group> groups;
    private Map<Integer, Set<Integer>> rows;
    private Map<Integer, Set<Integer>> cols;

    // Constructor

    public Board(int gridSize) {
        grid = new Cell[gridSize][gridSize];
        groups = new HashMap<>();
        rows = new HashMap<>();
        cols = new HashMap<>();
        for (int i = 0; i < gridSize; i++) {
            rows.put(i, new HashSet<>());
            cols.put(i, new HashSet<>());
        }
    }

    // Setters

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public void setGroups(Map<Integer, Group> groups) {
        this.groups = groups;
    }

    public void setCellInBoard(Cell cell) {
        int X = cell.getX();
        int Y = cell.getY();
        int value = cell.getValue();
        checkCoordinates(X,Y);
        checkAssignedCell(X,Y);
        checkCell(cell);
        checkRowsAndCols(X,Y,value);
        addCellToGroup(cell);
        grid[X-1][Y-1] = cell;
        if (value != 0){
            rows.get(X-1).add(value);
            cols.get(Y-1).add(value);
        }

    }

    // Getters

    public Cell[][] getGrid() {
        return grid;
    }

    public Map<Integer, Group> getGroups() {
        return groups;
    }

    public int getGridSize(){
        return grid.length;
    }

    public Cell getCell(int X, int Y) throws IllegalArgumentException{
        checkCoordinates(X,Y);
        return grid[X-1][Y-1];
    }

    // Others

    public void changeCellValue(int X, int Y, int value) throws IllegalArgumentException{
        checkCoordinates(X,Y);
        if (grid[X-1][Y-1] == null) throw new IllegalArgumentException("There is no cell assigned in the (" + X + "," + Y + ") " +
                "coordinate");
        grid[X-1][Y-1].setValue(value);
    }

    public void addNewGroup(Group group) throws IllegalArgumentException{
        int groupId = group.getGroupId();
        if (this.groups.containsKey(groupId))
            throw new IllegalArgumentException("Group already exists");
        this.groups.put(groupId, group);
    }

    private void addCellToGroup (Cell cell) throws IllegalArgumentException{
        int groupId = cell.getGroupId();
        if (groups.containsKey(groupId)){
            groups.get(groupId).assignCellToGroup(cell.getX(), cell.getY(), grid.length);
        }
        else throw new IllegalArgumentException("Group does not exist");
    }


    public void checkGroups() throws IllegalStateException{
        int totalCells = 0;
        Collection<Group> groups = this.groups.values();
        for (Group group : groups){
            int numCells = group.getNumberCells();
            Operation operation = group.getOperation();
            operation.checkNumberCells(this.grid.length,numCells);
            totalCells += numCells;
        }
        if (totalCells != this.grid.length*this.grid.length) throw new IllegalStateException("There are " +
                "cells on the board that have not been assigned to any group.");
    }

    private void checkCoordinates(int X, int Y) throws IllegalArgumentException{
        if (X < 1 || X > grid.length || Y < 1 || Y > grid.length)
            throw new IllegalArgumentException("The coordinate ("+ X + "," + Y + ") is invalid.");
    }

    private void checkCell(Cell cell) throws IllegalArgumentException{
        int X = cell.getX();
        int Y = cell.getY();
        checkCoordinates(X,Y);
        if (cell.getGroupId() == -1) throw new IllegalArgumentException("Cell does not have a groupId assigned");

        int value = cell.getValue();
        if (value != 0 && value > grid.length)
            throw new IllegalArgumentException("Cell (" + X + "," + Y + ") with invalid value (" + value + ")." +
                    " Value must be between 0 and " + grid.length);
    }

    private void checkAssignedCell(int X, int Y) throws IllegalArgumentException{
        if (grid[X-1][Y-1] != null)
            throw new IllegalArgumentException("There is already a cell assigned in the (" + X + "," + Y + ") " +
                    "coordinate");
    }

    private void checkRowsAndCols(int X, int Y, int value) throws IllegalArgumentException{
        if (rows.get(X-1).contains(value) || cols.get(Y-1).contains(value))
            throw new IllegalArgumentException("The value (" + value + ") already exists in the row or in the " +
                    "column of the board.");
    }
}
