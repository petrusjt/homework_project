package game.controller.mapxmlreader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Data class representing a {@code Wall} object stored in map's xml file.
 * This class cannot be used interchangeably with {@code game.utilities.Wall}.
 * */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wall {
    Start start;
    End end;
}
