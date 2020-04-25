package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.tinylog.Logger;

import java.io.IOException;


public class MainWindowController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startButton;

    @FXML
    private Button highscoreButton;

    @FXML
    private Button exitButton;

    @FXML
    protected void startGame(MouseEvent event)
    {
        int gameWidth = 800;
        int gameHeight = 600;
        GameWindow gameLayout = new GameWindow(gameWidth, gameHeight);
        Scene gameScene = new Scene(gameLayout, gameWidth, gameHeight);
        Stage gameStage = new Stage();
        gameStage.setResizable(false);
        gameStage.setTitle("Game Window");
        gameStage.initModality(Modality.WINDOW_MODAL);
        gameStage.initOwner(anchorPane.getScene().getWindow());
        gameStage.setScene(gameScene);
        gameStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Logger.info("Closing GameWindow");
            }
        });
        gameStage.show();
        Logger.info("Created GameWindow");
    }

    @FXML
    protected void showHighScores(MouseEvent event)
    {
        //TODO create and open new window containing the high score table
    }

    @FXML
    protected void exitGame(MouseEvent event)
    {
        Logger.info("Closing application");
        Platform.exit();
    }

}
