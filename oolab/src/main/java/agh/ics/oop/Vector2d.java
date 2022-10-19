package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    private final int x, y;
    private int newX, newY;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(%d,%d)".formatted(x, y);
    }

    public boolean precedes(Vector2d other) {
        return (x <= other.x && y <= other.y);
    }

    public boolean follows(Vector2d other) {
        return (x >= other.x && y >= other.y);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return add(other.opposite());
    }

    public Vector2d upperRight(Vector2d other) {
        newX = x;
        newY = y;
        if (other.x > x) {
            newX = other.x;
        }
        if (other.y > y) {
            newY = other.y;
        }
        return new Vector2d(newX, newY);
    }

    public Vector2d lowerLeft(Vector2d other) {
        newX = x;
        newY = y;
        if (other.x < x) {
            newX = other.x;
        }
        if (other.y < y) {
            newY = other.y;
        }
        return new Vector2d(newX, newY);
    }

    public Vector2d opposite() {
        return new Vector2d(-x, -y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d that))
            return false;
        return that.x == x && that.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
