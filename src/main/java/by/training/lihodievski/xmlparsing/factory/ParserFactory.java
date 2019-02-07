package by.training.lihodievski.xmlparsing.factory;

import by.training.lihodievski.xmlparsing.parser.AbstractFlowerParser;
import by.training.lihodievski.xmlparsing.parser.DOMFlowerParser;
import by.training.lihodievski.xmlparsing.parser.SAXFlowerParser;
import by.training.lihodievski.xmlparsing.parser.StAXFlowerParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class ParserFactory {

    private enum TypeParser {
        SAX, STAX, DOM
    }
    public AbstractFlowerParser createFlowerParser(String typeParser) throws ParserConfigurationException, SAXException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new DOMFlowerParser ();
            case STAX:
                return new StAXFlowerParser ();
            case SAX:
                return new SAXFlowerParser ();
            default:
                throw new EnumConstantNotPresentException (type.getDeclaringClass(), type.name());
        }
    }
}
