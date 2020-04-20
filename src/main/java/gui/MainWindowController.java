package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MainWindowController {
    @FXML
    Button btn_start;

    @FXML
    Button btn_highscores;

    @FXML
    Button btn_exit;

    @FXML
    protected void startGame(MouseEvent event)
    {
        //TODO create and open new window containing the game
    }

    @FXML
    protected void showHighScores(MouseEvent event)
    {
        //TODO create and open new window containing the high score table
    }

    @FXML
    protected void exitGame(MouseEvent event)
    {
        Platform.exit();
    }

}
