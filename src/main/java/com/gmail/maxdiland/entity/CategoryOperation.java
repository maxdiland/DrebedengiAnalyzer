package com.gmail.maxdiland.entity;

import java.util.Date;

/**
 * author: Maxim Diland
 */
public class CategoryOperation {

    private final SumWithCurrency sumWithCurrency;
    private final MoneyPlace moneyPlace;
    private final Date operationDate;
    private final String description;

    public CategoryOperation(SumWithCurrency sumWithCurrency, MoneyPlace moneyPlace, Date operationDate, String description) {
        this.sumWithCurrency = sumWithCurrency;
        this.moneyPlace = moneyPlace;
        this.operationDate = operationDate;
        this.description = description;
    }

    public SumWithCurrency getSumWithCurrency() {
        return sumWithCurrency;
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
