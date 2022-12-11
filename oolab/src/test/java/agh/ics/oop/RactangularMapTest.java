package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RactangularMapTest {

    private Animal[] createAnimals(AbstractWorldMap map, Vector2d[] positions){
        return Arrays.stream(positions)
                .map(vector->new Animal(map, vector))
                .toArray(Animal[]::new);
    }

    private MoveDirection[] parseDirections(String directions) {
        return new OptionsParser().parse(directions.split(" "));
    }

    private Vector2d[] intRowToPositions(int[][] coords) {
        return new Vector2d[]{
            new Vector2d(coords[0][0], coords[0][1]),
            new Vector2d(coords[1][0], coords[1][1])
            };
    }

    private Vector2d[][] createResultPositions() {
        // TODO: move test results to some text file and just load them
        // it's so awful now
        int[][][] resultPositionsVectors = {
                {{2, 2}, {3, 4}}, // 0 (N, N)
                {{2, 3}, {3, 4}}, // f (N, N)
                {{2, 3}, {3, 3}}, // b (N, N)
                {{2, 3}, {3, 3}}, // r (E, N)
                {{2, 3}, {3, 3}}, // l (E, W)
                {{2, 3}, {3, 3}}, // f (E, W)
                {{2, 3}, {3, 3}}, // f (E, W)
                {{2, 3}, {3, 3}}, // r (S, W)
                {{2, 3}, {3, 3}}, // r (S, N)
                {{2, 2}, {3, 3}}, // f (S, N)
                {{2, 2}, {3, 4}}, // f (S, N)
                {{2, 1}, {3, 4}}, // f (S, N)
                {{2, 1}, {3, 5}}, // f (S, N)
                {{2, 0}, {3, 5}}, // f (S, N)
                {{2, 0}, {3, 5}}, // f (S, N)
                {{2, 0}, {3, 5}}, // f (S, N)
                {{2, 0}, {3, 5}}, // f (S, N)
        };
        return Arrays.stream(resultPositionsVectors)
                .map(this::intRowToPositions)
                .toArray(Vector2d[][]::new);
    }

    private MapDirection[] rowDirectionsFromString(String directions){
        return Arrays.stream(directions.split(" "))
                .map(MapDirection::fromString)
                .toArray(MapDirection[]::new);
    }

    private MapDirection[][] createOrientations() {
        String[] orientationStrings = {"N N", "N N", "N N", "E N", "E W", "E W",
                                        "E W", "S W", "S N", "S N", "S N", "S N",
                                        "S N", "S N", "S N", "S N"};
        return Arrays.stream(orientationStrings)
                .map(this::rowDirectionsFromString)
                .toArray(MapDirection[][]::new);
    }

    @Test
    public void singleAnimal(){
        AbstractWorldMap map = new RectangularMap(4, 4);
        Animal animal = new Animal(map);

        Vector2d[] positions = {new Vector2d(2, 4), new Vector2d(4, 4),
                                new Vector2d(4, 0), new Vector2d(0, 0),
                                new Vector2d(0, 4)};

        assertTrue(animal.isAt(new Vector2d(2, 2)));
        for(int i=0; i<5; i++){
            for(int j=0; j<8; j++){
                animal.move(MoveDirection.FORWARD);
            }
            assertSame(animal, map.objectAt(positions[i]));
            animal.move(MoveDirection.RIGHT);
        }
    }

    @Test
    public void twoAnimals() {
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        Animal[] animals = createAnimals(map, positions);
        MoveDirection[] directions = parseDirections("f b r l f f r r f f f f f f f f");

        Vector2d[][] resultPositions = createResultPositions();
        MapDirection[][] resultOrientations = createOrientations();

        for(int i=0; i<directions.length; i++){
            for(int j=0; j<2; j++){
                assertEquals(resultPositions[i][j], animals[j].getPosition());
                assertEquals(resultOrientations[i][j], animals[j].getOrientation());
            }
            animals[i % 2].move(directions[i]);
        }
    }

    @Test
    public void engineTwoAnimals() {
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        MoveDirection[] directions = parseDirections("f b r l f f r r f f f f f f f f");

        Vector2d[] expectedPositions = {new Vector2d(2, 0), new Vector2d(3, 5)};

        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        for (Vector2d position: expectedPositions){
            assertNotNull(map.objectAt(position));
        }
    }

    @Test
    public void twoAnimalsOnePlace() {
        AbstractWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(2, 2)};
        MoveDirection[] directions = parseDirections("f");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            SimulationEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        });

        assertEquals("Can't place animal on coords: (2,2)", thrown.getMessage());
    }

}
