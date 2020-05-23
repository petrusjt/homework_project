package languageloader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanguageLoaderTest {

    @Test
    void loadGameStrings() {
        LanguageLoader.loadGameStrings("en");
        assertEquals("Back", LanguageLoader.getGameStrings().getBackToMainWindowButtonText());
        assertEquals("Main menu", LanguageLoader.getGameStrings().getMainWindowTitle());
        assertEquals("Exit", LanguageLoader.getGameStrings().getExitButtonText());
        assertEquals("Escaped?", LanguageLoader.getGameStrings().getGameWonColumnText());
        assertEquals("Time", LanguageLoader.getGameStrings().getSecondsToEndColumnText());

        LanguageLoader.loadGameStrings("hu");
        assertEquals("Vissza", LanguageLoader.getGameStrings().getBackToMainWindowButtonText());
        assertEquals("Főmenü", LanguageLoader.getGameStrings().getMainWindowTitle());
        assertEquals("Kilépés", LanguageLoader.getGameStrings().getExitButtonText());
        assertEquals("Kijutott?", LanguageLoader.getGameStrings().getGameWonColumnText());
        assertEquals("Idő", LanguageLoader.getGameStrings().getSecondsToEndColumnText());
    }

    @Test
    void getLanguage() {
        LanguageLoader.loadGameStrings("hu");
        assertEquals("hu",LanguageLoader.getLanguage());
        LanguageLoader.loadGameStrings("HU");
        assertEquals("hu",LanguageLoader.getLanguage());
        LanguageLoader.loadGameStrings("hU");
        assertEquals("hu",LanguageLoader.getLanguage());
        LanguageLoader.loadGameStrings("Hu");
        assertEquals("hu",LanguageLoader.getLanguage());

        LanguageLoader.loadGameStrings("en");
        assertEquals("en",LanguageLoader.getLanguage());
        LanguageLoader.loadGameStrings("EN");
        assertEquals("en",LanguageLoader.getLanguage());
        LanguageLoader.loadGameStrings("eN");
        assertEquals("en",LanguageLoader.getLanguage());
        LanguageLoader.loadGameStrings("En");
        assertEquals("en",LanguageLoader.getLanguage());
    }
}