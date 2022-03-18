package game.highscore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Data class that represents {@code HighScore} objects stored in the save file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HighScore {
    private String playerName;
    private double secondsToEnd;
    private int numberOfSteps;
    private boolean gameWon;

    /**
     * Custom getter for field {@code gameWon}.
     *
     * @return {@link String} object containing either character Unicode 0x2714 or character Unicode 0x2716 depending on field {@code gameWon}
     */
    public String isGameWon() {
        int c;
        if (gameWon) {
            c = 0x2714;
        } else {
            c = 0x2716;
        }
        return String.valueOf((char) c);
    }
}
