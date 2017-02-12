package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.CategoryHierarchyViewModel;

import java.util.List;

public class CategoryHierarchyViewBuilder {
    private long id;
    private String name;
    private List<CategoryHierarchyViewModel> subcategories;

    public CategoryHierarchyViewBuilder() {
    }

    public CategoryHierarchyViewBuilder setId(long categoryId) {
        this.id = categoryId;

        return this;
    }

    public CategoryHierarchyViewBuilder setName(String categoryName) {
        this.name = categoryName;

        return this;
    }

    public CategoryHierarchyViewBuilder setSubcategories(List<CategoryHierarchyViewModel> subcategories) {
        this.subcategories = subcategories;

        return this;
    }

    public CategoryHierarchyViewModel build() {
        return new CategoryHierarchyViewModel(
                id,
                name,
                subcategories
        );
    }
}
