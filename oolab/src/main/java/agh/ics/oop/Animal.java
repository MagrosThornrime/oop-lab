package agh.ics.oop;

import java.util.Objects;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;
    private IWorldMap map;

    private static final Vector2d INITIAL_VECTOR = new Vector2d(2, 2);


    /*
    I must assume that if you create an Animal, its place is correct.
    Otherwise it will be a source of confusion - WHEN EXACTLY the Animal
    is found on the map and when not?
     */
    public Animal(IWorldMap map){
        this.map = map;
        position = INITIAL_VECTOR;
        map.place(this);
    }
    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        position = initialPosition;
        map.place(this);
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

    public boolean isAt(Vector2d position) {
        return Objects.equals(this.position, position);
    }

    private void modifyPosition(Vector2d deltaPosition) {
        Vector2d newPosition = position.add(deltaPosition);
        if (map.canMoveTo(newPosition) && !map.isOccupied(newPosition)) {
            position = newPosition;
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

    public Vector2d getPosition(){ return position; }

}
