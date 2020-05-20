package game.utilities;

import game.highscore.HighScore;
import game.highscore.HighScores;
import org.tinylog.Logger;
import xmlhelper.JAXBHelper;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static void loadMap(List<Wall> wallls) throws JAXBException {
        Logger.info("Loading the map from resource: maps/map.xml");
        game.controller.mapxmlreader.Walls walls = JAXBHelper.fromXML(game.controller.mapxmlreader.Walls.class, FileHandler.class.getClassLoader().getResourceAsStream("maps/map.xml"));
        for(game.controller.mapxmlreader.Wall wall : walls.getWall())
        {
            wallls.add(new Wall(wall.getStart().getX(),wall.getStart().getY(), wall.getEnd().getX(), wall.getEnd().getY()));
        }
    }

    public static void savePlayerStats(String playerName, double v, int numberOfSteps, boolean gameLost) throws IOException, JAXBException {
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
        highScores.getHighScores().add(new HighScore(playerName, v / 1000.0, numberOfSteps, !gameLost));
        OutputStream os = new FileOutputStream(saveFile);
        JAXBHelper.toXML(highScores, os);
        os.close();
    }

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
