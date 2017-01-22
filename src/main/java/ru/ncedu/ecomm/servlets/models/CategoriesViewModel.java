package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class CategoriesViewModel {
    private long id;
    private String name;
    private List<CategoriesViewModel> subcategories;

    public CategoriesViewModel(long id, String name, List<CategoriesViewModel> subcategories) {

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

    public List<CategoriesViewModel> getSubcategories() {
        return subcategories;
    }


}


