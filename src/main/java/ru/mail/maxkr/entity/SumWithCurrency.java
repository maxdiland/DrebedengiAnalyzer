package ru.mail.maxkr.entity;

public class SumWithCurrency {
    private final Currency currency;
    private final float value;

    public SumWithCurrency(Currency currency, float value) {
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

    public Currency getCurrency() {
        return currency;
    }
}
