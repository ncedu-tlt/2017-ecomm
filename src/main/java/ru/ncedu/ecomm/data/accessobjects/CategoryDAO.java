package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.CategoryDAOObject;

import java.util.List;

public interface CategoryDAO {

    List<CategoryDAOObject> getCategories();

    CategoryDAOObject addCategory(CategoryDAOObject category);

    CategoryDAOObject updateCategory(CategoryDAOObject category);

    void deleteCategory(CategoryDAOObject category);

    CategoryDAOObject getCategoryById(long id);

    List<CategoryDAOObject> getCategoriesByParentId(long parentId);

    List<CategoryDAOObject> getAllNotEmptyChildrenCategoriesById(long categoryId);

    List<CategoryDAOObject> getAllNotEmptyCategories();

    List<CategoryDAOObject> getParentCategories();

    List<CategoryDAOObject> getCategoriesByHierarchy(long parentId);
}
