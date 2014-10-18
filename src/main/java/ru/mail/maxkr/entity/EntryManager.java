package ru.mail.maxkr.entity;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Set;

public class EntryManager {
    private Set<Category> categories;
    private Set<MoneyPlace> moneyPlaces;

    /**
     *  Searches category instance by name
     *
     * @param categoryName - name of category to be found
     * @return found Category or null if not found
     */
    public Category getCategoryByName(String categoryName) {
        Category foundCategory = getEntryByName(categoryName, categories);
        if (foundCategory != null) {
            return foundCategory;
        }

        for (Category category : categories) {
            foundCategory = getEntryByName(categoryName, category.getSubcategories());
            if (foundCategory != null) {
                return foundCategory;
            }
        }
        return null;
    }

    /**
     * Searches Entry in the Collection of Entries
     *
     * @param entityName - name of entry to be found
     * @param entries - Collection of Entries where Entry should be found
     * @param <T> any subclass of Entry
     * @return found Entry or null if not found
     */
    public <T extends Entry> T getEntryByName(String entityName, Collection<T> entries) {
        if (CollectionUtils.isNotEmpty(entries)) {
            for (T entry : entries) {
                if (entry.getName() != null && entry.getName().equalsIgnoreCase(entityName)) {
                    return entry;
                }
            }
        }
        return null;
    }

    /**
     * Searches MoneyPlace instance in a Collection of MoneyPlaces. Creates new MoneyPlace if not found,
     * adds to the Collection.
     *
     * @param moneyPlaceName - name of money place to search in collection with defined money places.
     * @return found MoneyPlace instance or new one.
     */
    public MoneyPlace getMoneyPlaceByName(String moneyPlaceName) {
        for (MoneyPlace moneyPlace : moneyPlaces) {
            if (moneyPlace.getName().equalsIgnoreCase(moneyPlaceName)) {
                return moneyPlace;
            }
        }
        MoneyPlace newMoneyPlace = new MoneyPlace(moneyPlaceName);
        moneyPlaces.add(newMoneyPlace);
        return newMoneyPlace;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<MoneyPlace> getMoneyPlaces() {
        return moneyPlaces;
    }

    public void setMoneyPlaces(Set<MoneyPlace> moneyPlaces) {
        this.moneyPlaces = moneyPlaces;
    }
}
