package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Discount;

import java.util.List;

public interface DiscountDAO {
   List<Discount> getDiscount();
}
