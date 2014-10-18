package ru.mail.maxkr.csvreader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Author: Maksim Diland
 * Date: 30.12.13
 */
public class CSVReaderTest {
    CSVReader reader = new CSVReader();
    String csvDelimiter = ";";

    {
        System.out.println("Something");

    }

    @Before
    public void prepare() {
        reader.setCsvDelimiter(csvDelimiter);
    }

    @Test
    public void splitStringTest01() {
        String stringToSplit = "-1900;грн;Мой кошелёк;Буфферная зона;2013-12-01 15:40:03;;";
        String[] strings = reader.splitString(stringToSplit);

        Assert.assertEquals(6, strings.length);
    }

    @Test
    public void splitStringTest02() {
        String[] stringArr = {"Hello", "World", "!!!", "=)"};
        String concatenatedString = concatenateStrings(stringArr, csvDelimiter, false);

        String[] strings = reader.splitString(concatenatedString);
        Assert.assertTrue(Arrays.equals(stringArr, strings));
    }

    private String concatenateStrings(String[] strings, String delimiter, boolean addDelimiterAtTheEnd) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i]);

            if (addDelimiterAtTheEnd || i < strings.length - 1) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }

}
