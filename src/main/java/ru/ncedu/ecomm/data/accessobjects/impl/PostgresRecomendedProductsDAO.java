package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.RecomendedProductsDAO;
import ru.ncedu.ecomm.data.models.RecomendedProduct;
import ru.ncedu.ecomm.data.models.builders.RecomendedProductBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class PostgresRecomendedProductsDAO implements RecomendedProductsDAO{
    private static final Logger LOG  = Logger.getLogger(PostgresRecomendedProductsDAO.class);

    @Override
    public List<RecomendedProduct> getRecomendedProducts() {
        List<RecomendedProduct> recomendedProducts = new ArrayList<>();

        try(Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  target_product_id,\n" +
                            "  source_product_id\n" +
                            "FROM public.recommended_products"
            );
            while (resultSet.next()) {
                RecomendedProduct recomendedProduct = new RecomendedProductBuilder()
                        .setSourceProductId(resultSet.getLong("source_product_id"))
                        .setTargetProductId(resultSet.getLong("target_product_id"))
                        .build();

                recomendedProducts.add(recomendedProduct);
            }

            LOG.info(null);
            return recomendedProducts;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public RecomendedProduct addRecomendedProduct(RecomendedProduct recomendedProduct) {

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO public.recommended_products\n" +
                            "(source_product_id, target_product_id)\n" +
                            "VALUES (?, ?)"
            )) {
                statement.setLong(1, recomendedProduct.getSourceProductId());
                statement.setLong(2, recomendedProduct.getTargetProductId());
                statement.execute();

                LOG.info(null);
                return recomendedProduct;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRecomendedProduct(RecomendedProduct recomendedProduct) {

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM public.recommended_products\n" +
                            "WHERE source_product_id = ?\n" +
                            "      AND target_product_id = ?"
            )) {
            statement.setLong(1, recomendedProduct.getSourceProductId());
            statement.setLong(2, recomendedProduct.getTargetProductId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
