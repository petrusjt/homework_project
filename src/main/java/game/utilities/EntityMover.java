package game.utilities;

import game.utilities.entities.Entity;
import game.utilities.entities.Monster;
import game.utilities.entities.Player;
import org.tinylog.Logger;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Contains methods to check the given {@link Entity} object's ability to move in the given direction
 * and moving them in the given direction.
 * */
public class EntityMover {
    /**
     * Checks for the {@code entity}'s ability to move in given direction.
     * The only thing to block the {@link Entity}'s ability to move is a {@link Wall}
     * object in the given direction.
     * @param entity The entity to be checked.
     * @param direction The direction to be checked
     * @param walls The list of {@link Wall} objects
     * @return Whether the {@link Entity} is able to move in the given direction
     * */
    public static boolean canEntityMove(Entity entity, Directions.Direction direction, List<Wall> walls) {
        System.out.println(entity);
        Point2D playerPosition = entity.getPosition();
        Point wallStart = new Point(0,0);
        Point wallEnd = new Point(0,0);
        if(direction == Directions.UP)
        {
            wallStart.x = (int) playerPosition.getX();
            wallStart.y = (int) playerPosition.getY();
            wallEnd.x = (int) (playerPosition.getX() + 1);
            wallEnd.y = (int) playerPosition.getY();
        }
        else if(direction == Directions.DOWN)
        {
            wallStart.x = (int) playerPosition.getX();
            wallStart.y = (int) (playerPosition.getY() + 1);
            wallEnd.x = (int) (playerPosition.getX() + 1);
            wallEnd.y = (int) (playerPosition.getY() + 1);
        }
        else if(direction == Directions.LEFT)
        {
            wallStart.x = (int) playerPosition.getX();
            wallStart.y = (int) playerPosition.getY();
            wallEnd.x = (int) playerPosition.getX();
            wallEnd.y = (int) (playerPosition.getY() + 1);
        }
        else if(direction == Directions.RIGHT)
        {
            wallStart.x = (int) (playerPosition.getX() + 1);
            wallStart.y = (int) playerPosition.getY();
            wallEnd.x = (int) (playerPosition.getX() + 1);
            wallEnd.y = (int) (playerPosition.getY() + 1);
        }

        //TODO: find a better name for this object
        Wall checkWall = new Wall(wallStart,wallEnd);

        boolean canMove = !walls.contains(checkWall);

        if(canMove)
            Logger.debug("Did not find wall so Player can move in the tested direction");
        else
            Logger.debug("Found wall so Player cannot move in the tested direction");

        return canMove;
    }

    /**
     * Moves the player in the given direction.
     * @param player The player {@link Entity} to be moved
     * @param direction the direction to move the player in
     * */
    public static void movePlayer(Entity player, Directions.Direction direction) {
        Point2D playerPosition = player.getPosition();
        Point newPlayerPosition = new Point();
        if(direction == Directions.UP)
        {
            newPlayerPosition.x = (int) playerPosition.getX();
            newPlayerPosition.y = (int) playerPosition.getY() - 1;
        }
        else if(direction == Directions.DOWN)
        {
            newPlayerPosition.x = (int) playerPosition.getX();
            newPlayerPosition.y = (int) playerPosition.getY() + 1;
        }
        else if(direction == Directions.LEFT)
        {
            newPlayerPosition.x = (int) playerPosition.getX() - 1;
            newPlayerPosition.y = (int) playerPosition.getY();
        }
        else if(direction == Directions.RIGHT)
        {
            newPlayerPosition.x = (int) playerPosition.getX() + 1;
            newPlayerPosition.y = (int) playerPosition.getY();
        }
        player.setPosition(newPlayerPosition);
    }

    /**
     * Moves the monster based on where the player is.
     * @param monster The {@link Monster} object to be moved
     * @param player The {@link Player} object the monster is moved relative to
     * @param walls The list of {@link Wall} objects
     * */
    public static void moveMonster(Entity monster, Entity player, List<Wall> walls) {
        moveMonsterOnce(monster, player, walls);
        moveMonsterOnce(monster, player, walls);
    }

    private static void moveMonsterOnce(Entity monster, Entity player, List<Wall> walls) {
        Point2D playerPosition = player.getPosition();
        Point2D monsterPosition = monster.getPosition();
        if(playerPosition.getX() < monsterPosition.getX() && canEntityMove(monster, Directions.LEFT, walls))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX() - 1, (int) monsterPosition.getY()));
        }
        else if(playerPosition.getX() > monsterPosition.getX() && canEntityMove(monster, Directions.RIGHT, walls))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX() + 1, (int) monsterPosition.getY()));
        }
        else if(playerPosition.getY() < monsterPosition.getY() && canEntityMove(monster, Directions.UP, walls))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX(), (int) monsterPosition.getY() - 1));
        }
        else if(playerPosition.getY() > monsterPosition.getY() && canEntityMove(monster, Directions.DOWN, walls))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX(), (int) monsterPosition.getY() + 1));
        }

    }

    /**
     * Returns whether the player's last step is the winning move.
     * @param player The {@link Player} object to be checked
     * @param direction The direction the player wants to move in
     * @param NUMBER_OF_CELLS The number of cells in a column or row in the grid.
     * @return Whether the player's move is the winning move
     * */
    public static boolean isWinningMove(Entity player, Directions.Direction direction, int NUMBER_OF_CELLS) {
        if(direction == Directions.UP)
        {
            if(player.getPosition().getY() == 0)
            {
                return true;
            }
        }
        else if(direction == Directions.DOWN)
        {
            if(player.getPosition().getY() == NUMBER_OF_CELLS)
            {
                return true;
            }
        }
        else if(direction == Directions.LEFT)
        {
            if(player.getPosition().getX() == 0)
            {
                return true;
            }
        }
        else if(direction == Directions.RIGHT)
        {
            if(player.getPosition().getY() == NUMBER_OF_CELLS)
            {
                return true;
            }
        }
        return false;
    }
}
