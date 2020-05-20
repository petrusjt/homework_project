package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.tinylog.Logger;

import java.io.IOException;


public class MainWindowController {
    @FXML
    private TextField playerName;

    @FXML
    private Button startButton;

    @FXML
    protected void startGame(MouseEvent event)
    {
        int gameWidth = 600;
        int gameHeight = 600;
        GameWindow gameLayout = new GameWindow(gameWidth, gameHeight);
        GameController gameController = new GameController(playerName.getText().strip().replaceAll("\\s",""), gameLayout.getCanvas());
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
        gameStage.show();
        Logger.info("Created GameWindow");
    }

    @FXML
    protected void showHighScores(MouseEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMLLoader.getDefaultClassLoader().getResource("fxml/high_score.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Scene highScoreScene = new Scene(fxmlLoader.load());
            stage.setScene(highScoreScene);
            stage.setTitle("Rekordok");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void exitGame(MouseEvent event)
    {
        Logger.info("Closing MainWindow");
        Platform.exit();
    }

    public void showHelp(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMLLoader.getDefaultClassLoader().getResource("fxml/help_window.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Scene highScoreScene = new Scene(fxmlLoader.load());
            stage.setScene(highScoreScene);
            stage.setTitle("Játékleírás");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void checkPlayerNameGiven(KeyEvent keyEvent) {
        String name = playerName.getText();
        name = name.strip().replaceAll("\\s","");
        if(name.length() > 0)
        {
            startButton.setDisable(false);
        }
        else
        {
            startButton.setDisable(true);
        }
    }
}
