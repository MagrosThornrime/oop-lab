package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrassFieldTest {
    @Test
    public void twoAnimals(){
        GrassField map = new GrassField(10, 0);
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
    public void twoGrasses() {
        GrassField map = new GrassField(10, 0);
        Vector2d grassCoords1 = new Vector2d(2, 2);
        Vector2d grassCoords2 = new Vector2d(2, 2);

        Grass grass1 = new Grass(grassCoords1);
        map.place(grass1);
        map.place(new Grass(grassCoords2));
        assertSame(grass1, map.objectAt(grassCoords1));
    }

    @Test
    public void animalEatsGrass() {
        GrassField map = new GrassField(10, 0);
        Vector2d animalCoords = new Vector2d(3, 0);
        Vector2d grassCoords = new Vector2d(4, 0);
        Vector2d newGrass = new Vector2d(10, 8);

        Animal animal = new Animal(map, animalCoords);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        assertSame(animal, map.objectAt(grassCoords));
        assertTrue(map.objectAt(newGrass) instanceof Grass);
    }

    @Test
    public void grassCantEatAnimal() {
        GrassField map = new GrassField(10, 0);
        Vector2d animalCoords = new Vector2d(3, 0);
        Vector2d grassCoords = new Vector2d(3, 0);

        Animal animal = new Animal(map, animalCoords);
        map.place(new Grass(animalCoords));
        assertTrue(map.objectAt(grassCoords) instanceof Animal);
    }
}
