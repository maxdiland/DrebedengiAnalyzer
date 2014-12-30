package com.gmail.maxdiland.entity.report;

import com.gmail.maxdiland.entity.FinancialOperation;
import com.gmail.maxdiland.entity.Category;
import com.gmail.maxdiland.entity.CategoryOperation;
import com.gmail.maxdiland.entity.Entry;
import com.gmail.maxdiland.entity.MoneyPlace;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.gmail.maxdiland.dictionary.Strings.*;

/**
* author: Maxim Diland
*/
public class FullReport {
    private LinkedHashMap<Category, CategoryReport> expenseCategoryReports;
    private LinkedHashMap<Category, CategoryReport> profitableCategoryReports;
    private Map<MoneyPlace, MoneyPlaceReport> moneyPlaces;

    public void addFinancialOperation(FinancialOperation financialOperation) {
        createMapIfNeeded();

        Entry categoryOrMoneyPlace = financialOperation.getCategoryOrMoneyPlace();
//        Category category = null;
        if (categoryOrMoneyPlace instanceof Category) {
            handleFinancialOperationForCategory(financialOperation);
        } else {

        }
    }

    private void handleFinancialOperationForCategory(FinancialOperation financialOperation) {
        Category category = (Category) financialOperation.getCategoryOrMoneyPlace();
        Map<Category, CategoryReport> reports =
                category.isProfitable() ? profitableCategoryReports : expenseCategoryReports;

        CategoryOperation categoryOperation = new CategoryOperation(
                financialOperation.getSumWithCurrency(),
                financialOperation.getMoneyPlace(),
                financialOperation.getOperationDate(),
                financialOperation.getDescription()
        );

        CategoryReport rootCategoryReport;
        Category rootCategory = category;
        if (category.isRoot()) {
            rootCategoryReport = reports.get(rootCategory);
        } else {
            rootCategory = category.getParentCategory();
            rootCategoryReport = reports.get(rootCategory);
        }

        if (rootCategoryReport == null) {
            rootCategoryReport = new CategoryReport(rootCategory);
            reports.put(rootCategory, rootCategoryReport);
        }

        if (category.isRoot()) {
            rootCategoryReport.addCategoryOperation(categoryOperation);
        } else {
//            CategoryReport rootCategoryReport = reports.get( category.getParentCategory() );
//            if (rootCategoryReport == null) {
//                rootCategoryReport = new CategoryReport(category.getParentCategory());
//                reports.put(category.getParentCategory(), rootCategoryReport);
//            }
            CategoryReport subcategoryReport = rootCategoryReport.getSubcategoryReports().get(category);
            if (subcategoryReport == null) {
                subcategoryReport = new CategoryReport(category);
                rootCategoryReport.getSubcategoryReports().put(category, subcategoryReport);
            }
            subcategoryReport.addCategoryOperation(categoryOperation);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TABLE_HORIZONTAL_LINE);
        stringBuilder.append(OPERATIONS_REPORT);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(TABLE_HORIZONTAL_LINE);
        // Расходы
        stringBuilder.append(EXPENSES).append(":");
        stringBuilder.append(NEW_LINE);
        for (CategoryReport categoryReport : expenseCategoryReports.values()) {
            stringBuilder.append(categoryReport).append("\n");
        }
        stringBuilder.append(TABLE_BOLD_HORIZONTAL_LINE);
        stringBuilder.append(TABLE_BOLD_HORIZONTAL_LINE);
        // Доходы
        stringBuilder.append(PROFITS).append(":");
        stringBuilder.append(NEW_LINE);
        for (CategoryReport categoryReport : profitableCategoryReports.values()) {
            stringBuilder.append(categoryReport).append("\n");
        }
        return stringBuilder.toString();
    }

    private void createMapIfNeeded() {
        if (profitableCategoryReports == null) {
            profitableCategoryReports = new LinkedHashMap<Category, CategoryReport>();
        }

        if (expenseCategoryReports == null) {
            expenseCategoryReports = new LinkedHashMap<Category, CategoryReport>();
        }
    }
}
