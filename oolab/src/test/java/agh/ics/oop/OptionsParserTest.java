package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    private final OptionsParser parser = new OptionsParser();

    private boolean areEqual(MoveDirection[] first, MoveDirection[] second) {
        if (first == second)
            return true;
        if (first.length != second.length)
            return false;
        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i])
                return false;
        }
        return true;
    }

    @Test
    public void simpleParse(){
        String[] arguments = {"f", "b", "r", "l"};
        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.BACKWARD,
                                    MoveDirection.RIGHT, MoveDirection.LEFT};

        assertTrue(areEqual(directions, parser.parse(arguments)));
    }

    @Test
    public void lengths(){
        String[] arguments = {"f", "b", "r"};
        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT};

        assertFalse(areEqual(directions, parser.parse(arguments)));
    }

    @Test
    public void invalidArgs(){
        String[] arguments = {"front", "boom", "r", "abc"};

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(arguments);
        });

        assertEquals("front is not legal move specification", thrown.getMessage());
    }

    @Test
    public void emptyArgs(){
        String[] arguments = {};
        MoveDirection[] directions = {};

        assertTrue(areEqual(directions, parser.parse(arguments)));
    }

    @Test
    public void longArgs(){
        String[] arguments = {"backward", "forward", "left", "right", "l", "f"};
        MoveDirection[] directions = {MoveDirection.BACKWARD, MoveDirection.FORWARD,
                                    MoveDirection.LEFT, MoveDirection.RIGHT,
                                    MoveDirection.LEFT, MoveDirection.FORWARD};

        assertTrue(areEqual(directions, parser.parse(arguments)));
    }
}