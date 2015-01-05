package com.gmail.maxdiland.report.builder;

import com.gmail.maxdiland.entity.Category;
import com.gmail.maxdiland.entity.FinancialOperation;
import com.gmail.maxdiland.report.CategoryReport;

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
