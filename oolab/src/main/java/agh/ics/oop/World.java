package agh.ics.oop;

import java.util.Arrays;
import java.util.Random;

public class World {
    public static void main(String[] args) {
//        MoveDirection[] directions = new OptionsParser().parse(args);
//        AbstractWorldMap map = new RectangularMap(10, 5);
//        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//        IEngine engine = new SimulationEngine(directions, map, positions);
//        engine.run();
        GrassField map = new GrassField(10, new Random(0));
        System.out.println(map);
        System.out.println(Arrays.toString(map.elementPositions()));
        Vector2d animalCoords = new Vector2d(4, 2);
        Animal animal = new Animal(map, animalCoords);
        map.place(animal);
        System.out.println(map);
        animal.move(MoveDirection.FORWARD);
        System.out.println(map);

    }
}
