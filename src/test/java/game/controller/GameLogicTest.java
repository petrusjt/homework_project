package game.controller;

import game.utilities.entities.Monster;
import game.utilities.entities.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;

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
}