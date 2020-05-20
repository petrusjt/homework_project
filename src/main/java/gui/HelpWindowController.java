package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(FXMLLoader.getDefaultClassLoader().getResource("fxml/main_menu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Főmenü");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
