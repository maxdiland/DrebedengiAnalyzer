package ru.mail.maxkr.analyzer.filter.impl;

import ru.mail.maxkr.analyzer.filter.Filter;
import ru.mail.maxkr.entity.FinancialOperation;

import java.util.Date;

public class DateRangeFilter implements Filter {
    private final Date from;
    private final Date till;

    public DateRangeFilter(Date from, Date till) {
        this.from = from;
        this.till = till;
    }

    @Override
    public boolean isOperationMatches(FinancialOperation operation) {
        long operationTime = operation.getOperationDate().getTime();
        return (operationTime >= from.getTime() && operationTime < till.getTime());
    }

    public Date getFrom() {
        return from;
    }

    public Date getTill() {
        return till;
    }
}
