package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.RecomendedProduct;

import java.util.List;

public interface RecomendedProductsDAO {
    List<RecomendedProduct> getRecomendedProducts();

    RecomendedProduct addRecomendedProduct(RecomendedProduct recomendedProduct);

    RecomendedProduct updateRecomendedProduct(RecomendedProduct recomendedProduct);

    void deleteRecomendedProduct(RecomendedProduct recomendedProduct);

}
