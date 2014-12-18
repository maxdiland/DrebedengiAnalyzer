package ru.mail.maxkr.csvreader;

import com.google.common.annotations.VisibleForTesting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVReader implements Iterator<String[]>, AutoCloseable {
    private String csvPath;
    private String csvDelimiter = ","; //Default value
    private Scanner scanner;

    public CSVReader(String csvPath, String delimiter) {
        this(csvPath);
        this.csvDelimiter = delimiter;
    }

    public CSVReader(String csvPath) {
        this.csvPath = csvPath;
        try {
            scanner = new Scanner(new File(csvPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
        scanner = new Scanner(csvPath);
    }

    public String getCsvDelimiter() {
        return csvDelimiter;
    }

    public void setCsvDelimiter(String csvDelimiter) {
        this.csvDelimiter = csvDelimiter;
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    @Override
    public String[] next() {
        if (!scanner.hasNext()) {
            throw new NoSuchElementException();
        }

        return splitString(scanner.nextLine(), csvDelimiter);
    }

    public static String[] splitString(String string, String delimiter) {
        List<String> delimitedStrings = new ArrayList<String>();
        int indexFrom = 0;
        int indexTo;

        while (true) {
            indexTo = string.indexOf(delimiter, indexFrom);
            if (indexTo == -1) {
                if (indexFrom < string.length() - 1) {
                    delimitedStrings.add(string.substring(indexFrom));
                }
                break;
            }
            delimitedStrings.add(string.substring(indexFrom, indexTo));
            indexFrom = indexTo + delimiter.length();
        }

        String[] result = new String[delimitedStrings.size()];
        return delimitedStrings.toArray(result);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
