package xmlhelper;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Helper class to work with JAXB.
 */
public class JAXBHelper {

    /**
     * Serializes an object to XML. The output document is written in UTF-8 encoding.
     *
     * @param o the object to serialize
     * @param os the {@code OutputStream} to write to
     * @throws JAXBException if any problem occurs during serialization
     */
    public static void toXML(final game.highscore.HighScores o, final OutputStream os) throws JAXBException {
        final JAXBContext context = JAXBContext.newInstance(o.getClass());
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(o, os);
    }

    /**
     * Deserializes an object from XML.
     *
     * @param clazz the class of the object
     * @param is the {@code InputStream} to read from
     * @param <T> the type of the resulting object
     * @return the resulting object
     * @throws JAXBException if any problem occurs during deserialization
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXML(final Class<T> clazz, final InputStream is) throws JAXBException {
        final JAXBContext context = JAXBContext.newInstance(clazz);
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(is);
    }

}