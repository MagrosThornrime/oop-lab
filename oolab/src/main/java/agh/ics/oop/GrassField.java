package agh.ics.oop;

import java.util.Random;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {
    private int fields;

    private final Random randomGenerator;

    public GrassField(int fields) {
        this.fields = fields;
        randomGenerator = new Random();
        placeFields(fields);
    }

    public GrassField(int fields, long seed) {
        this.fields = fields;
        randomGenerator = new Random(seed);
        placeFields(fields);
    }

    private int randomCoordinate(int max) {
        return (int) round(sqrt(randomGenerator.nextInt(max + 1) * 10));
    }


    @Override
    public boolean canMoveTo(Vector2d position){
        return true;
    }

    public void placeOneField() {
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
    public boolean place(AbstractWorldMapElement element) {
        AbstractWorldMapElement objectAtPosition = (AbstractWorldMapElement) objectAt(element.getPosition());
        if(objectAtPosition instanceof Animal) {
            return false;
        }
        if(objectAtPosition instanceof Grass) {
            if(element instanceof Animal) {
                elements.remove(objectAtPosition);
                elements.add(element);
                placeOneField();
                return true;
            }
            return false;
        }
        elements.add(element);
        return true;

    }

    protected Vector2d[] findCorners(){
        Vector2d[] positions = elementPositions();
        int xMin = positions[0].x, xMax = positions[0].x;
        int yMin = positions[0].y, yMax = positions[0].y;
        for(Vector2d position: positions) {
            if(position.x < xMin) {
                xMin = position.x;
            }
            else if(position.x > xMax) {
                xMax = position.x;
            }
            if(position.y < yMin) {
                yMin = position.y;
            }
            else if(position.y > yMax) {
                yMax = position.y;
            }
        }
        Vector2d lowerLeft = new Vector2d(xMin, yMin);
        Vector2d upperRight = new Vector2d(xMax, yMax);
        return new Vector2d[]{lowerLeft, upperRight};
    }
}
