package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class CategoryHierarchyViewModel {
    private long id;
    private String name;
    private List<CategoryHierarchyViewModel> subcategories;

    public CategoryHierarchyViewModel(long id, String name, List<CategoryHierarchyViewModel> subcategories) {

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

    public List<CategoryHierarchyViewModel> getSubcategories() {
        return subcategories;
    }


}


