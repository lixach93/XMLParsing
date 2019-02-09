package by.training.lihodievski.xmlparsing.validation;

import by.training.lihodievski.xmlparsing.bean.Flower;
import by.training.lihodievski.xmlparsing.parser.AbstractFlowerParser;
import by.training.lihodievski.xmlparsing.parser.DOMFlowerParser;
import by.training.lihodievski.xmlparsing.parser.DOMFlowerParserTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.util.Set;

public class ValidatorXMLTest {

    @Test(dataProvider = "validateData", dataProviderClass = ValidatorXMLTestData.class)
    public void testValidate_InputStream_boolean(InputStream stream)  {

        boolean result = ValidatorXML.validate (stream);

        Assert.assertTrue (result);
    }
}
