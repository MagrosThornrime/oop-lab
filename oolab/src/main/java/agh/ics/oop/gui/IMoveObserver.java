package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.MapDirection;
import agh.ics.oop.Vector2d;

public interface IMoveObserver {
    void animalMoved(Vector2d oldPosition, MapDirection oldDirection, Animal animal);
}
