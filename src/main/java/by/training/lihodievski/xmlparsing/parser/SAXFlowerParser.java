package by.training.lihodievski.xmlparsing.parser;

import by.training.lihodievski.xmlparsing.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;
import java.io.InputStream;



public class SAXFlowerParser extends AbstractFlowerParser {

    private static final Logger LOGGER = LogManager.getLogger (SAXFlowerParser.class);
    private FlowerHandler flowerHandler;
    private XMLReader reader;

    public SAXFlowerParser() throws ParserException {
        super ();
        flowerHandler = new FlowerHandler ();
        try {
            reader = XMLReaderFactory.createXMLReader ();
            reader.setContentHandler (flowerHandler);
        } catch (SAXException e) {
            LOGGER.error ("Sax Constructor exception",e);
            throw new ParserException (e);
        }
    }

    public void buildSetFlowers(InputStream fileName) {
        try {
            reader.parse (new InputSource (fileName));
            flowers = flowerHandler.getFlowers ();
        } catch (SAXException | IOException e) {
            LOGGER.error ("SAX parsing error! ", e);
        }
    }
}
