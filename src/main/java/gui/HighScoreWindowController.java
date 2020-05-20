package gui;

import game.highscore.HighScore;
import game.highscore.HighScores;
import game.utilities.MainMenuLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.tinylog.Logger;
import xmlhelper.JAXBHelper;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HighScoreWindowController implements Initializable {
    @FXML
    private TableView<HighScore> highScoreTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String userHome = System.getProperty("user.home");
        String separator = File.separator;
        String saveFilePath = userHome + separator + ".labyrinth" + separator + "savefile.xml";
        Logger.info("Loading save file data from " + saveFilePath);

        File saveFile = new File(saveFilePath);

        try {
            InputStream inputStream = new FileInputStream(saveFile);
            HighScores highScores = JAXBHelper.fromXML(HighScores.class, inputStream);

            TableColumn<HighScore, String> userNameCol = new TableColumn<>("Név");
            userNameCol.setPrefWidth(150);
            userNameCol.setStyle("-fx-alignment: CENTER");
            TableColumn<HighScore, Float> timeCol = new TableColumn<>("Idő");
            timeCol.setPrefWidth(150);
            timeCol.setStyle("-fx-alignment: CENTER");
            TableColumn<HighScore, Integer> stepsCol = new TableColumn<>("Lépések száma");
            stepsCol.setPrefWidth(150);
            stepsCol.setStyle("-fx-alignment: CENTER");
            TableColumn<HighScore, String> isFinishedCol = new TableColumn<>("Kijutott?");
            isFinishedCol.setPrefWidth(150);
            isFinishedCol.setStyle("-fx-alignment: CENTER");

            userNameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
            timeCol.setCellValueFactory(new PropertyValueFactory<>("secondsToEnd"));
            stepsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfSteps"));
            isFinishedCol.setCellValueFactory(new PropertyValueFactory<>("gameWon"));

            highScoreTable.getColumns().addAll(userNameCol,timeCol,stepsCol,isFinishedCol);

            for(HighScore highScore : highScores.getHighScores())
            {
                highScoreTable.getItems().add(highScore);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Logger.warn("Save file not found at " + saveFilePath);
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }

    public void backToMainMenu(MouseEvent event) {
        MainMenuLoader.loadMainMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
