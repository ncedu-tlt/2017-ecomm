package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.data.models.PriceRangeModel;

import java.util.List;

public interface ProductDAO {

    List<Product> getProducts();

    Product addProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    Product getProductById(long id);

    List<Product> getProductsByCategoryId(long categoryId);

    List<Product> getBestOffersProducts();

    List<Product> getProductAllChildrenCategory(long categoryId);

    List<Product> searchProductsByName(String name);

    List<Product> getProductsFromPriceRangeByCategoryId(PriceRangeModel priceRange, long categoryId);

    PriceRangeModel getProductsPriceRangeByCategoryId(long categoryId);
}
