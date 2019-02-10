package by.training.lihodievski.xmlparsing.factory;

import by.training.lihodievski.xmlparsing.exception.ParserException;
import by.training.lihodievski.xmlparsing.parser.AbstractFlowerParser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParserFactoryTest {

    @Test(dataProvider = "createFlowerParserData", dataProviderClass = ParserFactoryTestData.class)
    public void testCreateFlowerParser_String_Parser(String type, ParserFactory factory, Class expected) throws ParserException {

        AbstractFlowerParser actual = factory.createFlowerParser (type);

        Assert.assertEquals (actual.getClass (), expected);
    }
}
