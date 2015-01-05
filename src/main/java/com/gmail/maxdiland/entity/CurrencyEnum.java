package com.gmail.maxdiland.entity;

public enum CurrencyEnum {
    UAH("грн"),
    USD("USD");

    protected String textValue;

    private CurrencyEnum(String textValue) {
        this.textValue = textValue;
    }

    public String getTextValue() {
        return textValue;
    }

    public static CurrencyEnum getCurrencyByTextValue(String textValue) {
        for (CurrencyEnum currency : values()) {
            if ( currency.getTextValue().equalsIgnoreCase(textValue) ) {
                return currency;
            }
        }
        throw new RuntimeException("Unknown currency: " + textValue + ".");
    }
}
