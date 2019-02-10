package by.training.lihodievski.xmlparsing.factory;

import by.training.lihodievski.xmlparsing.exception.ParserException;
import by.training.lihodievski.xmlparsing.parser.AbstractFlowerParser;
import by.training.lihodievski.xmlparsing.parser.DOMFlowerParser;
import by.training.lihodievski.xmlparsing.parser.SAXFlowerParser;
import by.training.lihodievski.xmlparsing.parser.StAXFlowerParser;

public class ParserFactory {

    private static final ParserFactory INSTANCE =  new ParserFactory ();
    private enum TypeParser {
        SAX, STAX, DOM
    }

    private ParserFactory() {
    }

    public static ParserFactory getInstance() {
        return INSTANCE;
    }

    public AbstractFlowerParser createFlowerParser(String typeParser) throws ParserException {
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
