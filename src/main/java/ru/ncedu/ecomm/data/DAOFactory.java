package ru.ncedu.ecomm.data;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.accessobjects.*;
import ru.ncedu.ecomm.data.accessobjects.impl.*;

public abstract class DAOFactory {

    public abstract RoleDAO getRoleDAO();

    public abstract CategoryDAO getCategoryDAO();

    public abstract PropertyDAO getPropertyDAO();

    public abstract UserDAO getUserDAO();

    public abstract ReviewsDAO getReviewDAO();

    public abstract CharacteristicDAO getCahracteristicDAO();

    public abstract CharacteristicValueDAO getCharacteristicValueDAO();

    public abstract CharacteristicGroupDAO getCharacteristicGroupDAO();

    public abstract SalesOrdersDAO getSalesOrderDAO();

    public abstract RecomendedProductsDAO getRecomendedProductsDAO();

    public abstract OrderSratusDAO getOrderStatusDAO();

    public abstract OrderItemsDAO getOrderItemsDAO();

    public abstract DiscountDAO getDiscountDAO();

    public abstract ProductDAO getProductDAO();

    public static DAOFactory getDAOFactory() {

        switch (Configuration.getProperty("db.type")) {
            case "postgresql":
                return new DAOFactory() {
                    @Override
                    public ProductDAO getProductDAO() {
                        return new PostgresProductDAO();
                    }

                    @Override
                    public RoleDAO getRoleDAO() {
                        return new PostgresRoleDAO();
                    }

                    @Override
                    public CategoryDAO getCategoryDAO() {
                        return new PostgresCategoryDAO();
                    }


                    @Override
                    public PropertyDAO getPropertyDAO() {
                        return new PostgresPropertyDAO();
                    }

                    @Override
                    public UserDAO getUserDAO() {
                        return new PostgresUserDAO();
                    }

                    @Override
                    public ReviewsDAO getReviewDAO() {
                        return new PostgresReviewsDAO();
                    }

                    @Override
                    public CharacteristicDAO getCahracteristicDAO() {
                        return new PostgresCharacteristicDAO();
                    }

                    @Override
                    public CharacteristicValueDAO getCharacteristicValueDAO() {
                        return new PostgresCharacteristicVlaueDAO();
                    }

                    @Override
                    public CharacteristicGroupDAO getCharacteristicGroupDAO() {
                        return new PostgersCharacteristicGroupDAO();
                    }

                    @Override
                    public SalesOrdersDAO getSalesOrderDAO() {
                        return new PostgresSalesOrderDAO();
                    }

                    @Override
                    public RecomendedProductsDAO getRecomendedProductsDAO() {
                        return new PostgresRecomendedProductsDAO();
                    }

                    @Override
                    public OrderSratusDAO getOrderStatusDAO() {
                        return new PostgresOrderStatusDAO();
                    }

                    @Override
                    public OrderItemsDAO getOrderItemsDAO() {
                        return new PostgresOrderItemsDAO();
                    }

                    @Override
                    public DiscountDAO getDiscountDAO() {
                        return new PostgresDiscountDAO();
                    }
                };
            default:
                throw new UnsupportedOperationException("Unsupported DAO type");
        }
    }
}
