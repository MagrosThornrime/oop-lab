package agh.ics.oop;


import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected final Map<Vector2d, AbstractWorldMapElement> elements = new HashMap<>();

    protected final MapVisualizer visualizer = new MapVisualizer(this);


    @Override
    public boolean isOccupied(Vector2d position){
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return elements.get(position);
    }

    public Vector2d[] elementPositions() {
        return elements.keySet().toArray(Vector2d[]::new);
    }

    public String toString() {
        Vector2d[] corners = findCorners();
        return visualizer.draw(corners[0], corners[1]);
    }

    protected abstract Vector2d[] findCorners();

    @Override
    public boolean place(Animal animal){
        if (canMoveTo(animal.getPosition())){
            elements.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

}
