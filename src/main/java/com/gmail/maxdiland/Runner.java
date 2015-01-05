package com.gmail.maxdiland;

import com.gmail.maxdiland.consolescanner.SystemInExtendedScanner;
import com.gmail.maxdiland.entity.FinancialOperation;
import com.gmail.maxdiland.report.FullReport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.gmail.maxdiland.analyzer.filter.FinancialOperationFilterBuilder;
import com.gmail.maxdiland.mapper.FinancialOperationMapper;
import com.gmail.maxdiland.csvreader.CSVReader;

import java.io.File;
import java.util.*;

/**
 * author: Maxim Diland
 */
public class Runner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/app-config.xml");

        // CSV-file location request
        System.out.println("Please, enter full path to CSV-file to be analyzed");
        SystemInExtendedScanner scanner = (SystemInExtendedScanner) applicationContext.getBean("userInputScanner");

        File csvFileToAnalyze = scanner.getNextExistingFile();
        CSVReader csvReader = (CSVReader) applicationContext.getBean("csvReader");
        csvReader.setCsvFile(csvFileToAnalyze);


        FinancialOperationMapper mapper =
                (FinancialOperationMapper) applicationContext.getBean("financialOperationMapper");

        List<FinancialOperation> operations = new ArrayList<FinancialOperation>();

        try (CSVReader reader = csvReader) {
            while (reader.hasNext()) {
                String[] readData = reader.next();
                FinancialOperation financialOperation = mapper.map(readData);
                operations.add(financialOperation);
            }
        }

        FullReport fullReport = new FullReport();
        FinancialOperationFilterBuilder filterBuilder = new FinancialOperationFilterBuilder();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DATE, 1);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 4);
//        Date from = calendar.getTime();
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
//        Date till = calendar.getTime();
//        filterBuilder.addFiltrationByDateRange(from, till);
//        Filter filter = filterBuilder.build();

        for (FinancialOperation operation : operations) {
//            if (filter.isFinancialOperationMatches(operation)) {
                fullReport.addFinancialOperation(operation);
//            }
        }

        System.out.println(fullReport);
    }
}
