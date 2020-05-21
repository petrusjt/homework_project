package game.utilities;

import game.highscore.HighScore;
import game.highscore.HighScores;
import org.tinylog.Logger;
import xmlhelper.JAXBHelper;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling file operations.
 * */
public class FileHandler {
    /**
     * Loads the map of the game.
     * @param wallls The {@link List<Wall>} object to load the map into
     * @throws {@link JAXBException} if any problem occurs during deserialization
     * */
    public static void loadMap(List<Wall> wallls) throws JAXBException {
        Logger.info("Loading the map from resource: maps/map.xml");
        game.controller.mapxmlreader.Walls walls = JAXBHelper.fromXML(game.controller.mapxmlreader.Walls.class, FileHandler.class.getClassLoader().getResourceAsStream("maps/map.xml"));
        for(game.controller.mapxmlreader.Wall wall : walls.getWall())
        {
            wallls.add(new Wall(wall.getStart().getX(),wall.getStart().getY(), wall.getEnd().getX(), wall.getEnd().getY()));
        }
    }

    /**
     * Saves the player's stats.
     * @param playerName The player's name
     * @param elapsedTime The time of completing of failing the labyrinth
     * @param numberOfSteps The number of the steps the player did in the game
     * @param gameLost Whether the player lost or not
     * @throws IOException if any problem occurs opening the save file
     * @throws JAXBException if any problem occurs during serialization or deserialization
     * */
    public static void savePlayerStats(String playerName, double elapsedTime, int numberOfSteps, boolean gameLost) throws IOException, JAXBException {
        String userHome = System.getProperty("user.home");
        String separator = File.separator;
        String saveFilePath = userHome + separator + ".labyrinth" + separator + "savefile.xml";
        Logger.info("Loading save file from " + saveFilePath);

        File saveFile = new File(saveFilePath);
        InputStream is = new FileInputStream(saveFile);
        HighScores highScores = JAXBHelper.fromXML(HighScores.class, is);
        is.close();
        if(highScores.getHighScores() == null)
        {
            highScores.setHighScores(new ArrayList<>());
        }
        highScores.getHighScores().add(new HighScore(playerName, elapsedTime / 1000.0, numberOfSteps, !gameLost));
        OutputStream os = new FileOutputStream(saveFile);
        JAXBHelper.toXML(highScores, os);
        os.close();
    }

    /**
     * Checks for existing save file in the file system.
     * @return Whether the save file exists
     * */
    public static boolean doesSaveFileExist()
    {
        Logger.info("Checking for save file");

        String userHome = System.getProperty("user.home");
        String separator = File.separator;
        String saveFilePath = userHome + separator + ".labyrinth" + separator + "savefile.xml";
        Logger.debug("Save file location: " + saveFilePath);

        File saveFile = new File(saveFilePath);
        return saveFile.exists();
    }

    /**
     * Creates save file.
     * @throws {@link IOException} if any problem occurs opening the save file
     * @throws {@link JAXBException} if any problem occurs during serialization
     * */
    public static void createSaveFile() throws IOException, JAXBException {
        Logger.info("Save file not found. Creating save file.");

        String userHome = System.getProperty("user.home");
        String separator = File.separator;
        String saveFilePath = userHome + separator + ".labyrinth" + separator + "savefile.xml";
        String saveFileDir = userHome + separator + ".labyrinth";
        new File(saveFileDir).mkdirs();
        OutputStream os = new FileOutputStream(saveFilePath);
        HighScores highScores = new HighScores();
        JAXBHelper.toXML(highScores, os);
        os.close();
    }
}
