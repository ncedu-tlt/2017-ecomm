package ru.ncedu.ecomm.servlets.models;

import ru.ncedu.ecomm.data.models.Category;

import java.util.List;

public class CategoriesViewModel {
    private long id;
    private String name;
    private List<Category> subcategories;

    public CategoriesViewModel(long id, String name, List<Category> subcategories) {

        this.id = id;
        this.name = name;
        this.subcategories = subcategories;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }


}


