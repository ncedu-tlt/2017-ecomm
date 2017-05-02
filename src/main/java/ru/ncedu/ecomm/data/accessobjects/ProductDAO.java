package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.ProductDAOObject;
import ru.ncedu.ecomm.servlets.models.FilterViewModel;
import ru.ncedu.ecomm.servlets.models.PriceRangeViewModel;
import ru.ncedu.ecomm.servlets.models.ProductDetailsModel;

import java.util.List;

public interface ProductDAO {

    List<ProductDAOObject> getProducts();

    ProductDAOObject addProduct(ProductDAOObject product);

    ProductDAOObject updateProduct(ProductDAOObject product);

    void deleteProduct(ProductDAOObject product);

    ProductDAOObject getProductById(long id);

    List<ProductDAOObject> getProductsByCategoryId(long categoryId);

    List<ProductDAOObject> getBestOffersProducts();

    List<ProductDAOObject> getProductAllChildrenCategory(long categoryId);

    List<ProductDAOObject> searchProductsByName(String name);

    PriceRangeViewModel getProductsPriceRangeByCategoryId(long categoryId);

    ProductDetailsModel getFullProductById(long productId);

    List<ProductDAOObject> getFilteredProducts(List<FilterViewModel> filters, PriceRangeViewModel priceRange, long categoryId);

    List<ProductDAOObject> getProductByOrderId(long orderId);

    List<ProductDAOObject> getAllChrildrenProductsByCategoryId(long categoryId);
}
