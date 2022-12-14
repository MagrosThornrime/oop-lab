package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class App extends Application implements IMoveObserver{
    private final GridPane grid = new GridPane();
    private AbstractWorldMap map;
    private int xMin, xMax, yMin, yMax;

    private SimulationEngine engine;

    private String[] args;

    private HBox input;
    private final ImageGetter imageGetter = new ImageGetter();

    private final Map<Vector2d, Node> nodes = new HashMap<>();

    private int moveDelay = 1500;

    private void updateCorners(){
        Vector2d[] corners = map.findCorners();
        xMin = corners[0].x;
        yMin = corners[0].y;
        xMax = corners[1].x;
        yMax = corners[1].y;
    }

    @Override
    public void init(){
        map = new GrassField(10, new Random(0));
        Vector2d[] initialPositions = {new Vector2d(2, 2)};
        updateCorners();
        engine = new SimulationEngine(map, initialPositions);
        engine.addObserver(this);
        engine.setMoveDelay(moveDelay);
    }

    private int xGrid(int xOriginal){
        return xOriginal - xMin + 1;
    }

    private int yGrid(int yOriginal){
        return yMax - yOriginal + 2;
    }

    private void placeInput(){
        GuiInputBox inputBox = new GuiInputBox(engine);
        input = inputBox.getHBox();
        grid.add(input, 0, 0, 10, 1);
    }

    private void placeLabel(String content, int column, int row){
        Label label = new Label(content);
        label.setStyle("-fx-border: 2px solid; -fx-border-color: black;");
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMaxHeight(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        grid.add(label, column, row, 1, 1);
        nodes.put(new Vector2d(column, row), label);
    }

    private void emptyMap(){
        placeLabel("y/x", 0, 1);
        for(int i=xMin; i<=xMax; i++){
            placeLabel(Integer.toString(i + xMin), xGrid(i), 1);
        }
        for(int i=yMin; i<=yMax; i++){
            placeLabel(Integer.toString(i), 0, yGrid(i));
        }
        for(int i=xMin; i<=xMax; i++){
            for(int j=yMin; j<=yMax; j++){
                placeLabel("", xGrid(i), yGrid(j));
            }
        }
    }

    private void placeElement(AbstractWorldMapElement element){
        if(limitsUpdated()){
           return;
        }
        int xOriginal = element.getPosition().x;
        int yOriginal = element.getPosition().y;
        GuiElementBox elementBox = new GuiElementBox(element, imageGetter);
        VBox vBox = elementBox.getvBox();
        grid.add(vBox, xGrid(xOriginal), yGrid(yOriginal), 1, 1);
        nodes.put(new Vector2d(xGrid(xOriginal), yGrid(yOriginal)), vBox);
    }

    private void placeAllElements(){
        for(AbstractWorldMapElement element: map.getElements()){
            placeElement(element);
        }
    }

    private void setConstraints(){
        int width = 50, height = 50;
        for(int i=0; i<=xMax-xMin+1; i++){
            grid.getColumnConstraints().add(new ColumnConstraints(width));
        }
        for(int i=0; i<=yMax-yMin+2; i++){
            grid.getRowConstraints().add(new RowConstraints(height));
        }
    }

    private void clearGrid(){
        grid.getChildren().clear();
    }

    private boolean limitsUpdated(){
        int oldXMin=xMin, oldXMax=xMax, oldYMin=yMin, oldYMax=yMax;
        updateCorners();
        if(xMin != oldXMin || xMax != oldXMax || yMin != oldYMin || yMax != oldYMax){
            setConstraints();
            clearGrid();
            placeInput();
            emptyMap();
            placeAllElements();
            return true;
        }
        return false;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane.setHgrow(grid, Priority.ALWAYS);
        GridPane.setVgrow(grid, Priority.ALWAYS);
        setConstraints();
        placeInput();
        emptyMap();
        placeAllElements();
        Scene scene = new Scene(grid, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Node findGridNode(Vector2d position){
        Vector2d key = new Vector2d(xGrid(position.x), yGrid(position.y));
        if(!nodes.containsKey(key)){
            return null;
        }
        return nodes.get(key);
    }

    private void addRemainingElements(){
        map.getElements().stream()
                .filter(e-> findGridNode(e.getPosition()) instanceof Label)
                .forEach(this::placeElement);
    }

    @Override
    public void animalMoved(Vector2d oldPosition, MapDirection oldDirection, Animal animal) {
        Platform.runLater(() -> {
            Node oldNode = findGridNode(oldPosition);
            grid.getChildren().remove(oldNode);
            if(!oldPosition.equals(animal.getPosition())){
                placeLabel("", xGrid(oldPosition.x), yGrid(oldPosition.y));
            }
            Node newNode = findGridNode(animal.getPosition());
            grid.getChildren().remove(newNode);
            Vector2d newPosition = animal.getPosition();
            nodes.remove(new Vector2d(xGrid(newPosition.x), yGrid(newPosition.y)));
            placeElement(animal);
            addRemainingElements();
        });
    }
}
