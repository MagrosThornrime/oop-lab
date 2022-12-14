package agh.ics.oop.gui;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ImageGetter {
    private final Map<String, Image> images = new HashMap<>();

    private void createImage(String source) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(source));
        images.put(source, image);
    }


    public Image getImage(String source) throws FileNotFoundException {
        if(!images.containsKey(source)){
            createImage(source);
        }
        return images.get(source);
    }
}
