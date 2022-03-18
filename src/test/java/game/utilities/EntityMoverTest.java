package game.utilities;

import game.utilities.entities.Monster;
import game.utilities.entities.Player;
import game.utilities.helpers.Point2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityMoverTest {

    @Test
    void canEntityMove() {
        final List<Wall> walls = new ArrayList<>();
        final Player player = new Player(new Point2D(0,0));
        final EntityMover entityMover = new EntityMover(walls);

        walls.add(new Wall(0,0,1,0));
        walls.add(new Wall(0,0,0,1));
        walls.add(new Wall(0,1,1,1));
        walls.add(new Wall(1,0,1,1));

        assertFalse(entityMover.canEntityMove(player, Direction.UP));
        assertFalse(entityMover.canEntityMove(player, Direction.DOWN));
        assertFalse(entityMover.canEntityMove(player, Direction.LEFT));
        assertFalse(entityMover.canEntityMove(player, Direction.RIGHT));

        walls.remove(walls.size() - 1);
        assertTrue(entityMover.canEntityMove(player, Direction.RIGHT));
    }

    @Test
    void movePlayer() {
        final Player player = new Player(new Point2D(3,3));
        final EntityMover entityMover = new EntityMover(new ArrayList<>());
        entityMover.movePlayer(player, Direction.LEFT);
        assertEquals(new Point2D(2,3), player.getPosition());
        entityMover.movePlayer(player, Direction.UP);
        assertEquals(new Point2D(2,2), player.getPosition());
        entityMover.movePlayer(player, Direction.RIGHT);
        assertEquals(new Point2D(3,2), player.getPosition());
        entityMover.movePlayer(player, Direction.DOWN);
        assertEquals(new Point2D(3,3), player.getPosition());
    }

    @Test
    void moveMonster() {
        final Monster monster = new Monster(new Point2D(1,1));
        final Player player = new Player(new Point2D(0,0));
        final List<Wall> walls = new ArrayList<>();
        final EntityMover entityMover = new EntityMover(walls);
        entityMover.moveMonster(monster,player);
        assertEquals(player.getPosition(), monster.getPosition());
        player.setPosition(new Point2D(2,2));
        entityMover.moveMonster(monster,player);
        assertEquals(new Point2D(2,0), monster.getPosition());
        entityMover.moveMonster(monster,player);
        assertEquals(new Point2D(2,2), monster.getPosition());
        assertEquals(player.getPosition(), monster.getPosition());
    }

    @Test
    void isWinningMove() {
        final Player player = new Player(new Point2D(0,0));
        final EntityMover entityMover = new EntityMover(new ArrayList<>());
        assertTrue(entityMover.isWinningMove(player, Direction.UP, 6));
        assertTrue(entityMover.isWinningMove(player, Direction.LEFT, 6));
        assertFalse(entityMover.isWinningMove(player, Direction.RIGHT, 6));
        assertFalse(entityMover.isWinningMove(player, Direction.DOWN, 6));

        player.setPosition(new Point2D(6,3));
        assertFalse(entityMover.isWinningMove(player, Direction.UP, 6));
        assertFalse(entityMover.isWinningMove(player, Direction.LEFT, 6));
        assertTrue(entityMover.isWinningMove(player, Direction.RIGHT, 6));
        player.setPosition(new Point2D(6,6));
        assertTrue(entityMover.isWinningMove(player, Direction.RIGHT, 6));
        assertTrue(entityMover.isWinningMove(player, Direction.DOWN, 6));
    }
}