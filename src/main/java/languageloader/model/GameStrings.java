package languageloader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model for the game's strings in a given language.
 * */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GameStrings {
    //MainWindowController
    private String mainWindowTitle;
    private String startButtonText;
    private String helpButtonText;
    private String highScoreButtonText;
    private String exitButtonText;
    private String playerNameLabelText;
    ///private String gameWindowTitle;
    private String highScoreWindowTitle;
    private String helpWindowTitle;
    //HighScoreWindowController
    private String backToMainWindowButtonText; //This will be used in every other controllers that require it
    private String playerNameColumnText;
    private String secondsToEndColumnText;
    private String numberOfStepsColumnText;
    private String gameWonColumnText;
    //HelpWindowController
    private String gameDescriptionURL;

}
