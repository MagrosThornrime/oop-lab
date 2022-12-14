package agh.ics.oop;

import agh.ics.oop.gui.IMoveObserver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class SimulationEngine implements IEngine, Runnable{

    private final Animal[] animals;
    private MoveDirection[] directions;

    private final List<IMoveObserver> observers = new LinkedList<>();

    private int moveDelay;
    private final AbstractWorldMap map;

    public void addObserver(IMoveObserver observer){
        observers.add(observer);
    }

    public void setMoveDelay(int moveDelay){
        this.moveDelay = moveDelay;
    }

    public SimulationEngine(AbstractWorldMap map, Vector2d[] initialPositions){
        this(new MoveDirection[0], map, initialPositions);
    }

    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] initialPositions) {
        animals = Arrays.stream(initialPositions)
                .map(x -> new Animal(map, x))
                .toArray(Animal[]::new);
        this.directions = directions;
        this.map = map;
    }

    private void animalMoved(Vector2d oldPosition, MapDirection oldOrientation, Animal animal){
        if(!oldPosition.equals(animal.getPosition()) || !oldOrientation.equals(animal.getOrientation())){
            for(IMoveObserver observer: observers){
                observer.animalMoved(oldPosition, oldOrientation, animal);
            }
        }

    }

    private synchronized void move(Animal animal, MoveDirection direction){
        Vector2d oldPosition = animal.getPosition();
        MapDirection oldOrientation = animal.getOrientation();
        animal.move(direction);
        animalMoved(oldPosition,oldOrientation,animal);
    }

    @Override
    public void run() {
//        System.out.println(map.toString());
        for (int i=0; i<directions.length; i++) {
            Animal nextAnimal = animals[i % animals.length];
            move(nextAnimal, directions[i]);
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted");
            }
            System.out.println(map);
        }
    }

    public void setDirections(MoveDirection[] directions){
        this.directions = directions;
    }

}
