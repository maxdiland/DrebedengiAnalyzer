package ru.mail.maxkr.dictionary;

/**
 * author: Maxim Diland
 */
public class Strings {
    public static final String NEW_LINE = "\n";
    public static final String CATEGORY_TABLE_HORIZONTAL_LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    public static final String TABLE_HORIZONTAL_LINE = "------------------------------------------------------------------------------------------\n";
    public static final String TABLE_BOLD_HORIZONTAL_LINE = "##########################################################################################\n";
    public static final String OPERATIONS_REPORT = "Отчет по финансовым операциям:";
    public static final String EXPENSES = "Расходы";
    public static final String PROFITS = "Доходы";
    public static final String SUMMARY_CHANGES_BY_CATEGORY = TABLE_BOLD_HORIZONTAL_LINE +
                                                             "Общие %s по категории %s, с учетом подкатегорий: %s" +
                                                             NEW_LINE + TABLE_BOLD_HORIZONTAL_LINE;

    public static final String CATEGORY_REPORT_TABLE_HEADER = CATEGORY_TABLE_HORIZONTAL_LINE +
            "| %s по категории %s                                                       |" + NEW_LINE +
            CATEGORY_TABLE_HORIZONTAL_LINE;

    public static final String WITH_SUBCATEGORIES = "С учетом подкатегорий: ";
    public static final String CATEGORY_DESCRIPTION = "%s: ";
    public static final String CATEGORY_OPERATION_DESCRIPTION = "|%1$10.2f%2$s|%3$20s|%4$td.%4$tm.%4$tY| %5$-40s |";
    public static final String CATEGORY_RESULTS = "|%1$10.2f%2$s| Итог по категории %3$-54s |";
    public static final String CURRENCY_SUM = "%.2f%s";
    public static final String EMPTY_STRING = "";


}
