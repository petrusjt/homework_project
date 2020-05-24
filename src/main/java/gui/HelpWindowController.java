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
import languageloader.DescriptionLoader;
import languageloader.LanguageLoader;
import lombok.SneakyThrows;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
        try {
            textArea.setText(DescriptionLoader.getDescription());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        backButton.setText(LanguageLoader.getGameStrings().getBackToMainWindowButtonText());
        anchorPane.setStyle("-fx-background-image: url('mainwindow_background.png');-fx-background-size: 100% auto;");
    }

    public void backToMainMenu(MouseEvent event) {
        MainMenuLoader.loadMainMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
