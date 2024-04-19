package test;

import org.junit.Before;
import org.junit.Test;
import src.domain.classes.types.Pair;

import static org.junit.Assert.*;

public class PairTest {
    Pair<Integer, Integer> pair;

    @Before
    public void setUp() {
        pair = new Pair<>(5, 7);
    }

    @Test
    public void getFirst() {
        int actual = pair.getFirst();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void getSecond() {
        int actual = pair.getSecond();
        int expected = 7;
        assertEquals(expected, actual);
    }

    @Test
    public void setFirst() {
        pair.setFirst(9);
        int actual = pair.getFirst();
        int expected = 9;
        assertEquals(expected, actual);

    }

    @Test
    public void setSecond() {
        pair.setSecond(11);
        int actual = pair.getSecond();
        int expected = 11;
        assertEquals(expected, actual);
    }
}