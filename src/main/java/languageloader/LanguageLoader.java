package languageloader;

import languageloader.model.GameStrings;
import lombok.Getter;
import org.tinylog.Logger;
import xmlhelper.JAXBHelper;

import javax.xml.bind.JAXBException;


public class LanguageLoader {
    @Getter
    private static GameStrings gameStrings;
    @Getter
    private static String language = "hu";

    public static void loadEnglishGameStrings()
    {
        language = "en";
        Logger.info("Loading english language.");
        try {
            gameStrings = JAXBHelper.fromXML(GameStrings.class, LanguageLoader.class.getClassLoader().getResourceAsStream("strings_en.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
            Logger.warn(e);
        }
    }

    public static void loadHungarianGameStrings()
    {
        language = "hu";
        Logger.info("Loading hungarian language.");
        try {
            gameStrings = JAXBHelper.fromXML(GameStrings.class, LanguageLoader.class.getClassLoader().getResourceAsStream("strings_hu.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
            Logger.warn(e);
        }
    }

}
