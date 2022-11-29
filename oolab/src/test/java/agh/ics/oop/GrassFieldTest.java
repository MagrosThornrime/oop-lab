package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    public void twoAnimals(){
        GrassField map = new GrassField(10, new Random(0));
        Vector2d animalCoords1 = new Vector2d(1, 1);
        Vector2d animalCoords2 = new Vector2d(0, 1);

        Animal animal1 = new Animal(map, animalCoords1);
        Animal animal2 = new Animal(map, animalCoords2);
        animal2.move(MoveDirection.RIGHT);
        animal2.move(MoveDirection.FORWARD);
        assertSame(animal1, map.objectAt(animalCoords1));
        map.place(new Animal(map, animalCoords1));
        assertSame(animal1, map.objectAt(animalCoords1));

    }

    @Test
    public void eatingGrass(){
        GrassField map = new GrassField(10, new Random(0));
        Vector2d animalCoords = new Vector2d(4, 2);
        Animal animal = new Animal(map, animalCoords);
        Vector2d newGrass = new Vector2d(10, 8);

        map.place(animal);
        assertNull(map.objectAt(newGrass));
        animal.move(MoveDirection.FORWARD);
        assertTrue(map.objectAt(newGrass) instanceof Grass);
    }
}
