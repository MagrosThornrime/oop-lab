package agh.ics.oop.gui;

import agh.ics.oop.MoveDirection;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.SimulationEngine;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class GuiInputBox {

    private final SimulationEngine engine;

    private Thread engineThread;

    private final OptionsParser parser = new OptionsParser();

    public final TextField textField = new TextField();

    public Button button = new Button("Start");

    private final HBox hBox;

    public GuiInputBox(SimulationEngine engine){
        this.engine = engine;
        prepareButton();
        hBox = new HBox(button, textField);
    }

    private void prepareButton(){
        button.setOnAction(action -> {
            if(engineThread != null){
                engineThread.interrupt();
            }
            String text = textField.getText();
            MoveDirection[] directions = parser.parse(text.split(" "));
            engine.setDirections(directions);
            engineThread = new Thread(engine);
            engineThread.start();
        });
    }

    public HBox getHBox(){
        return hBox;
    }

}
