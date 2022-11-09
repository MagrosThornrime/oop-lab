package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    private final Vector2d minVector = new Vector2d(0, 0);
    private final Vector2d maxVector = new Vector2d(4, 4);

    @Override
    public String toString() {
        return "Zwierzę zorientowane jest na " +
                orientation +
                " i znajduje się na pozycji " +
                position;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    private void modifyPosition(Vector2d delta) {
        Vector2d newPosition = position.add(delta);
        if (newPosition.follows(minVector) && newPosition.precedes(maxVector)) {
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

// moja propozycja jak sprawdzać, czy dwoje zwierząt jest na tych samych współrzędnych:
// użyć statycznej listy/hashmapy i za każdym razem, gdy stworzę zwierzę lub je przesunę,
// sprawdzić czy jego współrzędne już są w liście/hashmapie
// hashmapa byłaby efektywniejsza