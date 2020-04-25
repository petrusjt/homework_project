package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class CanvasPane extends Pane {
    final Canvas canvas;

    //  https://stackoverflow.com/a/53247942
    public CanvasPane(double width, double height) {
        setWidth(width);
        setHeight(height);
        canvas = new Canvas(width, height);
        canvas.setId("cnv_drawboard");
        getChildren().add(canvas);
        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
