package agh.ics.oop;

import java.util.Arrays;

public class SimulationEngine implements IEngine{
    private final Animal[] animals;
    private MoveDirection[] directions;
    private IWorldMap map;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        animals = Arrays.stream(initialPositions)
                .map(x -> new Animal(map, x))
                .toArray(Animal[]::new);
        this.directions = directions;
        this.map = map;

    }

    @Override
    public void run() {
//        System.out.println(map.toString());
        int animalIndex = 0;
        for (MoveDirection direction: directions) {
            if (animalIndex == animals.length) {
                animalIndex = 0;
            }
            animals[animalIndex].move(direction);
//            System.out.println(map.toString());
            animalIndex++;
        }
    }

    public Vector2d[] getPositions(){
        return Arrays.stream(animals).map(Animal::getPosition).toArray(Vector2d[]::new);
    }
}
