package agh.ics.oop;



public class RectangularMap extends AbstractWorldMap{

    private static final Vector2d MIN_VECTOR = new Vector2d(0, 0);
    private final Vector2d maxVector;

    public RectangularMap(int width, int height){
        maxVector = new Vector2d(width, height);
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return objectAt(position) != null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        // the method's parameter suggest that you don't need
        // to check if there is another animal on your path
        // otherwise you would have to check the initial position of the animal
        return position.follows(MIN_VECTOR) && position.precedes(maxVector) && !isOccupied(position);
    }

    @Override
    protected Vector2d[] findCorners() {
        return new Vector2d[]{MIN_VECTOR, maxVector};
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if(oldPosition == newPosition) {
            return false;
        }
        Animal animal = (Animal) objectAt(oldPosition);
        elements.remove(oldPosition);
        elements.put(newPosition, animal);
        return true;
    }

}
