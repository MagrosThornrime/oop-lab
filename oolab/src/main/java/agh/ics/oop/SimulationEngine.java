package agh.ics.oop;

import java.util.Arrays;

public class SimulationEngine implements IEngine{
    private final Animal[] animals;
    private MoveDirection[] directions;
    private AbstractWorldMap map;

    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] initialPositions) {
        animals = Arrays.stream(initialPositions)
                .map(x -> new Animal(map, x))
                .toArray(Animal[]::new);
        this.directions = directions;
        this.map = map;

    }

    @Override
    public void run() {
//        System.out.println(map.toString());
        for (int i=0; i<directions.length; i++) {
            Animal nextAnimal = animals[i % animals.length];
            nextAnimal.move(directions[i]);
//            System.out.println(map.toString());
        }
    }
}
