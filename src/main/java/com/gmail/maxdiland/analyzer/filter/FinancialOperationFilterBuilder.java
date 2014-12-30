package com.gmail.maxdiland.analyzer.filter;

import com.gmail.maxdiland.entity.FinancialOperation;
import com.google.common.annotations.VisibleForTesting;
import com.gmail.maxdiland.entity.Currency;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FinancialOperationFilterBuilder {
    private List<Filter> filters = new ArrayList<Filter>();
//    private float sum;
//    private String currency;
//    private String category;
//    private String source;
//    private Date operationDate;
//    private String description;

    public void addFiltrationBySum(final float sum, final Currency currency) {
        Filter filter = new Filter() {
            @Override
            public boolean isFinancialOperationMatches(FinancialOperation operation) {
                return operation.getSumWithCurrency().getValue() == sum &&
                        operation.getSumWithCurrency().getCurrency() == currency;

            }
        };
        filters.add(filter);
    }

    public void addFiltrationByDateRange(final Date from, final Date till) {
        filters.add(
            new Filter() {
                @Override
                public boolean isFinancialOperationMatches(FinancialOperation operation) {
                    long operationTime = operation.getOperationDate().getTime();
                    return (operationTime >= from.getTime() && operationTime < till.getTime());
                }
            }
        );
    }

    public void addFiltrationByDate(final Date date) {
        Date[] range = generateDateRangeForOneDay(date);
        addFiltrationByDateRange(range[0], range[1]);
    }


    @VisibleForTesting
    /* default */ Date[] generateDateRangeForOneDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date from = calendar.getTime();

        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        Date till = new Date(calendar.getTimeInMillis() + 1);

        return new Date[] {from, till};
    }

    public Filter build() {
        return new Filter() {
            private final List<Filter> filterList = new ArrayList<>(filters);

            @Override
            public boolean isFinancialOperationMatches(FinancialOperation operation) {
                for (Filter currentFilter : filterList) {
                    if (!currentFilter.isFinancialOperationMatches(operation)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    private void resetBuilder() {
        filters = new ArrayList<Filter>();
    }

}
