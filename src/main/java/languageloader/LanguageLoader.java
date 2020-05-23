package languageloader;

import languageloader.model.GameStrings;
import lombok.Getter;
import org.tinylog.Logger;
import xmlhelper.JAXBHelper;

import javax.xml.bind.JAXBException;

/**
 * Helper class for loading the application's texts in the given language.
 * */
public class LanguageLoader {
    @Getter
    private static GameStrings gameStrings;
    @Getter
    private static String language;

    /**
     * Loads the application's strings in the given language.
     * Currently there are only two available languages. English and hungarian.
     * @param languageCode The two-letter code of the language
     * */
    public static void loadGameStrings(String languageCode)
    {
        language = languageCode.toLowerCase();
        Logger.info("Loading hungarian language.");
        try {
            gameStrings = JAXBHelper.fromXML(GameStrings.class, LanguageLoader.class.getClassLoader().getResourceAsStream("strings_"+ language +".xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
            Logger.warn(e);
        }
    }
}
