package by.training.lihodievski.xmlparsing.parser;

import by.training.lihodievski.xmlparsing.bean.Flower;
import by.training.lihodievski.xmlparsing.exception.ParserException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Set;

public class SAXFLowerParserTest {

    @Test(dataProvider = "buildSetFlowersData", dataProviderClass = SAXFLowerParserTestData.class)
    public void testBuildSetFlowers_InputStream_flowers(Set<Flower> expected, InputStream stream) throws ParserException {

        AbstractFlowerParser parser = new SAXFlowerParser ();
        parser.buildSetFlowers (stream);
        Set<Flower> actual = parser.flowers;

        Assert.assertEquals(actual, expected);
    }
}
