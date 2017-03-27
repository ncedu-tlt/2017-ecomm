package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.RecomendedProductDAOObject;

import java.util.List;

public interface RecomendedProductsDAO {
    List<RecomendedProductDAOObject> getRecomendedProducts();

    RecomendedProductDAOObject addRecomendedProduct(RecomendedProductDAOObject recomendedProduct);

    void deleteRecomendedProduct(RecomendedProductDAOObject recomendedProduct);

}
