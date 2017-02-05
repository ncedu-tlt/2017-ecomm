package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.CategoryViewModel;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;

import java.util.List;

public class CategoryViewBuilder {
    private Long id;
    private String name;
    private String description;
    private List<ProductViewModel> products;

    public CategoryViewBuilder() {
    }

    public CategoryViewBuilder setId(Long categoryId) {
        this.id = categoryId;

        return this;
    }

    public CategoryViewBuilder setName(String categoryName) {
        this.name = categoryName;

        return this;
    }

    public CategoryViewBuilder setProducts(List<ProductViewModel> products) {
        this.products = products;

        return this;
    }

    public CategoryViewModel build() {
        return new CategoryViewModel(
                id,
                name,
                products,
                description
        );
    }
}
