package com.gmail.maxdiland.csvreader;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Author: Maksim Diland
 * Date: 30.12.13
 */
public class CSVReaderTest {
    String csvDelimiter = ";";

    @Test
    public void splitStringTest01() {
        String stringToSplit = "-1900;грн;Мой кошелёк;Буфферная зона;2013-12-01 15:40:03;;";
        String[] strings = CSVReader.splitString(stringToSplit, csvDelimiter);

        Assert.assertEquals(6, strings.length);
    }

    @Test
    public void splitStringTest02() {
        String[] stringArr = {"Hello", "World", "!!!", "=)"};
        String concatenatedString = concatenateStrings(stringArr, csvDelimiter, false);

        String[] strings = CSVReader.splitString(concatenatedString, csvDelimiter);
        Assert.assertTrue(Arrays.equals(stringArr, strings));
    }

    private String concatenateStrings(String[] strings, String delimiter, boolean addDelimiterAtTheEnd) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string).append(delimiter);
        }

        if (!addDelimiterAtTheEnd) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

}
