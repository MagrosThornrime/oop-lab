package agh.ics.oop;

import java.util.Comparator;

public class XVector2dComparator implements Comparator<Vector2d> {
    @Override
    public int compare(Vector2d firstVector, Vector2d secondVector) {
        int intResults = Integer.compare(firstVector.x, secondVector.x);
        if(intResults != 0){
            return intResults;
        }
        if(!firstVector.equals(secondVector)) {
            return 1;
        }
        return 0;
    }
}
