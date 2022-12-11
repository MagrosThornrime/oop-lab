package agh.ics.oop;

import java.util.Random;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {
    private int fields;

    private final Random randomGenerator;

    private final MapBoundary boundary = new MapBoundary();

    private GrassField(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }
    public GrassField(int fields, Random randomGenerator) {
        this(randomGenerator);
        this.fields = fields;
        placeFields(fields);
    }
    private int randomCoordinate(int max) {
        return (int) round(sqrt(randomGenerator.nextInt(max + 1) * 10));
    }


    @Override
    public boolean canMoveTo(Vector2d position){
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public void place(Animal animal) {
        if (!canMoveTo(animal.getPosition())) {
            throw new IllegalArgumentException("Can't place animal on coords: " + animal.getPosition());
        }
        elements.put(animal.getPosition(), animal);
        boundary.insertElement(animal);
    }

    private boolean place(Grass grass) {
        if(objectAt(grass.getPosition()) == null) {
            elements.put(grass.getPosition(), grass);
            boundary.insertElement(grass);
            return true;
        }
        return false;
    }

    public void placeOneField() {
        // here, using exceptions would be just ugly
        Vector2d position;
        do{
            position = new Vector2d(randomCoordinate(fields), randomCoordinate(fields));
        }while(!place(new Grass(position)));
    }

    private void placeFields(int fields) {
        for(int i=0; i<fields; i++){
            placeOneField();
        }
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if(oldPosition == newPosition) {
            return false;
        }
        Animal animal = (Animal) objectAt(oldPosition);
        AbstractWorldMapElement lastElement = (AbstractWorldMapElement) objectAt(newPosition);
        elements.remove(oldPosition);
        elements.put(newPosition, animal);
        boundary.positionChanged(oldPosition, newPosition);
        if(lastElement instanceof Grass) {
            placeOneField();
        }
        return true;
    }

    @Override
    public Vector2d[] findCorners(){
        Vector2d lowerLeft = new Vector2d(boundary.xMin(), boundary.yMin());
        Vector2d upperRight = new Vector2d(boundary.xMax(), boundary.yMax());
        return new Vector2d[]{lowerLeft, upperRight};
    }
}
