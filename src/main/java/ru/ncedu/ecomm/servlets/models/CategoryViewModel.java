package ru.ncedu.ecomm.servlets.models;

import java.util.Set;

public class CategoryViewModel {
    private long id;
    private String  name;
    private Set<CategoryViewModel> categories;
    private Set<ProductViewModel> products;

    public CategoryViewModel() {
    }

    public CategoryViewModel(long id,
                             String  name,
                             Set<CategoryViewModel> categories,
                             Set<ProductViewModel> products) {
        this.id = id;
        this.name = name;
        this.categories = categories;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long categoryId) {
        this.id = categoryId;
    }

    public String  getName() {
        return name;
    }

    public void setName(String  categoryName) {
        this.name = name;
    }

    public Set<CategoryViewModel> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryViewModel> childCategory) {
        this.categories = childCategory;
    }

    public Set<ProductViewModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductViewModel> products) {
        this.products = products;
    }
}
