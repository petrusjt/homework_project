package gui;

import game.controller.GameController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.tinylog.Logger;


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
        int gameWidth = 600;
        int gameHeight = 600;
        GameWindow gameLayout = new GameWindow(gameWidth, gameHeight);
        GameController gameController = new GameController(gameLayout.getCanvas());
        Scene gameScene = new Scene(gameLayout, gameWidth, gameHeight);
        Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        gameStage.setResizable(false);
        gameStage.setTitle("Game Window");
        gameStage.setScene(gameScene);
        gameStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                gameController.handleOnCloseRequest(event);
            }
        });
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameController.handleKeyEvent(event);
            }
        });
        gameController.setCanvas(gameLayout.getCanvas());
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
