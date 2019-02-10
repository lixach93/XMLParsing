package by.training.lihodievski.xmlparsing.factory;

import by.training.lihodievski.xmlparsing.exception.ParserException;
import by.training.lihodievski.xmlparsing.parser.DOMFlowerParser;
import by.training.lihodievski.xmlparsing.parser.SAXFlowerParser;
import by.training.lihodievski.xmlparsing.parser.StAXFlowerParser;
import org.testng.annotations.DataProvider;

public class ParserFactoryTestData {

    @DataProvider(name = "createFlowerParserData")
    public static Object[] createFlowerParserData() throws ParserException {

        String dom = "dom";
        String sax = "sax";
        String stax = "stax";
        ParserFactory factory = ParserFactory.getInstance ();

        return  new Object[][]{
                {
                        dom, factory, DOMFlowerParser.class
                },
                {
                        sax, factory, SAXFlowerParser.class
                },
                {
                        stax, factory, StAXFlowerParser.class
                },
        };
    }
}
