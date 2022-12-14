package agh.ics.oop;

import java.util.SortedMap;
import java.util.TreeMap;

public class MapBoundary implements IPositionChangeObserver{
    private final SortedMap<Vector2d, AbstractWorldMapElement> xOrdered = new TreeMap<>(new XVector2dComparator());
    private final SortedMap<Vector2d, AbstractWorldMapElement> yOrdered = new TreeMap<>(new YVector2dComparator());

    public void removeElement(Vector2d position) {
        xOrdered.remove(position);
        yOrdered.remove(position);
    }

    public void insertElement(AbstractWorldMapElement element) {
        xOrdered.put(element.getPosition(), element);
        yOrdered.put(element.getPosition(), element);
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition){
        if(oldPosition.equals(newPosition)) {
            return false;
        }
        if(xOrdered.containsKey(newPosition)) {
            removeElement(newPosition);
        }
        AbstractWorldMapElement element = xOrdered.get(oldPosition);
        removeElement(oldPosition);
        insertElement(element);
        return true;
    }

    public int xMin(){
        return xOrdered.firstKey().x;
    }

    public int xMax(){
        return xOrdered.lastKey().x;
    }

    public int yMin(){
        return yOrdered.firstKey().y;
    }

    public int yMax(){
        return yOrdered.lastKey().y;
    }

}
