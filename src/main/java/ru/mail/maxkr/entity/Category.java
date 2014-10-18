package ru.mail.maxkr.entity;

import java.util.List;

/**
 * Author: Maksim Diland
 * Date: 31.12.13
 */
public class Category extends Entry {
    private boolean isProfitable;
    private List<Category> subcategories;
    private Category parentCategory;

    public boolean isProfitable() {
        return isProfitable;
    }

    public void setProfitable(boolean profitable) {
        isProfitable = profitable;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public boolean isRoot() {
        return parentCategory == null;
    }

    @Override
    public boolean equals(Object o) {
        return (this == o)
                || (o instanceof Category && super.equals(o) && isProfitable == ((Category) o).isProfitable);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isProfitable ? 1 : 0);
        return result;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
