package by.training.lihodievski.xmlparsing.parser;

import by.training.lihodievski.xmlparsing.bean.Flower;
import by.training.lihodievski.xmlparsing.exception.ParserException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.InputStream;
import java.util.Set;

public class DOMFlowerParserTest {

    @Test(dataProvider = "buildSetFlowersData", dataProviderClass = DOMFlowerParserTestData.class)
    public void testBuildSetFlowers_InputStream_flowers(Set<Flower> expected, InputStream stream) throws  ParserException {

        AbstractFlowerParser parser = new DOMFlowerParser ();
        parser.buildSetFlowers (stream);
        Set<Flower> actual = parser.flowers;

        Assert.assertEquals(actual, expected);
    }
}
