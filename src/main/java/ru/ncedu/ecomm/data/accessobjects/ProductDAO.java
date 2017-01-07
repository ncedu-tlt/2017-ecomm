package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getProducts();

    Product addProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    Product getProductById(long id);

    List<Product> getProductsByCategoryId(long categoryId);

    List<Product> getBestOffersProducts();

}
