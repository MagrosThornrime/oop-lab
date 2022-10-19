package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    private final Vector2d first = new Vector2d(1, 0);
    private final Vector2d second = new Vector2d(1, 0);
    private final Vector2d third = new Vector2d(1, 1);

    private final Vector2d fourth = new Vector2d(-1, -1);

    @Test
    public void equalsTest() {
        assertEquals(first, second);
        assertNotEquals(first, third);
        assertEquals(first, first);
    }

    @Test
    public void toStringTest() throws Exception{
        assertEquals("(1,0)", first.toString());
        assertEquals("(1,1)", third.toString());
    }

    @Test
    public void precedesTest() {
        assertTrue(first.precedes(third));
        assertFalse(third.precedes(first));
    }

    @Test
    public void followsTest() {
        assertFalse(first.follows(third));
        assertTrue(third.follows(first));
    }

    @Test
    public void upperRightTest() {
        assertEquals(first.upperRight(third), third);
        assertEquals(second.upperRight(fourth), second);
        assertEquals(fourth.upperRight(fourth), fourth);
    }

    @Test
    public void lowerLeftTest() {
        assertEquals(first.lowerLeft(fourth), fourth);
        assertEquals(second.lowerLeft(third), second);
        assertEquals(first.lowerLeft(first), first);
    }

    @Test
    public void addTest() {
        assertEquals(first.add(second), new Vector2d(2, 0));
        assertEquals(first.add(fourth), new Vector2d(0, -1));
        assertEquals(third.add(third), new Vector2d(2, 2));
    }

    @Test
    public void subtract() {
        assertEquals(first.subtract(second), new Vector2d(0, 0));
        assertEquals(third.subtract(first), new Vector2d(0, 1));
        assertEquals(third.subtract(fourth), new Vector2d(2, 2));
    }

    @Test
    public void opposite() {
        assertEquals(third.opposite(), fourth);
        assertEquals(first.opposite(), new Vector2d(-1, 0));
    }
}
