package game.controller.mapxmlreader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Data class representing the end of the {@code Wall} object stored in map's xml file.
 * */
@XmlAccessorType(XmlAccessType.FIELD)
@Data @AllArgsConstructor @NoArgsConstructor
public class End {
    private int x;
    private int y;
}
