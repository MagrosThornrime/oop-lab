package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{

    protected final List<AbstractWorldMapElement> elements = new ArrayList<>();

    protected final MapVisualizer visualizer = new MapVisualizer(this);

    @Override
    public boolean isOccupied(Vector2d position){
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return elements.stream()
                .filter(element -> element.isAt(position))
                .findAny()
                .orElse(null);
    }

    public void deleteObject(AbstractWorldMapElement object){
        elements.remove(object);
    }

    public Vector2d[] elementPositions() {
        return elements.stream()
                        .map(AbstractWorldMapElement::getPosition)
                        .toArray(Vector2d[]::new);
    }

    public String toString() {
        Vector2d[] corners = findCorners();
        return visualizer.draw(corners[0], corners[1]);
    }

    protected abstract Vector2d[] findCorners();

    public abstract boolean place(AbstractWorldMapElement element);

}
