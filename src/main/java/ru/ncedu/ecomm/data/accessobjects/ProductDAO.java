package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getProducts();

    Product getProductById(long id);
}
