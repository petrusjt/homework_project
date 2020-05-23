package gui;

import game.utilities.MainMenuLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import languageloader.LanguageLoader;
import org.tinylog.Logger;

public final class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LanguageLoader.loadGameStrings("hu");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon.png")));
        MainMenuLoader.loadMainMenu(primaryStage);
    }
    public static void main(String[] args) {
        Logger.info("Starting application");
        launch(args);
    }
}
