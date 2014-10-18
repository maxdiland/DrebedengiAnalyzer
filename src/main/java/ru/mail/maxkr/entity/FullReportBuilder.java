package ru.mail.maxkr.entity;

import ru.mail.maxkr.entity.FinancialOperation;
import ru.mail.maxkr.entity.report.CategoryReport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullReportBuilder {
    private List<CategoryReport> profitableCategories;
    private List<CategoryReport> expenseCategories;

    private Map<Category, CategoryReport> categoryReportCache = new HashMap<Category, CategoryReport>();

    public void addFinancialOperation(FinancialOperation operation) {

    }
}
