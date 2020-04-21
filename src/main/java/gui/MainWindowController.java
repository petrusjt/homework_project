package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    @FXML
    private AnchorPane pane_anchor;

    @FXML
    private Button btn_start;

    @FXML
    private Button btn_highscores;

    @FXML
    private Button btn_exit;

    @FXML
    protected void startGame(MouseEvent event)
    {
        CanvasPane gameLayout = new CanvasPane(800,600);
        Scene gameScene = new Scene(gameLayout, 800,600);
        Stage gameStage = new Stage();
        gameStage.setResizable(false);
        gameStage.setTitle("Game Window");
        gameStage.initModality(Modality.WINDOW_MODAL);
        gameStage.initOwner(pane_anchor.getScene().getWindow());
        gameStage.setScene(gameScene);
        gameStage.show();

    }

    @FXML
    protected void showHighScores(MouseEvent event)
    {
        //TODO create and open new window containing the high score table
    }

    @FXML
    protected void exitGame(MouseEvent event)
    {
        Logger.info("Application exit");
        Platform.exit();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
