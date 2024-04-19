package test;

import src.domain.classes.board.Cell;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CellTest {

    Cell cell;

    @Before
    public void before() {
        cell = new Cell(1, 2);
    }

    // Test Constructor

    @Test
    public void testConstructorCoordinatesOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Cell newCell = new Cell(-1, 0);
        });

    }

    // Test Setters

    @Test
    public void testSetValue() {
        cell.setValue(5);
        int actual = cell.getValue();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void testSetValueOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            cell.setValue(-1);
        });

    }

    @Test
    public void testSetGroupId() {
        cell.setGroupId(9);
        int actual = cell.getGroupId();
        int expected = 9;
        assertEquals(expected, actual);
    }

    // Test Getters

    @Test
    public void testGetValueUnassigned() {
        assertThrows(IllegalStateException.class, () -> {
            cell.getValue();
        });
    }

    @Test
    public void testGetValue() {
        cell.setValue(5);
        int actual = cell.getValue();
        int expected = 5;
        assertEquals(expected, actual);
    }

    /**
     * If a .getGroupId() is performed and the cell does not have a groupId assigned
     * to it,
     * an assertion is thrown.
     */
    @Test
    public void testGetGroupIdUnassigned() {
        assertThrows(IllegalStateException.class, () -> {
            cell.getGroupId();
        });
    }

    @Test
    public void testGetGroupId() {
        cell.setGroupId(9);
        int actual = cell.getGroupId();
        int expected = 9;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetX() {
        int actual = cell.getX();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetY() {
        int actual = cell.getY();
        int expected = 2;
        assertEquals(expected, actual);
    }
}