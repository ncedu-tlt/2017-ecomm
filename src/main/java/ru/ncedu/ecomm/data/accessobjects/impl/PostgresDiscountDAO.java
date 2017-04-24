package ru.ncedu.ecomm.data.accessobjects.impl;

import org.apache.log4j.Logger;
import ru.ncedu.ecomm.data.accessobjects.DiscountDAO;
import ru.ncedu.ecomm.data.models.dao.DiscountDAOObject;
import ru.ncedu.ecomm.data.models.dao.builders.DiscountDAOObjectBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresDiscountDAO implements DiscountDAO {
    private static final Logger LOG  = Logger.getLogger(PostgresDiscountDAO.class);

    @Override
    public List<DiscountDAOObject> getDiscount() {
        List<DiscountDAOObject> discounts = new ArrayList<>();

        try(Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  discount_id,\n" +
                            "  name,\n" +
                            "  value\n" +
                            "FROM public.discount");

            while (resultSet.next()) {
                DiscountDAOObject discount = new DiscountDAOObjectBuilder()
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setName(resultSet.getString("name"))
                        .setValue(resultSet.getInt("value"))
                        .build();

                discounts.add(discount);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return discounts;
    }
}
