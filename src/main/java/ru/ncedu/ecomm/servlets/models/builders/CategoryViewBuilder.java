package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.CategoryViewModel;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;

import java.util.Set;

public class CategoryViewBuilder {
    private long categoryId;
    private long parentId;
    private String  categoryName;
    private Set<CategoryViewModel> childCategory;
    private Set<ProductViewModel> productInCategory;

    public CategoryViewBuilder() {
    }

    public CategoryViewBuilder setCategoryId(long categoryId) {
        this.categoryId = categoryId;

        return this;
    }

    public CategoryViewBuilder setParentId(long parentId) {
        this.parentId = parentId;

        return this;
    }

    public CategoryViewBuilder setCategoryName(String  categoryName) {
        this.categoryName = categoryName;

        return this;
    }

    public CategoryViewBuilder setChildCategory(Set<CategoryViewModel> childCategory) {
        this.childCategory = childCategory;

        return this;
    }

    public CategoryViewBuilder setProductInCategory(Set<ProductViewModel> productInCategory) {
        this.productInCategory = productInCategory;

        return this;
    }

    public CategoryViewModel build() {
        return new CategoryViewModel(
                categoryId,
                parentId,
                categoryName,
                childCategory,
                productInCategory
        );
    }
}
