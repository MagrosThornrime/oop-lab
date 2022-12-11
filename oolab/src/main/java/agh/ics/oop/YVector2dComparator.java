package agh.ics.oop;

import java.util.Comparator;

public class YVector2dComparator implements Comparator<Vector2d> {
    @Override
    public int compare(Vector2d firstVector, Vector2d secondVector) {
        int intResults = Integer.compare(firstVector.y, secondVector.y);
        if(intResults != 0){
            return intResults;
        }
        if(!firstVector.equals(secondVector)) {
            return 1;
        }
        return 0;
    }
}
