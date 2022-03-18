package game.utilities.helpers;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Point2D {

    private int x;
    private int y;

    public void setXY(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(final Point2D location) {
        setXY(location.getX(), location.getY());
    }
}
