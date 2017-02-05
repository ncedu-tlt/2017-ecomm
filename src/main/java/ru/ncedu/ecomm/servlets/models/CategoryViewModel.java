package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class CategoryViewModel {
    private Long id;
    private String name;
    private String description;
    private List<ProductViewModel> products;

    public CategoryViewModel() {
    }

    public CategoryViewModel(Long id,
                             String name,
                             List<ProductViewModel> products,
                             String description) {

        this.description = description;
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getDescriprion() {
        return description;
    }

    public void setDescriprion(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long categoryId) {
        this.id = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String categoryName) {
        this.name = name;
    }

    public List<ProductViewModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductViewModel> products) {
        this.products = products;
    }
}
