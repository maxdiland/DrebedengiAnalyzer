package com.gmail.maxdiland.analyzer.filter;

import com.gmail.maxdiland.entity.FinancialOperation;

public interface Filter {
    public boolean isFinancialOperationMatches(FinancialOperation operation);
}
