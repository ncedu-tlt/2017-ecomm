package ru.ncedu.ecomm.servlets.viewmodel.viewmodelbuilders;

import ru.ncedu.ecomm.servlets.viewmodel.CategoryViewModel;
import ru.ncedu.ecomm.servlets.viewmodel.ProductItemsView;

import java.util.List;
import java.util.Set;

public class CategoryViewBuilder {
    private long categoryId;
    private long parentId;
    private String  categoryName;
    private List<CategoryViewModel> childCategory;
    private List<ProductItemsView> productInCategory;

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

    public CategoryViewBuilder setChildCategory(List<CategoryViewModel> childCategory) {
        this.childCategory = childCategory;

        return this;
    }

    public CategoryViewBuilder setProductInCategory(List<ProductItemsView> productInCategory) {
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
