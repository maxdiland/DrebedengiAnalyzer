package com.gmail.maxdiland.mapper;

import com.gmail.maxdiland.entity.FinancialOperation;
import com.gmail.maxdiland.entity.SumWithCurrency;
import com.gmail.maxdiland.entity.Entry;
import com.gmail.maxdiland.entity.EntryManager;
import com.gmail.maxdiland.entity.MoneyPlace;
import com.gmail.maxdiland.entity.CurrencyEnum;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Author: Maksim Diland
 * Date: 20.12.13
 */
public class FinancialOperationMapper {
    private DateFormat dateFormat;
    private EntryManager entryManager;

    public FinancialOperation map(String[] strings) {
        checkParameter(strings);

        float sum = Float.parseFloat(strings[0]);
        CurrencyEnum currency = CurrencyEnum.getCurrencyByTextValue(strings[1]);
        String categoryOrMoneyPlaceName = strings[2];
        Entry categoryOrMoneyPlace = entryManager.getCategoryByName(categoryOrMoneyPlaceName);
        if (categoryOrMoneyPlace == null) {
            categoryOrMoneyPlace = entryManager.getMoneyPlaceByName(categoryOrMoneyPlaceName);
        }
        MoneyPlace moneyPlace = entryManager.getMoneyPlaceByName(strings[3]);
        Date date = null;
        try {
            date = dateFormat.parse(strings[4]);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
        String description = strings[5];

        SumWithCurrency sumWithCurrency = new SumWithCurrency(currency, sum);

        return new FinancialOperation(sumWithCurrency, categoryOrMoneyPlace, moneyPlace, date, description);
    }

    private void checkParameter(String[] strings) {
        if (strings == null || strings.length != 6) {
            throw new IllegalArgumentException("String array mustn't be null and must have size equal 6");
        }
    }

    public EntryManager getEntryManager() {
        return entryManager;
    }

    public void setEntryManager(EntryManager entryManager) {
        this.entryManager = entryManager;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }
}
