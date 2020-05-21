package game.utilities;

import game.controller.GameLogic;
import game.utilities.entities.Monster;
import game.utilities.entities.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityMoverTest {

    @Test
    void canEntityMove() {
        List<Wall> walls = new ArrayList<>();
        Player player = new Player(new Point(0,0));

        walls.add(new Wall(0,0,1,0));
        walls.add(new Wall(0,0,0,1));
        walls.add(new Wall(0,1,1,1));
        walls.add(new Wall(1,0,1,1));

        assertFalse(EntityMover.canEntityMove(player, Directions.Direction.UP,walls));
        assertFalse(EntityMover.canEntityMove(player, Directions.Direction.DOWN,walls));
        assertFalse(EntityMover.canEntityMove(player, Directions.Direction.LEFT,walls));
        assertFalse(EntityMover.canEntityMove(player, Directions.Direction.RIGHT,walls));

        walls.remove(walls.size() - 1);
        assertTrue(EntityMover.canEntityMove(player, Directions.Direction.RIGHT,walls));
    }

    @Test
    void movePlayer() {
        Player player = new Player(new Point(3,3));
        EntityMover.movePlayer(player, Directions.Direction.LEFT);
        assertEquals(new Point(2,3), player.getPosition());
        EntityMover.movePlayer(player, Directions.Direction.UP);
        assertEquals(new Point(2,2), player.getPosition());
        EntityMover.movePlayer(player, Directions.Direction.RIGHT);
        assertEquals(new Point(3,2), player.getPosition());
        EntityMover.movePlayer(player, Directions.Direction.DOWN);
        assertEquals(new Point(3,3), player.getPosition());
    }

    @Test
    void moveMonster() {
        Monster monster = new Monster(new Point(1,1));
        Player player = new Player(new Point(0,0));
        List<Wall> walls = new ArrayList<>();
        EntityMover.moveMonster(monster,player,walls);
        assertEquals(player.getPosition(), monster.getPosition());
        player.setPosition(new Point(2,2));
        EntityMover.moveMonster(monster,player,walls);
        assertEquals(new Point(2,0), monster.getPosition());
        EntityMover.moveMonster(monster,player,walls);
        assertEquals(new Point(2,2), monster.getPosition());
        assertEquals(player.getPosition(), monster.getPosition());
    }

    @Test
    void isWinningMove() {
        Player player = new Player(new Point(0,0));
        assertTrue(EntityMover.isWinningMove(player,Directions.UP, 6));
        assertTrue(EntityMover.isWinningMove(player,Directions.LEFT, 6));
        assertFalse(EntityMover.isWinningMove(player,Directions.RIGHT, 6));
        assertFalse(EntityMover.isWinningMove(player,Directions.DOWN, 6));

        player.setPosition(new Point(6,3));
        assertFalse(EntityMover.isWinningMove(player,Directions.UP, 6));
        assertFalse(EntityMover.isWinningMove(player,Directions.LEFT, 6));
        assertTrue(EntityMover.isWinningMove(player,Directions.RIGHT, 6));
        player.setPosition(new Point(6,6));
        assertTrue(EntityMover.isWinningMove(player,Directions.RIGHT, 6));
        assertTrue(EntityMover.isWinningMove(player,Directions.DOWN, 6));
    }
}