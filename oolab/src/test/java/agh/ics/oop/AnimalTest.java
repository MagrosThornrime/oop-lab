package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    public void rotation(){
        Animal dog = new Animal();
        MapDirection[] orientations = {MapDirection.NORTH, MapDirection.EAST,
                                        MapDirection.SOUTH, MapDirection.WEST};

        for(int i=0; i<4; i++) {
            assertEquals(orientations[i], dog.getOrientation());
            dog.move(MoveDirection.RIGHT);
        }

        for(int i=3; i>=0; i--) {
            dog.move(MoveDirection.LEFT);
            assertEquals(orientations[i], dog.getOrientation());
        }
    }

    @Test
    public void isAt() {
        Animal dog = new Animal();
        assertTrue(dog.isAt(new Vector2d(2, 2)));
    }

    @Test
    public void forwardsAndBackwards() {
        Animal dog = new Animal();
        Vector2d[] vectors = {new Vector2d(2, 3), new Vector2d(3, 2),
                                new Vector2d(2, 1), new Vector2d(1, 2)};

        for(Vector2d vector: vectors) {
            dog.move(MoveDirection.FORWARD);
            assertTrue(dog.isAt(vector));
            dog.move(MoveDirection.BACKWARD);
            dog.move(MoveDirection.RIGHT);
        }
    }

    @Test
    public void mapLimits() {
        Animal dog = new Animal();
        Vector2d[] vectors = {new Vector2d(2, 4), new Vector2d(4, 4),
                new Vector2d(4, 0), new Vector2d(0, 0), new Vector2d(0, 4)};

        for(Vector2d vector: vectors) {
            for(int i=0; i<8; i++){
                dog.move(MoveDirection.FORWARD);
            }
            assertTrue(dog.isAt(vector));
            dog.move(MoveDirection.RIGHT);
        }
    }
}