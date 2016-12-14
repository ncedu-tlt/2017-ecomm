package ru.ncedu.ecomm.data;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.accessobjects.*;
import ru.ncedu.ecomm.data.accessobjects.impl.*;

public abstract class DAOFactory {

    public abstract RoleDAO getRoleDAO();

    public abstract CategoryDAO getCategoryDAO();

    public abstract PropertyDAO getPropertyDAO();

    public abstract UserDAO getUserDAO();

    public abstract ProductDAO getProductDAO();
    // public abstract CategoryDAO getCategoryDAO();
    // another DAO...

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
                };
            default:
                throw new UnsupportedOperationException("Unsupported DAO type");
        }
    }
}
