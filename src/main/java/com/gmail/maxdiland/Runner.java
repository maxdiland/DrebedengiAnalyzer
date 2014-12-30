package com.gmail.maxdiland;

import com.gmail.maxdiland.entity.FinancialOperation;
import com.gmail.maxdiland.entity.report.FullReport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.gmail.maxdiland.analyzer.filter.FinancialOperationFilterBuilder;
import com.gmail.maxdiland.mapper.FinancialOperationMapper;
import com.gmail.maxdiland.csvreader.CSVReader;
import com.gmail.maxdiland.entity.Category;

import java.util.*;

/**
 * author: Maxim Diland
 */
public class Runner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/app-config.xml");
        Set<Category> categories = (Set<Category>) applicationContext.getBean("categories");
//        CSVReader csvReader = (CSVReader) applicationContext.getBean("csvReader");
        FinancialOperationMapper converter =
                (FinancialOperationMapper) applicationContext.getBean("financialOperationCreator");

        List<FinancialOperation> operations = new ArrayList<FinancialOperation>();

        try ( CSVReader csvReader = (CSVReader) applicationContext.getBean("csvReader") ) {
            while (csvReader.hasNext()) {
                String[] readData = csvReader.next();
                FinancialOperation financialOperation = converter.convert(readData);
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
