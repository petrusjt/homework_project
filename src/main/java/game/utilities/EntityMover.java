package game.utilities;

import game.utilities.entities.Entity;
import org.tinylog.Logger;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class EntityMover {
    private static List<Wall> walls_inner;

    public static boolean canEntityMove(Entity entity, Directions.Direction direction) {
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

        boolean canMove = canMove = !walls_inner.contains(checkWall);

        if(canMove)
            Logger.debug("Did not find wall so Player can move in the tested direction");
        else
            Logger.debug("Found wall so Player cannot move in the tested direction");

        return canMove;
    }

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

    public static void moveMonster(Entity monster, Entity player) {
        moveMonsterOnce(monster, player);
        moveMonsterOnce(monster, player);
    }

    private static void moveMonsterOnce(Entity monster, Entity player) {
        Point2D playerPosition = player.getPosition();
        Point2D monsterPosition = monster.getPosition();
        if(playerPosition.getX() < monsterPosition.getX() && canEntityMove(monster, Directions.LEFT))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX() - 1, (int) monsterPosition.getY()));
        }
        else if(playerPosition.getX() > monsterPosition.getX() && canEntityMove(monster, Directions.RIGHT))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX() + 1, (int) monsterPosition.getY()));
        }
        else if(playerPosition.getY() < monsterPosition.getY() && canEntityMove(monster, Directions.UP))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX(), (int) monsterPosition.getY() - 1));
        }
        else if(playerPosition.getY() > monsterPosition.getY() && canEntityMove(monster, Directions.DOWN))
        {
            monsterPosition.setLocation(new Point((int) monsterPosition.getX(), (int) monsterPosition.getY() + 1));
        }

    }

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

    public static void setWalls(List<Wall> walls)
    {
        walls_inner = walls;
    }
}
