package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private final VBox vBox;

    private final ImageGetter imageGetter;

    private ImageView startImageView(AbstractWorldMapElement element) {
        String source = element.getResource();
        Image image;
        try {
            image = imageGetter.getImage(source);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        return imageView;
    }

    private Label createLabel(AbstractWorldMapElement element){
        return new Label(element.labelText());
    }

    private VBox createVBox(ImageView imageView, Label label){
        VBox vBox = new VBox();
        vBox.getChildren().addAll(imageView, label);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-border: 2px solid; -fx-border-color: black;");
        return vBox;
    }

    public GuiElementBox(AbstractWorldMapElement element, ImageGetter imageGetter){
        this.imageGetter = imageGetter;
        ImageView imageView = startImageView(element);
        Label label = createLabel(element);
        vBox = createVBox(imageView, label);
    }

    public VBox getvBox() {
        return vBox;
    }
}
