package by.training.lihodievski.xmlparsing.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class DateConvertTest {

    @Test(dataProvider = "convertDateData", dataProviderClass = DateConvertTestData.class)
    public void testConvertDate_String_LocalDate(String stringDate, LocalDate expected)  {

        LocalDate actual = DateConvert.convertDate (stringDate);

        Assert.assertEquals (actual, expected);
    }
}
