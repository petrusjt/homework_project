package languageloader;

import languageloader.model.Description;
import xmlhelper.JAXBHelper;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

/**
 * Helper class for loading the game's description.
 * */
public class DescriptionLoader {
    /**
     * Converts the object created from game_description_*.xml to string.
     * @return the resulting string
     * @throws {@link JAXBException} if any problem occurs during deserialization
     * */
    public static String getDescription() throws JAXBException {
        String desc = "";
        try {
            InputStream is = DescriptionLoader.class.getClassLoader().getResourceAsStream(LanguageLoader.getGameStrings().getGameDescriptionURL());
            Description description = JAXBHelper.fromXML(Description.class, is);
            desc += description.getGameplayString() + "\n" + description.getGameplayDescription() + "\n"
                    + description.getNameString() + "\n" + description.getNameDescription() + "\n" +
                    description.getHighScoreString() + "\n" + description.getHighScoreDescription();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw e;
        }

        return desc;
    }
}
