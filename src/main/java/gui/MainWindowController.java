package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import languageloader.LanguageLoader;
import org.tinylog.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label playerNameLabel;
    @FXML
    private TextField playerName;
    @FXML
    private Button startButton;
    @FXML
    private Button highscoreButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button huLanguageSelectorButton;
    @FXML
    private Button enLanguageSelectorButton;

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
        gameStage.setTitle("Labyrinth");
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
            stage.setTitle(LanguageLoader.getGameStrings().getHighScoreWindowTitle());
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
            stage.setTitle(LanguageLoader.getGameStrings().getHelpWindowTitle());
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

    public void loadHungarianLanguage(MouseEvent mouseEvent) {
        LanguageLoader.loadGameStrings("hu");
        huLanguageSelectorButton.setDisable(true);
        enLanguageSelectorButton.setDisable(false);
        setTexts();
    }

    public void LoadEnglishLanguage(MouseEvent mouseEvent) {
        LanguageLoader.loadGameStrings("en");
        huLanguageSelectorButton.setDisable(false);
        enLanguageSelectorButton.setDisable(true);
        setTexts();
    }

    private void setTexts()
    {
        playerNameLabel.setText(LanguageLoader.getGameStrings().getPlayerNameLabelText());
        startButton.setText(LanguageLoader.getGameStrings().getStartButtonText());
        highscoreButton.setText(LanguageLoader.getGameStrings().getHighScoreButtonText());
        helpButton.setText(LanguageLoader.getGameStrings().getHelpButtonText());
        exitButton.setText(LanguageLoader.getGameStrings().getExitButtonText());
        if(anchorPane.getScene() != null)
        {
            ((Stage)anchorPane.getScene().getWindow()).setTitle(LanguageLoader.getGameStrings().getMainWindowTitle());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTexts();
        if(LanguageLoader.getLanguage() == "hu")
        {
            huLanguageSelectorButton.setDisable(true);
            enLanguageSelectorButton.setDisable(false);
        }
        else
        {
            huLanguageSelectorButton.setDisable(false);
            enLanguageSelectorButton.setDisable(true);
        }
        anchorPane.setStyle("-fx-background-image: url('mainwindow_background.png')");
    }
}
