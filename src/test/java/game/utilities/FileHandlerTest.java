package game.utilities;

import game.utilities.helpers.Point2D;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void loadMap() {
        final List<Wall> walls = new ArrayList<>();
        try {
            walls.addAll(FileHandler.loadMap());
        } catch (JAXBException e) {
            e.printStackTrace();
            fail("JAXBException occurred");
        }
        final List<Wall> wallls = new ArrayList<>();
        wallls.add(new Wall(new Point2D(0,0), new Point2D(1,0)));
        wallls.add(new Wall(new Point2D(1,2), new Point2D(2,2)));
        wallls.add(new Wall(new Point2D(2,1), new Point2D(2,2)));
        wallls.add(new Wall(new Point2D(4,1), new Point2D(4,2)));
        for(final Wall wall : wallls)
        {
            assertTrue(walls.contains(wall));
        }

    }
}