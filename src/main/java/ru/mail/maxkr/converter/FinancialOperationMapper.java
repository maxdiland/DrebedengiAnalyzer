package ru.mail.maxkr.converter;

import ru.mail.maxkr.entity.FinancialOperation;
import ru.mail.maxkr.entity.Entry;
import ru.mail.maxkr.entity.EntryManager;
import ru.mail.maxkr.entity.MoneyPlace;
import ru.mail.maxkr.entity.Currency;
import ru.mail.maxkr.entity.SumWithCurrency;
import ru.mail.maxkr.util.dateparser.DateParser;

import java.util.Date;

/**
 * Author: Maksim Diland
 * Date: 20.12.13
 */
public class FinancialOperationMapper {
    private DateParser dateParser;
    private EntryManager entryManager;

    public FinancialOperation convert(String[] strings) {
        checkParameter(strings);

        float sum = Float.parseFloat(strings[0]);
        Currency currency = Currency.getCurrencyByTextValue( strings[1] );
        String categoryOrMoneyPlaceName = strings[2];
        Entry categoryOrMoneyPlace = entryManager.getCategoryByName(categoryOrMoneyPlaceName);
        if (categoryOrMoneyPlace == null) {
            categoryOrMoneyPlace = entryManager.getMoneyPlaceByName(categoryOrMoneyPlaceName);
        }
        MoneyPlace moneyPlace = entryManager.getMoneyPlaceByName(strings[3]);
        Date date = dateParser.parse(strings[4]);
        String description = strings[5];

        SumWithCurrency sumWithCurrency = new SumWithCurrency(currency, sum);

        return new FinancialOperation(sumWithCurrency, categoryOrMoneyPlace, moneyPlace, date, description);
    }

    private void checkParameter(String[] strings) {
        if (strings == null || strings.length != 6) {
            throw new IllegalArgumentException("String array mustn't be null and must have size equal 6");
        }
    }

    public DateParser getDateParser() {
        return dateParser;
    }

    public void setDateParser(DateParser dateParser) {
        this.dateParser = dateParser;
    }

    public EntryManager getEntryManager() {
        return entryManager;
    }

    public void setEntryManager(EntryManager entryManager) {
        this.entryManager = entryManager;
    }
}
