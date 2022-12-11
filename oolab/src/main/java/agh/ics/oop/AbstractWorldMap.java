package agh.ics.oop;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public String toString() {
        Vector2d[] corners = findCorners();
        return visualizer.draw(corners[0], corners[1]);
    }

    public abstract Vector2d[] findCorners();

    public List<AbstractWorldMapElement> getElements() {
        return new ArrayList<>(elements.values());
    }
}
