package ru.mail.maxkr.csvreader;

import com.google.common.annotations.VisibleForTesting;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CSVReader implements Iterator<String[]>, AutoCloseable {
    private String csvPath;
    private String csvDelimiter = ","; //Default value
    private Scanner scanner;

    public CSVReader() {}

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

    private void checkState() {
        if (scanner == null) {
            throw new IllegalStateException("CSV source file isn't defined");
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
        checkState();
        return scanner.hasNextLine();
    }

    @Override
    public String[] next() {
        checkState();

        if (!scanner.hasNext()) {
            throw new NoSuchElementException();
        }

        return splitString(scanner.nextLine());
    }

    @VisibleForTesting
    /*default*/ String[] splitString(String string) {
        List<String> delimitedStrings = new ArrayList<String>();
        int indexFrom = 0;
        int indexTo;

        while (true) {
            indexTo = string.indexOf(csvDelimiter, indexFrom);
            if (indexTo == -1) {
                if (indexFrom < string.length() - 1) {
                    delimitedStrings.add(string.substring(indexFrom));
                }
                break;
            }
            delimitedStrings.add(string.substring(indexFrom, indexTo));
            indexFrom = indexTo + csvDelimiter.length();
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
