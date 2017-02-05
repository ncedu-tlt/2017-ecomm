package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.CategoriesViewModel;

import java.util.List;

public class CategoriesViewBuilder {
    private long id;
    private String name;
    private List<CategoriesViewModel> subcategories;

    public CategoriesViewBuilder() {
    }

    public CategoriesViewBuilder setId(long categoryId) {
        this.id = categoryId;

        return this;
    }

    public CategoriesViewBuilder setName(String categoryName) {
        this.name = categoryName;

        return this;
    }

    public CategoriesViewBuilder setSubcategories(List<CategoriesViewModel> subcategories) {
        this.subcategories = subcategories;

        return this;
    }

    public CategoriesViewModel build() {
        return new CategoriesViewModel(
                id,
                name,
                subcategories
        );
    }
}
