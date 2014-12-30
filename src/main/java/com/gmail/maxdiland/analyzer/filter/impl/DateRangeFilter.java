package com.gmail.maxdiland.analyzer.filter.impl;

import com.gmail.maxdiland.entity.FinancialOperation;
import com.gmail.maxdiland.analyzer.filter.Filter;

import java.util.Date;

public class DateRangeFilter implements Filter {
    private final Date from;
    private final Date till;

    public DateRangeFilter(Date from, Date till) {
        this.from = from;
        this.till = till;
    }

    @Override
    public boolean isFinancialOperationMatches(FinancialOperation operation) {
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
