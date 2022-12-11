package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

public class World {
    public static void main(String[] args) {
        OptionsParser parser = new OptionsParser();
        MoveDirection[] directions;
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(2, 2)};

        try {
            String[] newArgs = {"forward", "backward", "hehe"};
            directions = parser.parse(newArgs);
            AbstractWorldMap map = new RectangularMap(10, 5);
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
//            System.exit(1);
        }

        try {
            directions = parser.parse(args);
            AbstractWorldMap map = new RectangularMap(10, 5);
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
//            System.exit(1);
        }
        Application.launch(App.class, args);
    }
}
