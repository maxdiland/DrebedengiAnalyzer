package com.gmail.maxdiland.csvreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVReader implements Iterator<String[]>, AutoCloseable {
    private File csvFile;
    private String csvDelimiter = ","; //Default value
    private Scanner scanner;

    public CSVReader() {}

    public CSVReader(File csvFile, String csvDelimiter) {
        this.csvDelimiter = csvDelimiter;
        instantiateScanner(csvFile);
    }

    public CSVReader(File csvFile) {
        this.csvFile = csvFile;
        instantiateScanner(csvFile);
    }

    public CSVReader(String csvPath) {
        this.csvFile = new File(csvPath);
        instantiateScanner(csvFile);
    }

    public CSVReader(String csvPath, String delimiter) {
        this(new File(csvPath), delimiter);
    }

    public File getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(File csvFile) {
        this.csvFile = csvFile;
        try {
            scanner = new Scanner(csvFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void instantiateScanner(File csvFile) {
        try {
            scanner = new Scanner(csvFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
