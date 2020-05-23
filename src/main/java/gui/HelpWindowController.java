package gui;

import game.utilities.MainMenuLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import languageloader.LanguageLoader;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelpWindowController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextArea textArea;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(LanguageLoader.getGameStrings().getGameDescriptionURL());
        Scanner scanner = new Scanner(is);
        String gameDescription = "";
        while(scanner.hasNextLine())
        {
            gameDescription += scanner.nextLine() + "\n";
        }
        textArea.setText(gameDescription);
        backButton.setText(LanguageLoader.getGameStrings().getBackToMainWindowButtonText());
        anchorPane.setStyle("-fx-background-image: url('mainwindow_background.png');-fx-background-size: 100% auto;");
    }

    public void backToMainMenu(MouseEvent event) {
        MainMenuLoader.loadMainMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
