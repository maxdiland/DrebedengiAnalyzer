package ru.mail.maxkr.entity;

/**
 * Author: Maksim Diland
 * Date: 04.01.14
 */
public class Entry {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Class<?> thatObjectClass = o.getClass();

        if (thatObjectClass == String.class && isEqualByName( (String) o ) ) {
            return true;
        }

        if (getClass() == thatObjectClass && name.equals( ((Entry) o).name) ) {
            return true;
        }

        return false;
    }

    public boolean isEqualByName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
