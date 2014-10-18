package ru.mail.maxkr.util.dateparser.impl;

import ru.mail.maxkr.util.dateparser.DateParser;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: Maksim Diland
 * Date: 21.12.13
 */
public class DefaultDateParser implements DateParser {

    @Override
    public Date parse(String source) {
        if (source.length() != 19) {
            throw new IllegalArgumentException("Can't parse string [" + source +
                "] to Date. Incorrect format. Use format like 2013-12-01 15:40:22");
        }

        int year = Integer.parseInt( source.substring(0, 4) );
        int month = Integer.parseInt( source.substring(5, 7) );
        int day = Integer.parseInt( source.substring(8, 10) );
        int hour = Integer.parseInt( source.substring(11, 13) );
        int minute = Integer.parseInt( source.substring(14, 16) );
        int seconds = Integer.parseInt( source.substring(17, 19) );


        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, seconds);


        return calendar.getTime();
    }
}
