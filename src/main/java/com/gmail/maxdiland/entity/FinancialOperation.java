package com.gmail.maxdiland.entity;

import java.util.Date;

public class FinancialOperation {

    private final SumWithCurrency sumWithCurrency;
    private final Entry categoryOrMoneyPlace;
    private final MoneyPlace moneyPlace;
    private final Date operationDate;
    private final String description;

    public FinancialOperation(SumWithCurrency sumWithCurrency, Entry categoryOrMoneyPlace, MoneyPlace moneyPlace, Date operationDate, String description) {
        this.sumWithCurrency = sumWithCurrency;
        this.categoryOrMoneyPlace = categoryOrMoneyPlace;
        this.moneyPlace = moneyPlace;
        this.operationDate = operationDate;
        this.description = description;
    }

    public SumWithCurrency getSumWithCurrency() {
        return sumWithCurrency;
    }

    public Entry getCategoryOrMoneyPlace() {
        return categoryOrMoneyPlace;
    }

    public MoneyPlace getMoneyPlace() {
        return moneyPlace;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public String getDescription() {
        return description;
    }
}
