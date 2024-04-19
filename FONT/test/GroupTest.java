package test;

import src.domain.classes.board.*;
import src.domain.classes.board.operations.Operation;
import src.domain.classes.types.Pair;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GroupTest {

    private Group group;
    private Operation operationMock;
    private Cell[][] grid;

    @Before
    public void setUp() {
        group = new Group(1, 2, 300);
        grid = new Cell[3][3];

        operationMock = mock(Operation.class);
        group.setOperation(operationMock);

        Cell c1 = new Cell(1, 2);
        Cell c2 = new Cell(1, 3);
        Cell c3 = new Cell(2, 3);
        c1.setValue(3);
        c2.setValue(5);
        c3.setValue(7);

        grid[0][1] = c1;
        grid[0][2] = c2;
        grid[1][2] = c3;
    }

    // Test Constructor


    @Test
    public void testConstructorGroupInvalid(){
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Group newGroup = new Group(1, 20, 300);
        });
    }

    // Test Setters

    @Test
    public void setGroupId() {
        group.setGroupId(5);
        int actual = group.getGroupId();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void setOpId() {
        group.setOpId(3);
        int actual = group.getOpId();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void setOpIdOutRange() {
        assertThrows(IllegalArgumentException.class, () -> group.setOpId(10));
    }

    // Test Getters

    @Test
    public void getGroupId() {
        int actual = group.getGroupId();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void getOpId() {
        when(operationMock.getOpId()).thenReturn(3);
        int actual = group.getOpId();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void getResult() {
        int actual = group.getResult();
        int expected = 300;
        assertEquals(expected, actual);
    }

    @Test
    public void getCellCoordinatesEmpty() {
        assertThrows(IllegalStateException.class, () -> group.getCellCoordinates());
    }

    /*
    @Test
    public void getCellCoordinates() {
        ArrayList<Pair<Integer, Integer>> expected = new ArrayList<>();
        expected.add(new Pair<Integer, Integer>(1, 2));
        expected.add(new Pair<Integer, Integer>(2, 3));

        group.assignCellToGroup(1, 2);
        group.assignCellToGroup(2, 3);

        ArrayList<Pair<Integer, Integer>> actual = group.getCellCoordinates();
        assertEquals(expected, actual);
    }

    @Test
    public void getNumberCells() {
        int expected = 0;
        int actual = group.getNumberCells();
        assertEquals(expected, actual);
    }

    // Test Others

    @Test
    public void assignCellToGroup() {
        ArrayList<Pair<Integer, Integer>> expected = new ArrayList<>();
        expected.add(new Pair<Integer, Integer>(1, 2));

        group.assignCellToGroup(1, 2);
        ArrayList<Pair<Integer, Integer>> actual = group.getCellCoordinates();
        assertEquals(expected, actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void calculateGroupResult() {
        when(operationMock.calculateGroupResult(any(Cell[][].class), any(ArrayList.class))).thenReturn(300);
        int expected = 300;
        int actual = group.calculateGroupResult(grid);
        assertEquals(expected, actual);
        verify(operationMock).calculateGroupResult(any(Cell[][].class), any(ArrayList.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void compareResults() {
        when(operationMock.calculateGroupResult(any(Cell[][].class), any(ArrayList.class))).thenReturn(300);
        assertTrue(group.compareResults(grid));
    }


    @Test
    public void testCheckConnectedGroup(){
        group.setGroupId(1);
        group.assignCellToGroup(1,2, grid);
        group.assignCellToGroup(1,3, grid);
        group.assignCellToGroup(2,3, grid);

        boolean result = group.checkConnectedGroup(grid);
        assertTrue(result);
    }
     */
}