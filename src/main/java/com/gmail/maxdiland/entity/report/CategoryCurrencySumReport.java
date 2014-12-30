package com.gmail.maxdiland.entity.report;

import com.gmail.maxdiland.entity.SumWithCurrency;

import java.util.*;

public class CategoryCurrencySumReport implements Iterable<List<SumWithCurrency>> {
    private EnumMap<com.gmail.maxdiland.entity.Currency, List<SumWithCurrency>> sums;

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
            sums = new EnumMap<com.gmail.maxdiland.entity.Currency, List<SumWithCurrency>>(com.gmail.maxdiland.entity.Currency.class);
        }
    }

    @Override
    public Iterator<List<SumWithCurrency>> iterator() {
        return new Iterator<List<SumWithCurrency>>() {
            private Iterator<com.gmail.maxdiland.entity.Currency> iterator = sums.keySet().iterator();

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


