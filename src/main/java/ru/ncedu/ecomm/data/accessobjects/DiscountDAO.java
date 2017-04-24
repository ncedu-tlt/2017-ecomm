package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.DiscountDAOObject;

import java.util.List;

public interface DiscountDAO {
   List<DiscountDAOObject> getDiscount();
}
