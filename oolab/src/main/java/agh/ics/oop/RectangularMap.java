package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class RectangularMap implements IWorldMap{

    private static final Vector2d MIN_VECTOR = new Vector2d(0, 0);
    private final Vector2d maxVector;

    private final List<Animal> animals = new LinkedList<>();

    private final MapVisualizer visualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height){
        maxVector = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        // the method's parameter suggest that you don't need
        // to check if there is another animal on your path
        // otherwise you would have to check the initial position of the animal
        return position.follows(MIN_VECTOR) && position.precedes(maxVector);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.stream().anyMatch(x -> x.getPosition().equals(position));
    }

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()) || isOccupied(animal.getPosition())){
            return false;
        }
        animals.add(animal);
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.stream()
                .filter(x -> x.getPosition().equals(position))
                .findAny()
                .orElse(null);
    }

    @Override
    public String toString() {
        return visualizer.draw(MIN_VECTOR, maxVector);
    }
}
