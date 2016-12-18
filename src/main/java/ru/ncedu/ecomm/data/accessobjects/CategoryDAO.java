package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Category;

import java.util.List;

public interface CategoryDAO {

    List<Category> getCategories();

    Category addCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Category category);

    Category getCategoryById(long id);

    List<Category> getCategoriesByParentId(long parentId);

    List<Category> getCategoriesByHierarchy(long parentId);
}
