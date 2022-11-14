package agh.ics.oop;


public class Animal extends AbstractWorldMapElement{
    private MapDirection orientation = MapDirection.NORTH;
    private AbstractWorldMap map;

    /*
    I must assume that if you create an Animal, its place is correct.
    Otherwise it will be a source of confusion - WHEN EXACTLY the Animal
    is found on the map and when not?
     */
    public Animal(AbstractWorldMap map){
        this(map, new Vector2d(2, 2));
        map.place(this);
    }
    public Animal(AbstractWorldMap map, Vector2d initialPosition) {
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

    private void modifyPosition(Vector2d deltaPosition) {
        Vector2d newPosition = position.add(deltaPosition);
        AbstractWorldMapElement objectFound = (AbstractWorldMapElement) map.objectAt(newPosition);
        if (!map.canMoveTo(newPosition)){
            return;
        }
        if (objectFound instanceof Grass){
            map.deleteObject(objectFound);
            position = newPosition;
            ((GrassField)map).placeOneField();
        }
        else if (objectFound == null){
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

}
