package com.gmail.maxdiland.entity;

public enum Currency {
    UAH("грн"),
    USD("USD");

    protected String textValue;

    private Currency(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }

    public static Currency getCurrencyByTextValue(String textValue) {
        for (Currency currency : values()) {
            if ( currency.getTextValue().equalsIgnoreCase(textValue) ) {
                return currency;
            }
        }
        throw new RuntimeException("Unknown currency: " + textValue + ".");
    }
}
