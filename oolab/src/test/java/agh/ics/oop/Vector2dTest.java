package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    private final Vector2d first = new Vector2d(1, 0);
    private final Vector2d second = new Vector2d(1, 0);
    private final Vector2d third = new Vector2d(1, 1);

    private final Vector2d fourth = new Vector2d(-1, -1);

    @Test
    public void equals() {
        assertEquals(first, second);
        assertNotEquals(first, third);
        assertEquals(first, first);
    }

    @Test
    public void toStringTest() throws Exception {
        assertEquals("(1,0)", first.toString());
        assertEquals("(1,1)", third.toString());
    }

    @Test
    public void precedes() {
        assertTrue(first.precedes(third));
        assertFalse(third.precedes(first));
    }

    @Test
    public void follows() {
        assertFalse(first.follows(third));
        assertTrue(third.follows(first));
    }

    @Test
    public void upperRight() {
        assertEquals(third, first.upperRight(third));
        assertEquals(second, second.upperRight(fourth));
        assertEquals(fourth, fourth.upperRight(fourth));
    }

    @Test
    public void lowerLeft() {
        assertEquals(fourth, first.lowerLeft(fourth));
        assertEquals(second, second.lowerLeft(third));
        assertEquals(first, first.lowerLeft(first));
    }

    @Test
    public void add() {
        assertEquals(new Vector2d(2, 0), first.add(second));
        assertEquals(new Vector2d(0, -1), first.add(fourth));
        assertEquals(new Vector2d(2, 2), third.add(third));
    }

    @Test
    public void subtract() {
        assertEquals(new Vector2d(0, 0), first.subtract(second));
        assertEquals(new Vector2d(0, 1), third.subtract(first));
        assertEquals(new Vector2d(2, 2), third.subtract(fourth));
    }

    @Test
    public void opposite() {
        assertEquals(fourth, third.opposite());
        assertEquals(new Vector2d(-1, 0), first.opposite());
    }
}
