package gui;

import game.utilities.MainMenuLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelpWindowController implements Initializable {
    @FXML
    private TextArea textArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InputStream is = getClass().getClassLoader().getResourceAsStream("game_description.txt");
        Scanner scanner = new Scanner(is);
        String gameDescription = "";
        while(scanner.hasNextLine())
        {
            gameDescription += scanner.nextLine() + "\n";
        }
        textArea.setText(gameDescription);
    }

    public void backToMainMenu(MouseEvent event) {
        MainMenuLoader.loadMainMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
