package com.gmail.maxdiland.entity;

public class SumWithCurrency {
    private final CurrencyEnum currency;
    private final float value;

    public SumWithCurrency(CurrencyEnum currency, float value) {
        this.currency = currency;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%.2f%s", value, currency.getTextValue());
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }
}
