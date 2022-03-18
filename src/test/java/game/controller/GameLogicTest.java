package game.controller;

import game.utilities.entities.Monster;
import game.utilities.entities.Player;
import game.utilities.helpers.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    @Test
    void isGameLost() {
        final GameLogic gameLogic = new GameLogic("asd", null);
        final Player player = new Player(new Point2D(0,0));
        final Monster monster = new Monster(new Point2D(4,2));
        gameLogic.setMonster(monster);
        gameLogic.setPlayer(player);
        assertFalse(gameLogic.isGameLost());

        monster.setPosition(new Point2D(0,0));
        assertTrue(gameLogic.isGameLost());
    }

    @Test
    void doGameLogic() {
        final GameLogic gameLogic = new GameLogic("asd", new Canvas(400,400));
        try {
            gameLogic.doGameLogic(new KeyEvent(null, null, KeyEvent.ANY, "a", "a", KeyCode.RIGHT, false, false, false, false));
            assertEquals(new Point2D(1,0), gameLogic.getPlayer().getPosition());
            assertEquals(new Point2D(3,1), gameLogic.getMonster().getPosition());
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}