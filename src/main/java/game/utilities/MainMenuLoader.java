package game.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

/**
 * A helper class with the only purpose of loading the main menu.
 *
 * */
public class MainMenuLoader {
    /**
     * Loads the main menu.
     * @param stage The {@link Stage} the menu should load in
     * */
    public static void loadMainMenu(Stage stage)
    {
        Logger.info("Game over. Loading main menu.");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(FXMLLoader.getDefaultClassLoader().getResource("fxml/main_menu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Főmenü");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
