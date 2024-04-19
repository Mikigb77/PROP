package test;

import src.domain.classes.board.Cell;
import src.domain.classes.board.operations.*;
import src.domain.classes.types.Pair;

import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OperationTest {

    private Cell[][] grid0;
    private Cell[][] grid1;
    private Cell[][] grid2;
    ArrayList<Pair<Integer, Integer>> cellCoordinates0;
    ArrayList<Pair<Integer, Integer>> cellCoordinates1;
    ArrayList<Pair<Integer, Integer>> cellCoordinates2;

    @Before
    public void setUp() {
        grid0 = new Cell[3][3];
        grid1 = new Cell[3][3];
        grid2 = new Cell[3][3];

        Cell cell1 = mock(Cell.class);
        Cell cell2 = mock(Cell.class);
        Cell cell3 = mock(Cell.class);

        when(cell1.getValue()).thenReturn(4);
        when(cell2.getValue()).thenReturn(12);
        when(cell3.getValue()).thenReturn(24);

        grid0[0][0] = cell1;

        grid1[0][0] = cell1;
        grid1[0][1] = cell2;

        grid2[0][0] = cell1;
        grid2[0][1] = cell2;
        grid2[0][2] = cell3;

        Pair<Integer, Integer> pair1 = mock(Pair.class);
        Pair<Integer, Integer> pair2 = mock(Pair.class);
        Pair<Integer, Integer> pair3 = mock(Pair.class);

        when(pair1.getFirst()).thenReturn(0);
        when(pair1.getSecond()).thenReturn(0);
        when(pair2.getFirst()).thenReturn(0);
        when(pair2.getSecond()).thenReturn(1);
        when(pair3.getFirst()).thenReturn(0);
        when(pair3.getSecond()).thenReturn(2);

        cellCoordinates0 = new ArrayList<>();
        cellCoordinates1 = new ArrayList<>();
        cellCoordinates2 = new ArrayList<>();

        cellCoordinates0.add(pair1);

        cellCoordinates1.add(pair1);
        cellCoordinates1.add(pair2);

        cellCoordinates2.add(pair1);
        cellCoordinates2.add(pair2);
        cellCoordinates2.add(pair3);
    }

    @Test
    public void testCalculateGroupResultNothing() {
        Operation op = new Nothing();
        int expected = 4;
        int actual = op.calculateGroupResult(grid0, cellCoordinates0);
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateGroupResultNothingInvalid() {
        Operation op = new Nothing();
        assertThrows(IllegalArgumentException.class, () -> {
            op.calculateGroupResult(grid1, cellCoordinates1);
        });
    }

    @Test
    public void testCalculateGroupResultSum() {
        Operation op = new Sum();
        int expected = 4 + 12 + 24;
        int actual = op.calculateGroupResult(grid2, cellCoordinates2);
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateGroupResultSumInvalid() {
        Operation op = new Sum();
        assertThrows(IllegalArgumentException.class, () -> {
            op.calculateGroupResult(grid0, cellCoordinates0);
        });
    }

    @Test
    public void testCalculateGroupResultSubtract() {
        Operation op = new Subtract();
        int expected = 4 - 12;
        int actual = op.calculateGroupResult(grid1, cellCoordinates1);
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateGroupResultSubtractInvalid() {
        Operation op = new Subtract();
        assertThrows(IllegalArgumentException.class, () -> {
            op.calculateGroupResult(grid2, cellCoordinates2);
        });
    }

    @Test
    public void testCalculateGroupResultMultiply() {
        Operation op = new Multiply();
        int expected = 4 * 12 * 24;
        int actual = op.calculateGroupResult(grid2, cellCoordinates2);
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateGroupResultMultiplyInvalid() {
        Operation op = new Multiply();
        assertThrows(IllegalArgumentException.class, () -> {
            op.calculateGroupResult(grid0, cellCoordinates0);
        });
    }

    @Test
    public void testCalculateGroupResultDivide() {
        Operation op = new Divide();
        int expected = 4 / 12;
        int actual = op.calculateGroupResult(grid1, cellCoordinates1);
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateGroupResultDivideInvalid() {
        Operation op = new Divide();
        assertThrows(IllegalArgumentException.class, () -> {
            op.calculateGroupResult(grid2, cellCoordinates2);
        });
    }

    @Test
    public void testCalculateGroupResultModulo() {
        Operation op = new Modulo();
        int expected = 4 % 12;
        int actual = op.calculateGroupResult(grid1, cellCoordinates1);
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateGroupResultModuloInvalid() {
        Operation op = new Modulo();
        assertThrows(IllegalArgumentException.class, () -> {
            op.calculateGroupResult(grid2, cellCoordinates2);
        });
    }
}