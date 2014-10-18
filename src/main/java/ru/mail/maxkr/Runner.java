package ru.mail.maxkr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mail.maxkr.analyzer.filter.Filter;
import ru.mail.maxkr.analyzer.filter.FinancialOperationFilterBuilder;
import ru.mail.maxkr.converter.StringArrayToFinancialOperationConverter;
import ru.mail.maxkr.csvreader.CSVReader;
import ru.mail.maxkr.entity.FinancialOperation;
import ru.mail.maxkr.entity.Category;
import ru.mail.maxkr.entity.report.FullReport;

import java.util.*;

/**
 * author: Maxim Diland
 */
public class Runner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/app-config.xml");
        Set<Category> categories = (Set<Category>) applicationContext.getBean("categories");
//        CSVReader csvReader = (CSVReader) applicationContext.getBean("csvReader");
        StringArrayToFinancialOperationConverter converter =
                (StringArrayToFinancialOperationConverter) applicationContext.getBean("financialOperationCreator");

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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 4);
        Date from = calendar.getTime();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        Date till = calendar.getTime();
        filterBuilder.addFiltrationByDateRange(from, till);
        Filter filter = filterBuilder.build();

        for (FinancialOperation operation : operations) {
            if (filter.isOperationMatches( operation )) {
                fullReport.addFinancialOperation(operation);
            }
        }

        System.out.println(fullReport);
    }
}
