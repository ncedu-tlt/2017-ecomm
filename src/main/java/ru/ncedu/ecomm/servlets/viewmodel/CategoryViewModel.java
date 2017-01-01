package ru.ncedu.ecomm.servlets.viewmodel;

import java.util.List;
import java.util.Set;

public class CategoryViewModel {
    private long categoryId;
    private long parentId;
    private String  categoryName;
    private List<CategoryViewModel> childCategory;
    private List<ProductItemsView> productInCategory;

    public CategoryViewModel() {
    }

    public CategoryViewModel(long categoryId,
                             long parentId,
                             String  categoryName,
                             List<CategoryViewModel> childCategory,
                             List<ProductItemsView> productInCategory) {

        this.categoryId = categoryId;
        this.parentId = parentId;
        this.categoryName = categoryName;
        this.childCategory = childCategory;
        this.productInCategory = productInCategory;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String  getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String  categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryViewModel> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(List<CategoryViewModel> childCategory) {
        this.childCategory = childCategory;
    }

    public List<ProductItemsView> getProductInCategory() {
        return productInCategory;
    }

    public void setProductInCategory(List<ProductItemsView> productInCategory) {
        this.productInCategory = productInCategory;
    }
}
