package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class GameWindow extends Pane {
    Canvas canvas;
    public GameWindow(double width, double height){
        setWidth(width);
        setHeight(height);
        canvas = new Canvas(width, height);
        getChildren().add(canvas);

    }

    public Canvas getCanvas() {
        return canvas;
    }
}