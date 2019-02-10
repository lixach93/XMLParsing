package by.training.lihodievski.xmlparsing.util;

import org.testng.annotations.DataProvider;

import java.time.LocalDate;

public class DateConvertTestData {

    @DataProvider(name = "convertDateData")
    public static Object[] convertDateData(){

        String dateOne = "1970-10-10";
        String dateTwo = "1876-11-17";
        String dateThree = "1993-05-30";
        String dateFour = "1970-01-27";

        LocalDate localDateOne = LocalDate.of (1970,10,10);
        LocalDate localDateTwo = LocalDate.of (1876,11,17);
        LocalDate localDateThree = LocalDate.of (1993,5,30);
        LocalDate localDateFour = LocalDate.of (1970,1,27);

        return  new Object[][]{
                {
                        dateOne, localDateOne
                },
                {
                        dateTwo, localDateTwo
                },
                {
                        dateThree, localDateThree
                },
                {
                        dateFour , localDateFour
                },
        };
    }
}
