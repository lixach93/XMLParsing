package by.training.lihodievski.xmlparsing.parser;

import by.training.lihodievski.xmlparsing.bean.Flower;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import java.io.InputStream;
import java.util.Set;

public class StAXFlowerParserTest {

    @Test(dataProvider = "buildSetFlowersData", dataProviderClass = StAXFlowerParserTestData.class)
    public void testBuildSetFlowers_InputStream_flowers(Set<Flower> expected, InputStream stream) {

        AbstractFlowerParser parser = new StAXFlowerParser ();
        parser.buildSetFlowers (stream);
        Set<Flower> actual = parser.flowers;

        Assert.assertEquals(actual, expected);
    }
}
