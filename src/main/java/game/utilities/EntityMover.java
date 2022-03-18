package game.utilities;

import game.utilities.entities.Entity;
import game.utilities.entities.Monster;
import game.utilities.entities.Player;
import game.utilities.helpers.Point2D;
import org.tinylog.Logger;

import java.util.List;

/**
 * Contains methods to check the given {@link Entity} object's ability to move in the given direction
 * and moving them in the given direction.
 */
public class EntityMover {

    private final List<Wall> walls;

    public EntityMover(final List<Wall> walls) {
        this.walls = walls;
    }

    /**
     * Moves the player in the given direction.
     *
     * @param player    The player {@link Entity} to be moved
     * @param direction the direction to move the player in
     */
    public void movePlayer(final Entity player, final Direction direction) {
        final Point2D playerPosition = player.getPosition();
        Point2D newPlayerPosition = new Point2D();
        if (direction == Direction.UP) {
            newPlayerPosition.setXY(playerPosition.getX(), playerPosition.getY() - 1);
        } else if (direction == Direction.DOWN) {
            newPlayerPosition.setXY(playerPosition.getX(), playerPosition.getY() + 1);
        } else if (direction == Direction.LEFT) {
            newPlayerPosition.setXY(playerPosition.getX() - 1, playerPosition.getY());
        } else if (direction == Direction.RIGHT) {
            newPlayerPosition.setXY(playerPosition.getX() + 1, playerPosition.getY());
        }
        player.setPosition(newPlayerPosition);
    }

    /**
     * Moves the monster based on where the player is.
     *
     * @param monster The {@link Monster} object to be moved
     * @param player  The {@link Player} object the monster is moved relative to
     */
    public void moveMonster(Entity monster, Entity player) {
        moveMonsterOnce(monster, player);
        moveMonsterOnce(monster, player);
    }

    private void moveMonsterOnce(final Entity monster, final Entity player) {
        final Point2D playerPosition = player.getPosition();
        final Point2D monsterPosition = monster.getPosition();
        if (playerPosition.getX() < monsterPosition.getX() && canEntityMove(monster, Direction.LEFT)) {
            monsterPosition.setLocation(new Point2D(monsterPosition.getX() - 1, monsterPosition.getY()));
        } else if (playerPosition.getX() > monsterPosition.getX() && canEntityMove(monster, Direction.RIGHT)) {
            monsterPosition.setLocation(new Point2D(monsterPosition.getX() + 1, monsterPosition.getY()));
        } else if (playerPosition.getY() < monsterPosition.getY() && canEntityMove(monster, Direction.UP)) {
            monsterPosition.setLocation(new Point2D(monsterPosition.getX(), monsterPosition.getY() - 1));
        } else if (playerPosition.getY() > monsterPosition.getY() && canEntityMove(monster, Direction.DOWN)) {
            monsterPosition.setLocation(new Point2D(monsterPosition.getX(),monsterPosition.getY() + 1));
        }

    }

    /**
     * Checks for the {@code entity}'s ability to move in given direction.
     * The only thing to block the {@link Entity}'s ability to move is a {@link Wall}
     * object in the given direction.
     *
     * @param entity    The entity to be checked.
     * @param direction The direction to be checked
     * @return Whether the {@link Entity} is able to move in the given direction
     */
    public boolean canEntityMove(final Entity entity, final Direction direction) {
        final Point2D playerPosition = entity.getPosition();
        final Point2D wallStart = new Point2D(0, 0);
        final Point2D wallEnd = new Point2D(0, 0);

        if (direction == Direction.UP) {
            wallStart.setXY(playerPosition.getX(), playerPosition.getY());
            wallEnd.setXY(playerPosition.getX() + 1, playerPosition.getY());
        } else if (direction == Direction.DOWN) {
            wallStart.setXY(playerPosition.getX(), playerPosition.getY() + 1);
            wallEnd.setXY(playerPosition.getX() + 1, playerPosition.getY() + 1);
        } else if (direction == Direction.LEFT) {
            wallStart.setXY(playerPosition.getX(), playerPosition.getY());
            wallEnd.setXY(playerPosition.getX(), playerPosition.getY() + 1);
        } else if (direction == Direction.RIGHT) {
            wallStart.setXY(playerPosition.getX() + 1, playerPosition.getY());
            wallEnd.setXY(playerPosition.getX() + 1, playerPosition.getY() + 1);
        }

        //TODO: find a better name for this object
        final Wall checkedWall = new Wall(wallStart, wallEnd);

        final boolean canMove = !walls.contains(checkedWall);

        if (canMove) {
            Logger.debug("Did not find wall so Entity can move in the tested direction");
        } else {
            Logger.debug("Found wall so Entity cannot move in the tested direction");
        }

        return canMove;
    }

    /**
     * Returns whether the player's last step is the winning move.
     *
     * @param player          The {@link Player} object to be checked
     * @param direction       The direction the player wants to move in
     * @param numberOfCells The number of cells in a column or row in the grid.
     * @return Whether the player's move is the winning move
     */
    public boolean isWinningMove(final Entity player, final Direction direction, final int numberOfCells) {
        if (direction == Direction.UP) {
            return player.getPosition().getY() == 0;
        } else if (direction == Direction.DOWN) {
            return player.getPosition().getY() == numberOfCells;
        } else if (direction == Direction.LEFT) {
            return player.getPosition().getX() == 0;
        } else if (direction == Direction.RIGHT) {
            return player.getPosition().getX() == numberOfCells;
        }
        return false;
    }
}
