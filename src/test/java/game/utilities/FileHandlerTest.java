package game.utilities;

import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void loadMap() {
        List<Wall> walls = new ArrayList<>();
        try {
            FileHandler.loadMap(walls);
        } catch (JAXBException e) {
            e.printStackTrace();
            fail("JAXBException occurred");
        }
        List<Wall> wallls = new ArrayList<>();
        wallls.add(new Wall(new Point(0,0), new Point(1,0)));
        wallls.add(new Wall(new Point(1,2), new Point(2,2)));
        wallls.add(new Wall(new Point(2,1), new Point(2,2)));
        wallls.add(new Wall(new Point(4,1), new Point(4,2)));
        for(Wall wall : wallls)
        {
            assertTrue(walls.contains(wall));
        }

    }
}