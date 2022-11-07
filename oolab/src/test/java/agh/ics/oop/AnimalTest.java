package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    public void rotation(){
        RectangularMap map = new RectangularMap(4, 4);
        Animal dog = new Animal(map);
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
        RectangularMap map = new RectangularMap(4, 4);
        Animal dog = new Animal(map);
        assertTrue(dog.isAt(new Vector2d(2, 2)));
    }

    @Test
    public void forwardsAndBackwards() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal dog = new Animal(map);
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
        RectangularMap map = new RectangularMap(4, 4);
        Animal dog = new Animal(map);
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