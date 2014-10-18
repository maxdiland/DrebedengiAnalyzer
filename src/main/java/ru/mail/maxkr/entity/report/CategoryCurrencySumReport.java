package ru.mail.maxkr.entity.report;

import ru.mail.maxkr.entity.*;
import ru.mail.maxkr.entity.Currency;
import ru.mail.maxkr.entity.SumWithCurrency;

import java.util.*;

public class CategoryCurrencySumReport implements Iterable<List<SumWithCurrency>> {
    private EnumMap<ru.mail.maxkr.entity.Currency, List<SumWithCurrency>> sums;

    public void addSumWithCurrency(SumWithCurrency sumWithCurrency) {
        checkMap();
        List<SumWithCurrency> thisCurrencySums = sums.get( sumWithCurrency.getCurrency() );

        if ( thisCurrencySums == null ) {
            thisCurrencySums = new ArrayList<SumWithCurrency>();
            sums.put(sumWithCurrency.getCurrency(), thisCurrencySums);
        }

        thisCurrencySums.add(sumWithCurrency);
    }

    private void checkMap() {
        if (sums == null) {
            sums = new EnumMap<Currency, List<SumWithCurrency>>(Currency.class);
        }
    }

    @Override
    public Iterator<List<SumWithCurrency>> iterator() {
        return new Iterator<List<SumWithCurrency>>() {
            private Iterator<Currency> iterator = sums.keySet().iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public List<SumWithCurrency> next() {
                return sums.get( iterator.next() );
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}


