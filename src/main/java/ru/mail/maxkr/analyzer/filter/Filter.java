package ru.mail.maxkr.analyzer.filter;

import ru.mail.maxkr.entity.FinancialOperation;

public interface Filter {
    public boolean isOperationMatches(FinancialOperation operation);
}
