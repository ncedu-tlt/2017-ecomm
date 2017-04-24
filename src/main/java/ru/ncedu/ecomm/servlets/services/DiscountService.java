package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.dao.DiscountDAOObject;

import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class DiscountService {
    private static DiscountService instance;

    public static synchronized DiscountService getInstance() {
        if (instance == null) {
            instance = new DiscountService();
        }
        return instance;
    }

    public long getDiscountPrice(long discountId, long price) {
        double discountValue = price * (getDiscountValue(discountId) / 100.0);

        long discount = (long) discountValue;

        return price - discount;
    }

    private int getDiscountValue(long discountId) {
        List<DiscountDAOObject> allDiscountValues = getDAOFactory()
                .getDiscountDAO()
                .getDiscount();

        for (DiscountDAOObject discount : allDiscountValues) {
            if (discount.getDiscountId() == discountId) {
                return discount.getValue();
            }
        }

        return 0;
    }

}
