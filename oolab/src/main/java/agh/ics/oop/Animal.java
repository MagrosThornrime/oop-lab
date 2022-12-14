package agh.ics.oop;


import javafx.scene.control.Label;

import java.util.LinkedList;
import java.util.List;

public class Animal extends AbstractWorldMapElement{
    private MapDirection orientation = MapDirection.NORTH;
    private final AbstractWorldMap map;

    private final List<IPositionChangeObserver> observers = new LinkedList<>();

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public Animal(AbstractWorldMap map, Vector2d initialPosition) {
        /*
        I must assume that if you create an Animal, its place is correct.
        Otherwise, it will be a source of confusion - WHEN EXACTLY the Animal
        is found on the map and when not?
         */
        this.map = map;
        addObserver(map);
        position = initialPosition;
        map.place(this);
    }

    public Animal(AbstractWorldMap map){
        this(map, new Vector2d(2, 2));
    }

    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observer: observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    private void modifyPosition(Vector2d deltaPosition) {
        Vector2d oldPosition = position;
        Vector2d newPosition = position.add(deltaPosition);
        if (map.canMoveTo(newPosition)){
            position = newPosition;
            positionChanged(oldPosition, newPosition);
        }
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();
            case FORWARD -> modifyPosition(orientation.toUnitVector());
            case BACKWARD -> modifyPosition(orientation.toUnitVector().opposite());
        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public String getResource(){
        return switch (orientation){
            case NORTH -> "oolab/src/main/resources/up.png";
            case EAST -> "oolab/src/main/resources/right.png";
            case SOUTH -> "oolab/src/main/resources/down.png";
            case WEST -> "oolab/src/main/resources/left.png";
        };
    }

    @Override
    public String labelText(){
        return "Z" + position.toString();
    }

}
