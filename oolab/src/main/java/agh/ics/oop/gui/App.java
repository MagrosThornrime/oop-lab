package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;


public class App extends Application {
    private final GridPane grid = new GridPane();
    private AbstractWorldMap map;
    private int xMin, xMax, yMin, yMax;

    private String[] args;

    @Override
    public void init(){
        map = new GrassField(10, new Random(0));
        new Animal(map, new Vector2d(2, 2));
        Vector2d[] corners = map.findCorners();
        xMin = corners[0].x;
        yMin = corners[0].y;
        xMax = corners[1].x;
        yMax = corners[1].y;
    }

    private void emptyMap(GridPane grid){
        grid.add(new Text("y/x"), 0, 0, 1, 1);
        for(int i=0; i<=xMax-xMin; i++){
            Label label = new Label(Integer.toString(i + xMin));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, i+1, 0, 1, 1);
        }
        for(int i=0; i<=yMax-yMin; i++){
            Label label = new Label(Integer.toString(yMax - i));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0, i+1, 1, 1);
        }
    }

    private void mapElements(GridPane grid){
        for(AbstractWorldMapElement element: map.getElements()){
            int xOriginal = element.getPosition().x;
            int yOriginal = element.getPosition().y;
            int xGrid = xOriginal - xMin + 1;
            int yGrid = yMax - yOriginal + 1;
            Label label = new Label(element.toString());
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, xGrid, yGrid, 1, 1);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        args = getParameters().getRaw().toArray(new String[0]);
        int width = 20, height = 20;
        grid.setGridLinesVisible(true);
        for(int i=0; i<=xMax-xMin+1; i++){
            grid.getColumnConstraints().add(new ColumnConstraints(width));
        }
        for(int i=0; i<=yMax-yMin+1;i++){
            grid.getRowConstraints().add(new RowConstraints(height));
        }
        Scene scene = new Scene(grid, 400, 400);
        emptyMap(grid);
        mapElements(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
