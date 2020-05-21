package game.controller;

import game.utilities.entities.Monster;
import game.utilities.entities.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    @Test
    void isGameLost() {
        GameLogic gameLogic = new GameLogic("asd", null);
        Player player = new Player(new Point(0,0));
        Monster monster = new Monster(new Point(4,2));
        gameLogic.setMonster(monster);
        gameLogic.setPlayer(player);
        assertEquals(false, gameLogic.isGameLost());

        monster.setPosition(new Point(0,0));
        assertEquals(true, gameLogic.isGameLost());
    }

    @Test
    void doGameLogic() {
        GameLogic gameLogic = new GameLogic("asd", new Canvas(400,400));
        try {
            gameLogic.doGameLogic(new KeyEvent(null, null, KeyEvent.ANY, "a", "a", KeyCode.RIGHT, false, false, false, false));
            assertEquals(new Point(1,0),gameLogic.getPlayer().getPosition());
            assertEquals(new Point(3,1), gameLogic.getMonster().getPosition());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}