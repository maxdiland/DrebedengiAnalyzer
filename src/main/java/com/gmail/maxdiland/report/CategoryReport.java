package com.gmail.maxdiland.report;

import com.gmail.maxdiland.entity.Category;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import com.gmail.maxdiland.entity.CategoryOperation;
import com.gmail.maxdiland.entity.CurrencyEnum;
import static com.gmail.maxdiland.dictionary.Strings.*;

import java.util.*;

public class CategoryReport {
    private final Category categoryReference;
    private Map<Category, CategoryReport> subcategoryReports;
//    private CategoryReport parentCategoryReport;
    private List<CategoryOperation> categoryOperations;

    public CategoryReport(Category categoryReference) {
        this.categoryReference = categoryReference;
        categoryOperations = new ArrayList<CategoryOperation>();
        if (categoryReference.isRoot()) {
            subcategoryReports = new HashMap<Category, CategoryReport>();
        }
    }

    public void addCategoryOperation(CategoryOperation operation) {
        categoryOperations.add(operation);
    }

    public List<CategoryOperation> getCategoryOperations() {
        return (categoryOperations == null) ? Collections.<CategoryOperation>emptyList() : categoryOperations;
    }

    private String toStringCategoryOperation(CategoryOperation operation) {
        return String.format(
                CATEGORY_OPERATION_DESCRIPTION, operation.getSumWithCurrency().getValue(),
                operation.getSumWithCurrency().getCurrency().getTextValue(), operation.getMoneyPlace(),
                operation.getOperationDate(), operation.getDescription()
        );
    }

    @Override
    public String toString() {
        Map<CurrencyEnum, Double> withSubcategoriesSums = getCategorySumsWithSubcategories();
        removeEmptySums( withSubcategoriesSums );
        String stringSums = formSumsString( withSubcategoriesSums );

        StringBuilder sb = new StringBuilder();
        String profExp = categoryReference.isProfitable() ? PROFITS : EXPENSES;
        sb.append(String.format(SUMMARY_CHANGES_BY_CATEGORY, profExp, categoryReference.getName(), stringSums));
        sb.append(NEW_LINE);

        sb.append(formCategoryReportTable());
        sb.append(formSubcategoriesReportTables());
        return sb.toString();
    }

    private String formCategoryReportTable() {
        if (CollectionUtils.isEmpty(categoryOperations)) {
            return EMPTY_STRING;
        }
        StringBuilder sb = new StringBuilder();



        sb.append(CATEGORY_TABLE_HORIZONTAL_LINE);
        for (CategoryOperation operation : categoryOperations) {
            sb.append( toStringCategoryOperation(operation) );
            sb.append( NEW_LINE );
        }
        sb.append(CATEGORY_TABLE_HORIZONTAL_LINE);
        //TODO: add using of String.format() to provide pretty category result view
        Map<CurrencyEnum, Double> categorySums = getCategorySums(categoryOperations);
        removeEmptySums( categorySums );

        for (CurrencyEnum currency : CurrencyEnum.values()) {
            Double currencySum = categorySums.get(currency);
            if (currencySum != null) {
                sb.append(
                        String.format(
                                CATEGORY_RESULTS, currencySum,
                                currency.getTextValue(), categoryReference.getName()
                        )
                );
                sb.append( NEW_LINE );
            }
        }

        sb.append(CATEGORY_TABLE_HORIZONTAL_LINE);

        return sb.toString();
    }

    private String formSubcategoriesReportTables() {
        if (subcategoryReports == null || CollectionUtils.isEmpty(subcategoryReports.values())) {
            return EMPTY_STRING;
        }
        StringBuilder sb = new StringBuilder();
        for (CategoryReport subcategoryReport : subcategoryReports.values()) {
            sb.append(subcategoryReport.formCategoryReportTable());
        }
        return sb.toString();
    }

    private String formSumsString(Map<CurrencyEnum, Double> sums) {
        if (MapUtils.isEmpty(sums)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String sumsSeparator = ", ";
//        int sumsSize = sums.keySet().size();
        for (CurrencyEnum currency : sums.keySet()) {
//            sumsSize--;
            Double sum = sums.get(currency);
            if (!isSumEmpty(sum)) {
                if (sb.length() != 0) {
                    sb.append(sumsSeparator); //TODO: Coma should appear in case if next sum isn't empty.
                }
                sb.append(String.format(CURRENCY_SUM, sum, currency.getTextValue()));
            }
        }
        sb.append(". ");
        return sb.toString();
    }

    private Map<CurrencyEnum, Double> getCategorySumsWithSubcategories() {
        Map<CurrencyEnum, Double> resultSums = getCategorySums(categoryOperations);
        if (MapUtils.isEmpty(resultSums)) {
            resultSums = createCurrencySumsMap();
        }
        for (CategoryReport categoryReport : subcategoryReports.values()) {
            Map<CurrencyEnum, Double> subcategorySums = getCategorySums(categoryReport.getCategoryOperations());
            addSumsToResultSums(resultSums, subcategorySums);
        }
        return resultSums;
    }

    private void removeEmptySums(Map<CurrencyEnum, Double> resultSums) {
        for (CurrencyEnum currency : resultSums.keySet()) {
            if (resultSums.get(currency) == 0) {
                resultSums.remove(currency);
            }
        }
    }

    private void addSumsToResultSums(Map<CurrencyEnum, Double> resultSums, Map<CurrencyEnum, Double> subcategorySums) {
        for (CurrencyEnum currency : subcategorySums.keySet()) {
            resultSums.put(currency, resultSums.get(currency) + subcategorySums.get(currency));
        }
    }

    public Map<CurrencyEnum, Double> getCategorySums(Collection<CategoryOperation> categoryOperations) {
        Map<CurrencyEnum, Double> sums = createCurrencySumsMap();
        for (CategoryOperation operation : categoryOperations) {
            CurrencyEnum currency = operation.getSumWithCurrency().getCurrency();
            Double currencySum =
                    sums.get(currency) + operation.getSumWithCurrency().getValue();

            sums.put(currency, currencySum);
        }

        return sums;
    }

    private Map<CurrencyEnum, Double> createCurrencySumsMap() {
        Map<CurrencyEnum, Double> sums = new EnumMap<CurrencyEnum, Double>(CurrencyEnum.class);
        for (CurrencyEnum currency : CurrencyEnum.values()) {
            sums.put(currency, 0D);
        }
        return sums;
    }

    private boolean isSumEmpty(Double sum) {
        return sum == null || sum == 0;
    }

    public Category getCategoryReference() {
        return categoryReference;
    }

    public Map<Category, CategoryReport> getSubcategoryReports() {
        return subcategoryReports;
    }

    public void addSubcategoryReport(Category category, CategoryReport categoryReport) {
        if (!categoryReference.isRoot()) {
            throw new IllegalArgumentException("Subcategory cannot have subcategories.");
        }

        subcategoryReports.put(category, categoryReport);
    }
}
