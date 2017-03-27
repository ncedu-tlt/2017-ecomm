package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.DiscountDAOObject;

import java.util.List;

public interface DiscountDAO {
   List<DiscountDAOObject> getDiscount();
}
