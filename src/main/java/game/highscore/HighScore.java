package game.highscore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HighScore {
    private String playerName;
    private double secondsToEnd;
    private int numberOfSteps;
    private boolean gameWon;

    public String isGameWon()
    {
        int c;
        if(gameWon)
        {
            c = 0x2714;
        }
        else
        {
            c = 0x2716;
        }
        return String.valueOf((char) c);
    }
}
