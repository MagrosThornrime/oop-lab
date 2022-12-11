package agh.ics.oop;

import java.util.Objects;

public abstract class AbstractWorldMapElement {
    protected Vector2d position;


    public Vector2d getPosition() {
        return position;
    }

    public boolean isAt(Vector2d position) {
        return Objects.equals(this.position, position);
    }

    public abstract String toString();

}
