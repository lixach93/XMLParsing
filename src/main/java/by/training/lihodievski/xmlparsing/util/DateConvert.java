package by.training.lihodievski.xmlparsing.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConvert {

    public  static LocalDate convertDate(String text) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
        return LocalDate.parse(text, inputFormat);
    }
}
