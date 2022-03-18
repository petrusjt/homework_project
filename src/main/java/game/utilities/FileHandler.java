package game.utilities;

import game.controller.mapxmlreader.Walls;
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
     * */
    public static List<Wall> loadMap() throws JAXBException {
        Logger.info("Loading the map from resource: maps/map.xml");
        final Walls w = JAXBHelper.fromXML(Walls.class,
                FileHandler.class.getClassLoader().getResourceAsStream("maps/map.xml"));
        final List<Wall> walls = new ArrayList<>();
        for(game.controller.mapxmlreader.Wall wall : w.getWall())
        {
            walls.add(new Wall(wall.getStart().getX(), wall.getStart().getY(),
                    wall.getEnd().getX(), wall.getEnd().getY()));
        }
        return walls;
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
    public static void savePlayerStats(final String playerName,
                                       final double elapsedTime,
                                       final int numberOfSteps,
                                       final boolean gameLost) throws IOException, JAXBException {
        final String saveFilePath = getSaveFilePath();
        Logger.info("Loading save file from " + saveFilePath);

        final File saveFile = new File(saveFilePath);
        final InputStream is = new FileInputStream(saveFile);
        final HighScores highScores = JAXBHelper.fromXML(HighScores.class, is);
        is.close();
        if(highScores.getHighScores() == null)
        {
            highScores.setHighScores(new ArrayList<>());
        }
        highScores.getHighScores().add(new HighScore(playerName, elapsedTime / 1000.0, numberOfSteps, !gameLost));
        final OutputStream os = new FileOutputStream(saveFile);
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

        final String saveFilePath = getSaveFilePath();
        Logger.debug("Save file location: " + saveFilePath);

        final File saveFile = new File(saveFilePath);
        return saveFile.exists();
    }

    private static String getSaveFilePath() {
        final String userHome = System.getProperty("user.home");
        final String separator = File.separator;
        return userHome + separator + ".labyrinth" + separator + "savefile.xml";
    }

    /**
     * Creates save file.
     * */
    public static void createSaveFile() throws IOException, JAXBException {
        Logger.info("Save file not found. Creating save file.");

        final String userHome = System.getProperty("user.home");
        final String separator = File.separator;
        final String saveFilePath = userHome + separator + ".labyrinth" + separator + "savefile.xml";
        final String saveFileDir = userHome + separator + ".labyrinth";
        final boolean b = new File(saveFileDir).mkdirs();
        final OutputStream os = new FileOutputStream(saveFilePath);
        final HighScores highScores = new HighScores();
        JAXBHelper.toXML(highScores, os);
        os.close();
    }
}
